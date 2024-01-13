package com.nonsoolmate.nonsoolmateServer.external.aws.service;

import com.nonsoolmate.nonsoolmateServer.external.aws.error.AWSException;
import com.nonsoolmate.nonsoolmateServer.external.aws.error.AWSExceptionType;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.cloudfront.CloudFrontUtilities;
import software.amazon.awssdk.services.cloudfront.model.CannedSignerRequest;
import software.amazon.awssdk.services.cloudfront.url.SignedUrl;


@Component
@RequiredArgsConstructor
@Slf4j
public class CloudFrontService {
    @Value("${aws-property.distribution-domain}")
    private String distributionDomain;

    @Value("${aws-property.private-key-file-path}")
    private String privateKeyFilePath;

    @Value("${aws-property.key-pair-id}")
    private String keyPairId;

    public String createPreSignedGetUrl(String path, String fileName, int expireTime) {
        try {
            String encodedFileName = URLEncoder.encode(fileName, "UTF-8");
            String resourcePath = path + encodedFileName;
            String cloudFrontUrl = "https://" + distributionDomain + "/" + resourcePath;
            Instant expirationTime = Instant.now().plus(expireTime, ChronoUnit.MINUTES);
            Path keyPath = Paths.get(privateKeyFilePath);

            CloudFrontUtilities cloudFrontUtilities = CloudFrontUtilities.create();
            CannedSignerRequest cannedSignerRequest = CannedSignerRequest.builder()
                    .resourceUrl(cloudFrontUrl)
                    .privateKey(keyPath)
                    .keyPairId(keyPairId)
                    .expirationDate(expirationTime)
                    .build();
            SignedUrl signedUrl = cloudFrontUtilities.getSignedUrlWithCannedPolicy(cannedSignerRequest);
            return signedUrl.url();
        } catch (Exception e) {
            log.info("createPreSignedGetUrl = {}", e);
            throw new AWSException(AWSExceptionType.GET_PRESIGNED_URL_AWS_CLOUDFRONT_FAIL);
        }
    }
}

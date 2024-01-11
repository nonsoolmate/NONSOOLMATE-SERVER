package com.nonsoolmate.nonsoolmateServer.external.aws.service;

import com.amazonaws.services.cloudfront.CloudFrontUrlSigner;
import com.amazonaws.services.cloudfront.util.SignerUtils;
import com.nonsoolmate.nonsoolmateServer.external.aws.error.AWSException;
import com.nonsoolmate.nonsoolmateServer.external.aws.error.AWSExceptionType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.time.Duration;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;


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

    public String createPreSignedGetUrl(String path, String fileName) {
        String s3ObjectKey = path + fileName; // S3 객체 키
        Duration duration = Duration.ofMinutes(1);
        Date expirationTime = new Date(System.currentTimeMillis() + duration.toMillis());
        SignerUtils.Protocol protocol = SignerUtils.Protocol.https;
        String signedUrl = null;
        try {
            File privateKeyFile = ResourceUtils.getFile(privateKeyFilePath);
            signedUrl = CloudFrontUrlSigner.getSignedURLWithCannedPolicy(
                    protocol,
                    distributionDomain,
                    privateKeyFile,
                    s3ObjectKey,
                    keyPairId,
                    expirationTime
            );
        } catch (FileNotFoundException e) {
            throw new AWSException(AWSExceptionType.NOT_FOUND_CLOUD_PRIVATE_KEY_FAIL);
        } catch (InvalidKeySpecException e) {
            throw new AWSException(AWSExceptionType.INVALID_KEY_SPEC_FAIL);
        } catch (IOException e) {
            throw new AWSException(AWSExceptionType.GET_PRESIGNED_URL_AWS_CLOUDFRONT_FAIL);
        }
        return signedUrl;
    }
}

package com.nonsoolmate.nonsoolmateServer.external.aws.service;

import com.amazonaws.services.cloudfront.CloudFrontUrlSigner;
import com.amazonaws.services.cloudfront.util.SignerUtils;
import com.nonsoolmate.nonsoolmateServer.external.aws.config.AWSConfig;
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
public class S3Service {

    @Value("${aws-property.s3-bucket-name}")
    private String bucketName;
    private final AWSConfig awsConfig;

    @Value("${aws-property.distribution-domain}")
    private String distributionDomain;

    @Value("${aws-property.private-key-file-path}")
    private String privateKeyFilePath;

    @Value("${aws-property.key-pair-id}")
    private String keyPairId;

    private static final Long PRE_SIGNED_URL_EXPIRE_MINUTE = 1L;

    public String createPresignedGetUrl(String path, String fileName) {
        final String key = path + fileName;
        String s3ObjectKey = key; // S3 객체 키

        // 현재 시간에서 1시간 유효한 URL 생성
        Duration duration = Duration.ofHours(1);
        Date expirationTime = new Date(System.currentTimeMillis() + duration.toMillis());
        File privateKeyFile = null;
        String signedUrl = null;
        SignerUtils.Protocol protocol = SignerUtils.Protocol.https;
        try {
            privateKeyFile = ResourceUtils.getFile(privateKeyFilePath);

            signedUrl = CloudFrontUrlSigner.getSignedURLWithCannedPolicy(
                    protocol,
                    distributionDomain,
                    privateKeyFile,
                    s3ObjectKey,
                    keyPairId,
                    expirationTime
            );

            System.out.println("Signed URL: " + signedUrl);
        }catch (FileNotFoundException e){
            log.info("FileNotFoundException = {}", e);
        }catch (IOException e){
            log.info("");
        }catch (InvalidKeySpecException e){

        }
        return signedUrl;

//
//        try (S3Presigner presigner = S3Presigner.create()) {
//
//            GetObjectRequest objectRequest = GetObjectRequest.builder()
//                    .bucket(bucketName)
//                    .key(key)
//                    .build();
//
//            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
//                    .signatureDuration(Duration.ofMinutes(PRE_SIGNED_URL_EXPIRE_MINUTE))  // The URL will expire in 10 minutes.
//                    .getObjectRequest(objectRequest)
//                    .build();
//
//            PresignedGetObjectRequest presignedRequest = presigner.presignGetObject(presignRequest);
//            log.info("Presigned URL: [{}]", presignedRequest.url().toString());
//            log.info("HTTP method: [{}]", presignedRequest.httpRequest().method());
//
//            return presignedRequest.url().toExternalForm();
//        }
    }

}

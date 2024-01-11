package com.nonsoolmate.nonsoolmateServer.external.aws.service;

import com.nonsoolmate.nonsoolmateServer.external.aws.config.AWSConfig;
import com.nonsoolmate.nonsoolmateServer.external.aws.error.AWSException;
import com.nonsoolmate.nonsoolmateServer.external.aws.error.AWSExceptionType;
import com.nonsoolmate.nonsoolmateServer.external.aws.service.vo.PreSignedUrlVO;
import java.time.Duration;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;
import java.net.URL;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3Service {
    @Value("${aws-property.s3-bucket-name}")
    private String bucketName;
    private final AWSConfig awsConfig;
    private static final Long PRE_SIGNED_URL_EXPIRE_MINUTE = 1L;

    public PreSignedUrlVO getUploadPreSignedUrl(final String prefix) {
        String uuidFileName = generateZipFileName();
        String key = prefix + uuidFileName;

        S3Presigner preSigner = awsConfig.getS3Presigner();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        PutObjectPresignRequest preSignedUrlRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(PRE_SIGNED_URL_EXPIRE_MINUTE))
                .putObjectRequest(putObjectRequest)
                .build();

        URL url = preSigner.presignPutObject(preSignedUrlRequest).url();

        return PreSignedUrlVO.of(uuidFileName, url.toString());
    }

    private String generateZipFileName() {
        return UUID.randomUUID() + ".zip";
    }

    public String validateURL(final String prefix, final String fileName) {
        try {
            String zipUrl = prefix + fileName;
            GetUrlRequest request = GetUrlRequest.builder()
                    .bucket(bucketName)
                    .key(zipUrl)
                    .build();

            S3Client s3Client = awsConfig.getS3Client();

            URL url = s3Client.utilities().getUrl(request);
            if (zipUrl.equals(url.toString())) {
                return fileName;
            }
            throw new AWSException(AWSExceptionType.NOT_FOUND_FILE_AWS_S3);
        } catch (S3Exception e) {
            throw new AWSException(AWSExceptionType.NOT_FOUND_FILE_AWS_S3);
        }
    }

    public void deleteFile(final String prefix, final String fileName) {
        String key = prefix + fileName;
        final S3Client s3Client = awsConfig.getS3Client();
        try {
            s3Client.deleteObject((DeleteObjectRequest.Builder builder) ->
                    builder.bucket(bucketName)
                            .key(key)
                            .build()
            );
        } catch (S3Exception e) {
            throw new AWSException(AWSExceptionType.DELETE_FILE_AWS_S3_FAIL);
        }
    }
}

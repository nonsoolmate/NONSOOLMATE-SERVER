package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.service;

import static com.nonsoolmate.nonsoolmateServer.external.aws.FolderName.EXAM_SHEET_FOLDER_NAME;

import com.nonsoolmate.nonsoolmateServer.external.aws.service.S3Service;
import com.nonsoolmate.nonsoolmateServer.external.aws.service.vo.PreSignedUrlVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UniversityExamRecordSheetService {
    private final S3Service s3Service;

    public PreSignedUrlVO getUniversityExamRecordSheetPreSignedUrl() {
        return s3Service.getUploadPreSignedUrl(EXAM_SHEET_FOLDER_NAME);
    }

}

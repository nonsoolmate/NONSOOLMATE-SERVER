package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.service;

import static com.nonsoolmate.nonsoolmateServer.domain.university.exception.UniversityExamExceptionType.NOT_FOUND_UNIVERSITY_EXAM;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.university.entity.UniversityExam;
import com.nonsoolmate.nonsoolmateServer.domain.university.exception.UniversityExamException;
import com.nonsoolmate.nonsoolmateServer.domain.university.repository.UniversityExamRepository;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.UniversityExamRecordResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.entity.UniversityExamRecord;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.repository.UniversityExamRecordRepository;
import com.nonsoolmate.nonsoolmateServer.external.aws.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UniversityExamRecordService {

    private static final String EXAM_ANSWER_FOLDER_NAME = "exam-answer/";
    private static final String EXAM_RESULT_FOLDER_NAME = "exam-result/";


    private final UniversityExamRecordRepository universityExamRecordRepository;
    private final UniversityExamRepository universityExamRepository;
    private final S3Service s3Service;

    public UniversityExamRecordResponseDTO getUniversityExamRecord(Long universityExamId, Member member) {

        UniversityExam universityExam = universityExamRepository.findByUniversityExamId(universityExamId)
                .orElseThrow(() -> new UniversityExamException(NOT_FOUND_UNIVERSITY_EXAM));

        UniversityExamRecord universityExamRecord = universityExamRecordRepository.findByUniversityExamAndMemberOrElseThrowException(
                universityExam, member);

        String answerUrl = s3Service.createPresignedGetUrl(EXAM_ANSWER_FOLDER_NAME,
                universityExam.getExamAnswerFileName());
        String resultUrl = s3Service.createPresignedGetUrl(EXAM_RESULT_FOLDER_NAME,
                universityExamRecord.getExamRecordResultFileName());

        return UniversityExamRecordResponseDTO.of(answerUrl, resultUrl);
    }

}

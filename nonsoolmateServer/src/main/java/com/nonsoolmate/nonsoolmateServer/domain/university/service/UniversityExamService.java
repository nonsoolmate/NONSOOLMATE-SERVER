package com.nonsoolmate.nonsoolmateServer.domain.university.service;

import com.nonsoolmate.nonsoolmateServer.domain.university.controller.dto.UniversityExamImageAndAnswerResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.university.controller.dto.UniversityExamImageResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.university.controller.dto.UniversityExamInfoResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.university.entity.UniversityExam;
import com.nonsoolmate.nonsoolmateServer.domain.university.entity.UniversityExamImage;
import com.nonsoolmate.nonsoolmateServer.domain.university.exception.UniversityExamException;
import com.nonsoolmate.nonsoolmateServer.domain.university.exception.UniversityExamExceptionType;
import com.nonsoolmate.nonsoolmateServer.domain.university.repository.UniversityExamImageRepository;
import com.nonsoolmate.nonsoolmateServer.external.aws.service.CloudFrontService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nonsoolmate.nonsoolmateServer.domain.university.repository.UniversityExamRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UniversityExamService {
    private static final String EXAM_ANSWER_PATH = "exam-answer/";
    private static final String EXAM_IMAGE_PATH = "exam-image/";


    private final UniversityExamRepository universityExamRepository;
    private final UniversityExamImageRepository universityExamImageRepository;
    private final CloudFrontService cloudFrontService;


    public UniversityExamInfoResponseDTO getUniversityExam(Long universityExamId) {
        UniversityExam universityExam = universityExamRepository.findByUniversityExamId(universityExamId)
                .orElseThrow(() -> new UniversityExamException(
                        UniversityExamExceptionType.NOT_FOUND_UNIVERSITY_EXAM));
        return UniversityExamInfoResponseDTO.of(universityExam.getUniversityExamId(), universityExam.getExamName(),
                universityExam.getExamTimeLimit());
    }

    public Page<UniversityExamImageResponseDTO> getUniversityExamImages(Long id, Pageable pageable) {
        UniversityExam universityExam = universityExamRepository.findByUniversityExamId(id)
                .orElseThrow(() -> new UniversityExamException(
                        UniversityExamExceptionType.NOT_FOUND_UNIVERSITY_EXAM));
        Page<UniversityExamImage> universityExamImages = universityExamImageRepository.findAllByUniversityExam(
                universityExam,
                pageable);
        return universityExamImages.map(
                image -> UniversityExamImageResponseDTO.of(image.getUniversityExamImageFileName()));
    }

    public UniversityExamImageAndAnswerResponseDTO getUniversityExamImageAndAnswer(Long universityExamId) {
        UniversityExam universityExam = universityExamRepository.findByUniversityExamId(universityExamId)
                .orElseThrow(() -> new UniversityExamException(
                        UniversityExamExceptionType.NOT_FOUND_UNIVERSITY_EXAM));

        String examAnswerUrl = cloudFrontService.createPreSignedGetUrl(EXAM_ANSWER_PATH,
                universityExam.getExamAnswerFileName());
        List<UniversityExamImageResponseDTO> examImageUrls = new ArrayList<>();

        List<UniversityExamImage> UniversityExamImages = universityExamImageRepository.findAllByUniversityExam(
                universityExam);

        UniversityExamImages.stream().forEach(universityExamImage -> {
            String presignedGetUrl = cloudFrontService.createPreSignedGetUrl(EXAM_IMAGE_PATH,
                    universityExamImage.getUniversityExamImageFileName());

            examImageUrls.add(
                    UniversityExamImageResponseDTO.of(presignedGetUrl));
        });

        return UniversityExamImageAndAnswerResponseDTO.of(
                universityExam.getUniversity().getUniversityName(),
                universityExam.getUniversity().getUniversityCollege(), examImageUrls, examAnswerUrl);
    }

}
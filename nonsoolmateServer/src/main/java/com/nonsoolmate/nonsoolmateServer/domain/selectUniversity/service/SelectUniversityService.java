package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.service;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response.SelectUniversityExamResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response.SelectUniversityExamsResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response.SelectUniversityResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.entity.SelectUniversity;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.repository.SelectUniversityRepository;
import com.nonsoolmate.nonsoolmateServer.domain.university.entity.UniversityExam;
import com.nonsoolmate.nonsoolmateServer.domain.university.repository.UniversityExamRepository;
import com.nonsoolmate.nonsoolmateServer.domain.university.repository.UniversityRepository;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.entity.UniversityExamRecord;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.repository.UniversityExamRecordRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SelectUniversityService {
    private final SelectUniversityRepository selectUniversityRepository;
    private final UniversityRepository universityRepository;
    private final UniversityExamRepository universityExamRepository;
    private final UniversityExamRecordRepository universityExamRecordRepository;
    private final static String BEFORE_EXAM = "시험 응시 전";
    private final static String LEFT_BRACKET = "(";
    private final static String RIGHT_BRACKET = ")";


    public List<SelectUniversityResponseDTO> getSelectUniversities(Member member) {
        List<SelectUniversity> selectUniversities = selectUniversityRepository.findAllByMember(member);
        List<SelectUniversityResponseDTO> selectUniversityResponseDTOS = new ArrayList<>();

        universityRepository.findAll().stream().forEach(university -> {
            boolean status = false;

            for (SelectUniversity selectUniversity : selectUniversities) {
                if (university.getUniversityId() == selectUniversity.getUniversity().getUniversityId()) {
                    status = true;
                }
            }
            selectUniversityResponseDTOS.add(SelectUniversityResponseDTO.of(university.getUniversityId(),
                    university.getUniversityName() + LEFT_BRACKET + university.getUniversityCollege() + RIGHT_BRACKET,
                    status));

        });

        return selectUniversityResponseDTOS;
    }

    public List<SelectUniversityExamsResponseDTO> getSelectUniversityExams(Member member) {
        List<SelectUniversityExamsResponseDTO> selectUniversityExamsResponseDTOS = new ArrayList<>();
        List<SelectUniversity> selectUniversities = selectUniversityRepository.findAllByMember(member);

        for (SelectUniversity selectUniversity : selectUniversities) {
            selectUniversityExamsResponseDTOS.add(getSelectUniversityExamsResponseDTO(selectUniversity, member));
        }

        return selectUniversityExamsResponseDTOS;
    }

    private SelectUniversityExamsResponseDTO getSelectUniversityExamsResponseDTO(SelectUniversity selectUniversity,
                                                                                 Member member) {
        List<SelectUniversityExamResponseDTO> selectUniversityExamResponseDTOS;

        List<UniversityExam> universityExams = universityExamRepository.findAllByUniversity(
                selectUniversity.getUniversity());

        selectUniversityExamResponseDTOS = getSelectUniversityExamResponseDTOS(universityExams, member);

        return SelectUniversityExamsResponseDTO.of(selectUniversity.getUniversity().getUniversityId(),
                selectUniversity.getUniversity().getUniversityName(),
                selectUniversity.getUniversity().getUniversityCollege(), selectUniversityExamResponseDTOS);
    }

    private List<SelectUniversityExamResponseDTO> getSelectUniversityExamResponseDTOS(
            List<UniversityExam> universityExams, Member member) {
        UniversityExamRecord universityExamRecord;
        List<SelectUniversityExamResponseDTO> selectUniversityExamResponseDTOS = new ArrayList<>();
        for (UniversityExam universityExam : universityExams) {
            universityExamRecord = universityExamRecordRepository.findByUniversityExamAndMember(universityExam, member)
                    .orElse(null);
            String status =
                    universityExamRecord == null ? BEFORE_EXAM : universityExamRecord.getExamResultStatus().getStatus();
            selectUniversityExamResponseDTOS.add(
                    SelectUniversityExamResponseDTO.of(universityExam.getUniversityExamId(),
                            universityExam.getExamName(), universityExam.getExamTimeLimit(), status));
        }
        return selectUniversityExamResponseDTOS;
    }
}

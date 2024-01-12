package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.service;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.request.SelectUniversityRequestDTO;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response.SelectUniversityExamResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response.SelectUniversityExamsResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response.SelectUniversityResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response.SelectUniversityUpdateResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.entity.SelectUniversity;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.repository.SelectUniversityRepository;
import com.nonsoolmate.nonsoolmateServer.domain.university.entity.University;
import com.nonsoolmate.nonsoolmateServer.domain.university.entity.UniversityExam;
import com.nonsoolmate.nonsoolmateServer.domain.university.repository.UniversityExamRepository;
import com.nonsoolmate.nonsoolmateServer.domain.university.repository.UniversityRepository;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.entity.UniversityExamRecord;
import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.repository.UniversityExamRecordRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SelectUniversityService {
    private final SelectUniversityRepository selectUniversityRepository;
    private final UniversityRepository universityRepository;
    private final UniversityExamRepository universityExamRepository;
    private final UniversityExamRecordRepository universityExamRecordRepository;
    private final static String BEFORE_EXAM = "시험 응시 전";


    public List<SelectUniversityResponseDTO> getSelectUniversities(Member member) {
        List<SelectUniversity> selectUniversities = selectUniversityRepository.findAllByMemberOrderByUniversityNameASCUniversityCollegeAsc(
                member);
        List<SelectUniversityResponseDTO> selectUniversityResponseDTOS = new ArrayList<>();

        selectUniversities.stream().forEach(selectUniversity -> {
            boolean status = true;

            University university = universityRepository.findByUniversityId(
                    selectUniversity.getUniversity().getUniversityId()).orElse(null);
            if (university == null) {
                status = false;
            }
            selectUniversityResponseDTOS.add(SelectUniversityResponseDTO.of(university.getUniversityId(),
                    university.getUniversityName(), university.getUniversityCollege(),
                    status));

        });

        return selectUniversityResponseDTOS;
    }

    public List<SelectUniversityExamsResponseDTO> getSelectUniversityExams(Member member) {
        List<SelectUniversityExamsResponseDTO> selectUniversityExamsResponseDTOS = new ArrayList<>();
        List<SelectUniversity> selectUniversities = selectUniversityRepository.findAllByMemberOrderByUniversityNameASCUniversityCollegeAsc(
                member);

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
                            universityExam.getUniversityExamName(), universityExam.getUniversityExamTimeLimit(),
                            status));
        }
        return selectUniversityExamResponseDTOS;
    }

    public SelectUniversityUpdateResponseDTO patchSelectUniversities(
            Member member,
            List<SelectUniversityRequestDTO> request) {

        List<Long> prevUniversityIds = selectUniversityRepository.findAllByMember(member).stream()
                .map(univ -> univ.getUniversity().getUniversityId())
                .toList();
        List<Long> curUniversityIds = request.stream().map(req -> req.universityId()).toList();

        Map<Long, University> universityMap = new HashMap<>();

        curUniversityIds.stream().forEach(curUniversityId -> {
            universityMap.put(curUniversityId,
                    universityRepository.findByUniversityIdOrElseThrowException(curUniversityId));

            boolean isPresent = false;
            for (Long prevUniversityId : prevUniversityIds) {
                if (prevUniversityId == curUniversityId) {
                    isPresent = true;
                }
            }

            if (!isPresent) {
                selectUniversityRepository.save(SelectUniversity
                        .builder()
                        .member(member)
                        .university(universityMap.get(curUniversityId))
                        .build());
            }
        });

        prevUniversityIds.stream().forEach(prevUniversityId -> {
            universityMap.put(prevUniversityId,
                    universityRepository.findByUniversityIdOrElseThrowException(prevUniversityId));

            boolean isPresent = false;
            for (Long curUniversityId : curUniversityIds) {
                if (prevUniversityId == curUniversityId) {
                    isPresent = true;
                }
            }

            if (!isPresent) {
                selectUniversityRepository.deleteByMemberAndUniversity(member, universityMap.get(prevUniversityId));
            }
        });

        return SelectUniversityUpdateResponseDTO.of(true);
    }
}

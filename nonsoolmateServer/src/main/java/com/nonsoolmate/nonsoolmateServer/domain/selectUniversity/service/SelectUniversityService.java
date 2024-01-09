package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.service;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response.SelectUniversityResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.entity.SelectUniversity;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.repository.SelectUniversityRepository;
import com.nonsoolmate.nonsoolmateServer.domain.university.entity.University;
import com.nonsoolmate.nonsoolmateServer.domain.university.repository.UniversityRepository;
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


    public List<SelectUniversityResponseDTO> getSelectUniversities(Member member){
        List<SelectUniversity> selectUniversities = selectUniversityRepository.findAllByMember(member);
        List<SelectUniversityResponseDTO> selectUniversityResponseDTOS = new ArrayList<>();

        universityRepository.findAll().stream().forEach(university -> {
            boolean status = false;

            for(SelectUniversity selectUniversity : selectUniversities){
                if(university.getUniversityId() == selectUniversity.getUniversity().getUniversityId()){
                    status = true;
                }
            }
            selectUniversityResponseDTOS.add(SelectUniversityResponseDTO.of(university.getUniversityId(),
                    university.getUniversityName() + university.getUniversityCollege(), status));

        });

        return selectUniversityResponseDTOS;
    }
}

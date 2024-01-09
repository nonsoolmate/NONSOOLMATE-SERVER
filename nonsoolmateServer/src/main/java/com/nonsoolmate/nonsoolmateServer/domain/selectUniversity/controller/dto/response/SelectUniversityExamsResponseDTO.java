package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response;

import java.util.List;

public record SelectUniversityExamsResponseDTO(Long universityId, String universityName, String universityCollege,
                                               List<SelectUniversityExamResponseDTO> examList) {
    public static SelectUniversityExamsResponseDTO of(Long universityId, String universityName,
                                                      String universityCollege,
                                                      List<SelectUniversityExamResponseDTO> examList) {
        return new SelectUniversityExamsResponseDTO(universityId, universityName, universityCollege, examList);
    }
}

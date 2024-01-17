package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(name = "SelectUniversityExamsResponseDTO", description = "대학별 시험 리스트 조회 요청 DTO")
public record SelectUniversityExamsResponseDTO(@Schema(name = "대학 id", example = "대학 이름") Long universityId,
                                               @Schema(name = "대학 이름", example = "중앙대학교") String universityName,
                                               @Schema(name = "단과대학 이름", example = "경영경제") String universityCollege,
                                               @Schema(name = "시험 리스트") List<SelectUniversityExamResponseDTO> examList) {
    public static SelectUniversityExamsResponseDTO of(Long universityId, String universityName,
                                                      String universityCollege,
                                                      List<SelectUniversityExamResponseDTO> examList) {
        return new SelectUniversityExamsResponseDTO(universityId, universityName, universityCollege, examList);
    }
}

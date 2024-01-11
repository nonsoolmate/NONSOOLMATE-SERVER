package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreateUniversityExamRequestDTO(@NotNull Long universityExamId, @NotNull int memberTakeTimeExam,
                                             String memberSheetFileName) {
    public static CreateUniversityExamRequestDTO of(Long universityExamId, int memberTakeTimeExam,
                                                    String memberSheetFileName) {
        return new CreateUniversityExamRequestDTO(universityExamId, memberTakeTimeExam, memberSheetFileName);
    }
}

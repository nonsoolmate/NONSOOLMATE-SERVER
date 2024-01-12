package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateUniversityExamRequestDTO(@NotNull Long universityExamId,
                                             @Positive int memberTakeTimeExam,
                                             @NotNull String memberSheetFileName) {
    public static CreateUniversityExamRequestDTO of(Long universityExamId, int memberTakeTimeExam,
                                                    String memberSheetFileName) {
        return new CreateUniversityExamRequestDTO(universityExamId, memberTakeTimeExam, memberSheetFileName);
    }
}

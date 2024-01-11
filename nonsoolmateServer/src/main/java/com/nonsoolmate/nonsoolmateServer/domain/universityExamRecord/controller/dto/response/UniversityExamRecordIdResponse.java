package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.controller.dto.response;

public record UniversityExamRecordIdResponse(Long universityExamRecordId) {
    public static UniversityExamRecordIdResponse of(Long universityExamRecordId) {
        return new UniversityExamRecordIdResponse(universityExamRecordId);
    }
}
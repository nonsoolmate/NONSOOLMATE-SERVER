package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExamResultStatus {
    ONGOING("첨삭 진행 중"), FINISH("첨삭 완료");

    private final String status;
}

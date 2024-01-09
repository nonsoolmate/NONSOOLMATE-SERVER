package com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.entity;

import com.nonsoolmate.nonsoolmateServer.domain.universityExamRecord.entity.enums.ExamResultStatus;
import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.university.entity.UniversityExam;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import java.sql.Time;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UniversityExamRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long universityExamRecordId;

    @ManyToOne
    @JoinColumn(name = "university_exam_id")
    private UniversityExam universityExam;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ExamResultStatus examResultStatus;

    @NotNull
    private int timeTakeExam;

    @NotNull
    private String examRecordSheetUrl;

    @NotNull
    private String examRecordResultUrl;
}
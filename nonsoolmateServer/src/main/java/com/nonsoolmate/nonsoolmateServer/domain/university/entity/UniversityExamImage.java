package com.nonsoolmate.nonsoolmateServer.domain.university.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UniversityExamImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long universityExamImageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_exam_id")
    private UniversityExam universityExam;

    @NotNull
    private String universityExamImageFileName;

    @NotNull
    private int page;
}
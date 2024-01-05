package com.nonsoolmate.nonsoolmateServer.domain.member.entity;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.enums.PlatformType;
import com.nonsoolmate.nonsoolmateServer.domain.member.entity.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.Year;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @NotNull
    private String email;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PlatformType platformType;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @Length(max = 4)
    private String birthYear;

    @Length(max = 1)
    private String gender;

    @NotNull
    @Length(max = 13)
    private String phoneNumber;

    @ColumnDefault("0")
    private int ticketCount;

    private LocalDateTime ticketPreviousPublicationTime;

    @Builder
    public Member(String email, String name, PlatformType platformType, Role role, String birthYear,
                  String gender, String phoneNumber) {
        this.email = email;
        this.name = name;
        this.platformType = platformType;
        this.role = role;
        this.birthYear = birthYear;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }
}

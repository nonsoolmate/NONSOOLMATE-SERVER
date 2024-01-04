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
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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
    private String memberName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PlatformType platformType;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @NotNull
    private Year memberBirthYear;

    @ColumnDefault("0")
    private int ticketCount;

    private LocalDateTime ticketPreviousPublicationTime;

    @NotNull
    private String phoneNumber;
}

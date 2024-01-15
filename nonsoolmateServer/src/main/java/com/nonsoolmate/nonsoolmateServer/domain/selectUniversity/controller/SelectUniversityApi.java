package com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller;

import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.request.SelectUniversityRequestDTO;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response.SelectUniversityExamsResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response.SelectUniversityResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.selectUniversity.controller.dto.response.SelectUniversityUpdateResponseDTO;
import com.nonsoolmate.nonsoolmateServer.global.response.ApiResponse;
import com.nonsoolmate.nonsoolmateServer.global.security.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;


@Tag(name = "SelectUniversity", description = "목표 대학과 관련된 API")
public interface SelectUniversityApi {

    @ApiResponses(
            value = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", content = @Content)
            }
    )

    @Operation(summary = "목표대학 설정: 리스트 조회", description = "내 목표 대학 리스트를 조회합니다.")
    ResponseEntity<ApiResponse<List<SelectUniversityResponseDTO>>> getSelectUniversities(
            @AuthUser Member member);

    @Operation(summary = "마이 페이지: 대학별 시험 리스트 조회", description = "내 목표 대학들의 시험 리스트를 조회합니다.")
    ResponseEntity<ApiResponse<List<SelectUniversityExamsResponseDTO>>> getSelectUniversityExams(
            @AuthUser Member member);

    @Operation(summary = "목표대학 설정: 리스트 선택", description = "내 목표 대학들 리스트를 업데이트(수정) 합니다.")
    ResponseEntity<ApiResponse<SelectUniversityUpdateResponseDTO>> patchSelectUniversities(
            @AuthUser Member member,
            @Parameter(description = "선택 대학교 Id", required = true) List<SelectUniversityRequestDTO> request);
}

package com.nonsoolmate.nonsoolmateServer.domain.member.controller;

import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.NameResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response.TicketResponseDTO;
import com.nonsoolmate.nonsoolmateServer.domain.member.entity.Member;
import com.nonsoolmate.nonsoolmateServer.global.response.ApiResponse;
import com.nonsoolmate.nonsoolmateServer.global.security.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "my", description = "멤버와 관련된 API")
public interface MemberApi {
    @ApiResponses(
            value = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", content = @Content)
            }
    )

    @Operation(summary = "마이페이지: 이름", description = "내 이름을 조회합니다.")
    ResponseEntity<ApiResponse<NameResponseDTO>> getName(
            @AuthUser Member member);

    @Operation(summary = "내 정보 확인: 첨삭권 개수", description = "내 첨삭권 갯수를 조회합니다.")
    ResponseEntity<ApiResponse<TicketResponseDTO>> getTicket(@AuthUser Member member);

}

package com.nonsoolmate.nonsoolmateServer.domain.member.controller.dto.response;

public record TicketResponseDTO(String memberName, int ticketCount) {
    static public TicketResponseDTO of(String memberName, int ticketCount) {
        return new TicketResponseDTO(memberName, ticketCount);
    }
}

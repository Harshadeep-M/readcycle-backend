package com.readcycle.readcycle.dto;

import jakarta.validation.constraints.NotNull;

public class ExchangeRequestDTO {

    @NotNull(message = "Requester ID is required")
    private Long requesterId;

    @NotNull(message = "Book ID is required")
    private Long bookId;

    public ExchangeRequestDTO() {
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
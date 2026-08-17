package com.readcycle.readcycle.dto;

import jakarta.validation.constraints.NotNull;

public class WishlistRequestDTO {

    @NotNull(message = "Book ID is required")
    private Long bookId;

    public WishlistRequestDTO() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
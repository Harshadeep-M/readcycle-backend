package com.readcycle.readcycle.dto;

import jakarta.validation.constraints.NotNull;

public class WishlistRequestDTO {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Book ID is required")
    private Long bookId;

    public WishlistRequestDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
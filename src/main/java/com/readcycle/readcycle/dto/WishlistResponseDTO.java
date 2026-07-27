package com.readcycle.readcycle.dto;

public class WishlistResponseDTO {

    private Long id;
    private Long userId;
    private Long bookId;

    public WishlistResponseDTO() {
    }

    public WishlistResponseDTO(Long id, Long userId, Long bookId) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
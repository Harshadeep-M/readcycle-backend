package com.readcycle.readcycle.dto;

import com.readcycle.readcycle.enums.ExchangeStatus;

import java.time.LocalDateTime;

public class ExchangeResponseDTO {

    private Long id;
    private Long requesterId;
    private Long ownerId;
    private Long bookId;
    private ExchangeStatus status;
    private LocalDateTime requestDate;

    public ExchangeResponseDTO() {
    }

    public ExchangeResponseDTO(Long id,
                               Long requesterId,
                               Long ownerId,
                               Long bookId,
                               ExchangeStatus status,
                               LocalDateTime requestDate) {

        this.id = id;
        this.requesterId = requesterId;
        this.ownerId = ownerId;
        this.bookId = bookId;
        this.status = status;
        this.requestDate = requestDate;
    }

    public Long getId() {
        return id;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Long getBookId() {
        return bookId;
    }

    public ExchangeStatus getStatus() {
        return status;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setStatus(ExchangeStatus status) {
        this.status = status;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }
}
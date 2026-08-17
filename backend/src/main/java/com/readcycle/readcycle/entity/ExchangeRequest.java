package com.readcycle.readcycle.entity;

import com.readcycle.readcycle.enums.ExchangeStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ExchangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User requesting the book
    private Long requesterId;

    // Owner of the book
    private Long ownerId;

    // Requested book
    private Long bookId;

    @Enumerated(EnumType.STRING)
    private ExchangeStatus status;

    private LocalDateTime requestDate;

    public ExchangeRequest() {
    }

    public Long getId() {
        return id;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public ExchangeStatus getStatus() {
        return status;
    }

    public void setStatus(ExchangeStatus status) {
        this.status = status;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }
}
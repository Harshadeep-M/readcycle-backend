package com.readcycle.readcycle.service;

import com.readcycle.readcycle.dto.ExchangeRequestDTO;
import com.readcycle.readcycle.dto.ExchangeResponseDTO;
import com.readcycle.readcycle.entity.Book;
import com.readcycle.readcycle.entity.ExchangeRequest;
import com.readcycle.readcycle.enums.ExchangeStatus;
import com.readcycle.readcycle.exception.ResourceNotFoundException;
import com.readcycle.readcycle.mapper.ExchangeMapper;
import com.readcycle.readcycle.repository.BookRepository;
import com.readcycle.readcycle.repository.ExchangeRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeRequestService {

    private final ExchangeRequestRepository exchangeRequestRepository;
    private final BookRepository bookRepository;

    public ExchangeRequestService(ExchangeRequestRepository exchangeRequestRepository,
                                  BookRepository bookRepository) {

        this.exchangeRequestRepository = exchangeRequestRepository;
        this.bookRepository = bookRepository;
    }

    // CREATE EXCHANGE REQUEST
    public ExchangeResponseDTO createExchangeRequest(ExchangeRequestDTO request) {

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        if (!book.isAvailable()) {
            throw new RuntimeException("Book is not available for exchange.");
        }

        if (book.getOwnerId().equals(request.getRequesterId())) {
            throw new RuntimeException("You cannot request your own book.");
        }

        ExchangeRequest exchangeRequest = new ExchangeRequest();

        exchangeRequest.setRequesterId(request.getRequesterId());
        exchangeRequest.setOwnerId(book.getOwnerId());
        exchangeRequest.setBookId(book.getId());
        exchangeRequest.setStatus(ExchangeStatus.PENDING);
        exchangeRequest.setRequestDate(LocalDateTime.now());

        ExchangeRequest savedRequest = exchangeRequestRepository.save(exchangeRequest);

        return ExchangeMapper.toResponseDTO(savedRequest);
    }

    // GET ALL REQUESTS
    public List<ExchangeResponseDTO> getAllExchangeRequests() {

        List<ExchangeResponseDTO> responseList = new ArrayList<>();

        for (ExchangeRequest request : exchangeRequestRepository.findAll()) {
            responseList.add(ExchangeMapper.toResponseDTO(request));
        }

        return responseList;
    }

    // GET REQUEST BY ID
    public ExchangeResponseDTO getExchangeRequestById(Long id) {

        ExchangeRequest request = exchangeRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exchange Request not found"));

        return ExchangeMapper.toResponseDTO(request);
    }

    // ACCEPT REQUEST
    public ExchangeResponseDTO acceptExchangeRequest(Long id) {
        return updateStatus(id, ExchangeStatus.ACCEPTED);
    }

    // REJECT REQUEST
    public ExchangeResponseDTO rejectExchangeRequest(Long id) {
        return updateStatus(id, ExchangeStatus.REJECTED);
    }

    // PRIVATE HELPER
    private ExchangeResponseDTO updateStatus(Long id, ExchangeStatus status) {

        ExchangeRequest request = exchangeRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exchange Request not found"));

        request.setStatus(status);

        ExchangeRequest updatedRequest = exchangeRequestRepository.save(request);

        return ExchangeMapper.toResponseDTO(updatedRequest);
    }
}
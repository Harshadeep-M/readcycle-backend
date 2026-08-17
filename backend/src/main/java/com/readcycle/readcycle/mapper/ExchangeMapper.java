package com.readcycle.readcycle.mapper;

import com.readcycle.readcycle.dto.ExchangeResponseDTO;
import com.readcycle.readcycle.entity.ExchangeRequest;

public class ExchangeMapper {

    // Convert Entity -> Response DTO
    public static ExchangeResponseDTO toResponseDTO(ExchangeRequest exchangeRequest) {

        return new ExchangeResponseDTO(
                exchangeRequest.getId(),
                exchangeRequest.getRequesterId(),
                exchangeRequest.getOwnerId(),
                exchangeRequest.getBookId(),
                exchangeRequest.getStatus(),
                exchangeRequest.getRequestDate()
        );
    }
}   
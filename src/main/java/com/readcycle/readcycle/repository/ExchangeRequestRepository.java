package com.readcycle.readcycle.repository;

import com.readcycle.readcycle.entity.ExchangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRequestRepository extends JpaRepository<ExchangeRequest, Long> {

}
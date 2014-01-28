package com.apress.prospring3.ch10.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apress.prospring3.ch10.domain.ContactTelDetail;

public interface ContactTelDetailRepository extends JpaRepository<ContactTelDetail, Long> {
}

package com.apress.prospring3.ch10.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apress.prospring3.ch10.domain.Hobby;

public interface HobbyRepository extends JpaRepository<Hobby, String> {
}

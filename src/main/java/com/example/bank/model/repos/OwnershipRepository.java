package com.example.bank.model.repos;

import com.example.bank.model.entities.Ownership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnershipRepository extends JpaRepository<Ownership, Long> {
}


package com.example.bank.model.repos;

import com.example.bank.model.entities.StaffStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffStatusRepository extends JpaRepository<StaffStatus, Long> {
}

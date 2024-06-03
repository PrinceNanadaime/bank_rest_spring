package com.example.bank.model.repos;

import com.example.bank.model.entities.ClientStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientStatusRepository extends JpaRepository<ClientStatus, Long> {
    /*@Modifying
    @Query("update status_values u set u.value = :value where u.id = :id")
    void updateValue(@Param(value = "id") long id, @Param(value = "value") String value);
     */
}

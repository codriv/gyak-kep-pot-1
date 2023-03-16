package org.training360.sv2jvjbfzarovizsga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.training360.sv2jvjbfzarovizsga.model.School;

import java.util.List;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {

    @Query("SELECT s FROM School s WHERE :city IS NULL OR UPPER(s.address.city) LIKE UPPER(CONCAT('%', :city, '%'))")
    List<School> findByCity(Optional<String> city);
}

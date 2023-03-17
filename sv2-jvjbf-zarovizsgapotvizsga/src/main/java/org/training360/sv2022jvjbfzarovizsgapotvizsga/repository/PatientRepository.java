package org.training360.sv2022jvjbfzarovizsgapotvizsga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.training360.sv2022jvjbfzarovizsgapotvizsga.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE ((p.hospital.id = :id) AND ((:hospitalWard IS NULL) OR (UPPER(p.hospitalWard) LIKE UPPER(CONCAT('%', :hospitalWard, '%'))))) ORDER BY p.name")
    List<Patient> findPatientsByHospitalWard(Long id, Optional<String> hospitalWard);

    Optional<Patient> findByIdAndHospitalId(Long ptId, Long id);
}

package org.training360.sv2jvjbfzarovizsga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training360.sv2jvjbfzarovizsga.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // jo
    Optional<Student> findByIdAndSchoolId(Long id, Long schoolId);

    // jo
//    List<Student> findByIdAndSchoolId(Long id, Long schoolId);
}

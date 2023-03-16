package org.training360.sv2jvjbfzarovizsga.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    @Column(name = "school_age_status")
    private SchoolAgeStatus schoolAgeStatus;
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    public Student(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        setSchoolAgeStatus();
    }

    public void setSchoolAgeStatus() {
        long age = ChronoUnit.YEARS.between(LocalDate.now(), dateOfBirth);
        schoolAgeStatus = age < 16 ? SchoolAgeStatus.SCHOOL_AGED : SchoolAgeStatus.NOT_SCHOOL_AGED;
    }
}

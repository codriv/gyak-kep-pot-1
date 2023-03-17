package org.training360.sv2jvjbfzarovizsga.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.training360.sv2jvjbfzarovizsga.model.SchoolAgeStatus;

@Getter
@Setter
@NoArgsConstructor
public class StudentDto {

    private Long id;
    private String name;
    private SchoolAgeStatus schoolAgeStatus;
}

package org.training360.sv2022jvjbfzarovizsgapotvizsga.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.training360.sv2022jvjbfzarovizsgapotvizsga.model.Patient;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HospitalDto {

    private Long id;
    private String hospitalName;
    private List<PatientDto> patients;
}

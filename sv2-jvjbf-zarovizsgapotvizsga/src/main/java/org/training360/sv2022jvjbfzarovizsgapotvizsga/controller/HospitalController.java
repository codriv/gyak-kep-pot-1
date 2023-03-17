package org.training360.sv2022jvjbfzarovizsgapotvizsga.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.training360.sv2022jvjbfzarovizsgapotvizsga.dto.CreateHospitalCommand;
import org.training360.sv2022jvjbfzarovizsgapotvizsga.dto.CreatePatientCommand;
import org.training360.sv2022jvjbfzarovizsgapotvizsga.dto.HospitalDto;
import org.training360.sv2022jvjbfzarovizsgapotvizsga.dto.PatientDto;
import org.training360.sv2022jvjbfzarovizsgapotvizsga.model.Hospital;
import org.training360.sv2022jvjbfzarovizsgapotvizsga.service.HospitalService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    private HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HospitalDto createHospital(@RequestBody @Valid CreateHospitalCommand command) {
        return hospitalService.createHospital(command);
    }

    @PostMapping("/{id}/patients")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDto addPatient(@PathVariable("id") Long id, @RequestBody @Valid CreatePatientCommand command) {
        return hospitalService.addPatient(id, command);
    }

    @GetMapping("/{id}/patients")
    @ResponseStatus(HttpStatus.OK)
    public List<PatientDto> getPatients(@PathVariable("id") Long id, @RequestParam Optional<String> hospitalWard) {
        return hospitalService.getPatients(id, hospitalWard);
    }

    @DeleteMapping("/{id}/patients/{ptId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePatient(@PathVariable("id") Long id, @PathVariable("ptId") Long ptId) {
        hospitalService.removePatient(id, ptId);
    }
}

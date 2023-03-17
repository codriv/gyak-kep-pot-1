package org.training360.sv2022jvjbfzarovizsgapotvizsga.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training360.sv2022jvjbfzarovizsgapotvizsga.dto.CreateHospitalCommand;
import org.training360.sv2022jvjbfzarovizsgapotvizsga.dto.CreatePatientCommand;
import org.training360.sv2022jvjbfzarovizsgapotvizsga.dto.HospitalDto;
import org.training360.sv2022jvjbfzarovizsgapotvizsga.dto.PatientDto;
import org.training360.sv2022jvjbfzarovizsgapotvizsga.exceptions.EntityNotFoundException;
import org.training360.sv2022jvjbfzarovizsgapotvizsga.model.Hospital;
import org.training360.sv2022jvjbfzarovizsgapotvizsga.model.Patient;
import org.training360.sv2022jvjbfzarovizsgapotvizsga.repository.HospitalRepository;
import org.training360.sv2022jvjbfzarovizsgapotvizsga.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HospitalService {

    private HospitalRepository hospitalRepository;
    private PatientRepository patientRepository;
    private ModelMapper modelMapper;

    public HospitalService(HospitalRepository hospitalRepository, PatientRepository patientRepository, ModelMapper modelMapper) {
        this.hospitalRepository = hospitalRepository;
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    public HospitalDto createHospital(CreateHospitalCommand command) {
        Hospital hospital = new Hospital(command.getHospitalName());
        hospitalRepository.save(hospital);
        return modelMapper.map(hospital, HospitalDto.class);
    }

    public PatientDto addPatient(Long id, CreatePatientCommand command) {
        Hospital hospital = findHospitalById(id);
        Patient patient = modelMapper.map(command, Patient.class);
        patientRepository.save(patient);
        hospital.addPatient(patient);
        return modelMapper.map(patient, PatientDto.class);
    }

    public List<PatientDto> getPatients(Long id, Optional<String> hospitalWard) {
        Hospital hospital = findHospitalById(id);
        List<Patient> patients = patientRepository.findPatientsByHospitalWard(id, hospitalWard);
        return patients.stream().map(p -> modelMapper.map(p, PatientDto.class)).toList();
    }

    public void removePatient(Long id, Long ptId) {
        Hospital hospital = findHospitalById(id);
        Patient patient = findPatientById(ptId);
        if (patientRepository.findByIdAndHospitalId(ptId, id).isEmpty()) {
            throw new EntityNotFoundException("Patient", ptId);
        } else {
            hospital.removePatient(patient);
        }
    }

    private Hospital findHospitalById(Long id) {
        return hospitalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Hospital", id));
    }

    private Patient findPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Patient", id));
    }
}

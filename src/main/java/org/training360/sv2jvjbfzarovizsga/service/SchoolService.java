package org.training360.sv2jvjbfzarovizsga.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training360.sv2jvjbfzarovizsga.dto.CreateSchoolCommand;
import org.training360.sv2jvjbfzarovizsga.dto.CreateStudentCommand;
import org.training360.sv2jvjbfzarovizsga.dto.SchoolDto;
import org.training360.sv2jvjbfzarovizsga.exceptions.EntityNotFoundException;
import org.training360.sv2jvjbfzarovizsga.model.Address;
import org.training360.sv2jvjbfzarovizsga.model.School;
import org.training360.sv2jvjbfzarovizsga.model.Student;
import org.training360.sv2jvjbfzarovizsga.repository.SchoolRepository;
import org.training360.sv2jvjbfzarovizsga.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SchoolService {

    private SchoolRepository schoolRepository;
    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    public SchoolService(SchoolRepository schoolRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
        this.schoolRepository = schoolRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public SchoolDto createSchool(CreateSchoolCommand command) {
        Address address = new Address(command.getPostalCode(), command.getCity(), command.getStreet(), command.getHouseNumber());
        School school = new School(command.getSchoolName(), address);
        schoolRepository.save(school);
        return modelMapper.map(school, SchoolDto.class);
    }

    public SchoolDto addStudent(Long id, CreateStudentCommand command) {
        Student student = new Student(command.getName(), command.getDateOfBirth());
        studentRepository.save(student);
        School school = findSchoolById(id);
        school.addStudent(student);
        return modelMapper.map(school, SchoolDto.class);
    }

    private School findSchoolById(Long id) {
        return schoolRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("School", id));
    }

    private Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student", id));
    }

    public List<SchoolDto> getSchools(Optional<String> city) {
        List<School> schools = schoolRepository.findByCity(city);
        return schools.stream().map(s -> modelMapper.map(s, SchoolDto.class)).toList();
    }

    public SchoolDto studentOff(Long id, Long stdId) {
        School school = findSchoolById(id);
        Student student = findStudentById(stdId);
        if (!school.getStudents().contains(student)) {
            throw new EntityNotFoundException("Student", stdId);
        } else {
            school.removeStudent(student);
        }
        return modelMapper.map(school, SchoolDto.class);
    }
}

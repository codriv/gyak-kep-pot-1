package org.training360.sv2jvjbfzarovizsga.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.training360.sv2jvjbfzarovizsga.dto.CreateSchoolCommand;
import org.training360.sv2jvjbfzarovizsga.dto.CreateStudentCommand;
import org.training360.sv2jvjbfzarovizsga.dto.SchoolDto;
import org.training360.sv2jvjbfzarovizsga.service.SchoolService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    private SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolDto createSchool(@Valid @RequestBody CreateSchoolCommand command) {
        return schoolService.createSchool(command);
    }

    @PostMapping("{id}/students")
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolDto addStudent(@PathVariable("id") Long id, @Valid @RequestBody CreateStudentCommand command) {
        return schoolService.addStudent(id, command);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SchoolDto> getSchools(@RequestParam Optional<String> city) {
        return schoolService.getSchools(city);
    }

    @PutMapping("/{id}/students/{stdId}")
    @ResponseStatus(HttpStatus.OK)
    public SchoolDto studentOff(@PathVariable("id") Long id, @PathVariable("stdId") Long stdId) {
        return schoolService.studentOff(id, stdId);
    }
}

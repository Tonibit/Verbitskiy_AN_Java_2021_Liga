package com.verbitskiy.springboot_mvc.controller;

import com.verbitskiy.springboot_mvc.dto.SchoolDTO;
import com.verbitskiy.springboot_mvc.entity.School;
import com.verbitskiy.springboot_mvc.services.interfaces.SchoolService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public List<SchoolDTO> getAllSchools() {
        return schoolService.getAllSchools().stream()
                .map(SchoolDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SchoolDTO getSchool(@PathVariable("id") int id) throws NotFoundException {
        return new SchoolDTO(schoolService.getSchoolById(id));
    }

    @PostMapping
    public SchoolDTO saveSchool(@RequestBody School school)  {
        return  new SchoolDTO(schoolService.saveSchool(school));
    }

    @PutMapping
    public SchoolDTO updateSchool(@RequestBody School school) {
        return  new SchoolDTO(schoolService.saveSchool(school));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchool(@PathVariable("id") int id) throws NotFoundException{
        schoolService.deleteSchoolById(id);
    }

}

package com.verbitskiy.springboot_mvc.services;

import com.verbitskiy.springboot_mvc.dto.SchoolDTO;
import com.verbitskiy.springboot_mvc.entity.School;
import com.verbitskiy.springboot_mvc.repository.SchoolRepository;
import com.verbitskiy.springboot_mvc.services.interfaces.SchoolService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    @Override
    public School getSchoolById(int id) throws NotFoundException {
        return schoolRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find School by ID. Try another one"));
    }

    @Override
    @Transactional
    public void deleteSchoolById(int id) throws NotFoundException {
        schoolRepository.deleteById(schoolRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find School by ID. Try another one"))
                .getId());
    }

    @Override
    @Transactional
    public School saveSchool(School school) {
        return schoolRepository.save(school);
    }
}

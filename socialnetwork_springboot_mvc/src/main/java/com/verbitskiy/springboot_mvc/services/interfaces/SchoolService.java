package com.verbitskiy.springboot_mvc.services.interfaces;

import com.verbitskiy.springboot_mvc.dto.SchoolDTO;
import com.verbitskiy.springboot_mvc.entity.School;
import javassist.NotFoundException;

import java.util.List;

public interface SchoolService {

    List<School> getAllSchools();

    School getSchoolById(int id) throws NotFoundException;

    void deleteSchoolById(int id) throws NotFoundException;

    School saveSchool(School school);

 }

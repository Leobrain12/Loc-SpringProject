package com.Murc.Loc.Service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.Murc.Loc.Model.Vacancy;
import com.Murc.Loc.Repository.VacancyRepository;
import com.Murc.Loc.Service.VacancyService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Primary
public class InMemVacancyServiceIMPL implements VacancyService {

    private final VacancyRepository repository;

    @Override
    public List<Vacancy> findAllVacancy() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Vacancy saveVacancy(Vacancy newVacancy) {
        return repository.save(newVacancy);
    }

    @Transactional
    @Override
    public Vacancy updateVacancy(Vacancy vacancy, Long Id) {
        return repository.save(vacancy);
    }

    @Transactional
    @Override
    public void deleteVacancy(Long Id) {
        repository.deleteById(Id);
    }

    @Override
    public Vacancy findById(Long Id) {
        return repository.findByVacancyId(Id);
    }
    
}

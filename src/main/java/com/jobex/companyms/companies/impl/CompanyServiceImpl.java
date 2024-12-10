package com.jobex.companyms.companies.impl;


import com.jobex.companyms.companies.Company;
import com.jobex.companyms.companies.CompanyRepository;
import com.jobex.companyms.companies.CompanyService;
import com.jobex.companyms.companies.clients.ReviewClient;
import com.jobex.companyms.companies.dto.ReviewMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private ReviewClient reviewClient;
    public CompanyServiceImpl(CompanyRepository companyRepository, ReviewClient reviewClient) {
        this.companyRepository = companyRepository;
        this.reviewClient=reviewClient;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public boolean createCompany(Company company) {
        try{
            companyRepository.save(company);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateCompanyById(Long id, Company updatedCompany) {
        Optional<Company> companyOptional=companyRepository.findById(id);
        if(companyOptional.isPresent())
        {
            Company company = companyOptional.get();
            company.setCompanyName(updatedCompany.getCompanyName());
            company.setDescription(updatedCompany.getDescription());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if (companyRepository.existsById(id))
        {
            companyRepository.deleteById(id);
            return true;
        }
        else
            return false;
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        Company company = getCompanyById(reviewMessage.getCompanyId());

        double averageRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
        company.setRating(averageRating);
        companyRepository.save(company);
    }
}


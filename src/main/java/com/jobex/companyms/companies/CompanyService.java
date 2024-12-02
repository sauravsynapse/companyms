package com.jobex.companyms.companies;
import java.util.List;

public interface CompanyService {
    List<Company> findAll();

    boolean createCompany(Company company);

    Company getCompanyById(Long id);

    boolean updateCompanyById(Long id, Company company);

    boolean deleteCompanyById(Long id);
}

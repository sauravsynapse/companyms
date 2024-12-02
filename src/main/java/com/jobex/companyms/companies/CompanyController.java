package com.jobex.companyms.companies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAll()
    {
        return new ResponseEntity(companyService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company)
    {
        if(companyService.createCompany(company))
            return new ResponseEntity("Company Created Successfully",HttpStatus.CREATED);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id)
    {
        Company company = companyService.getCompanyById(id);
        if(company!=null)
            return new ResponseEntity(company,HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompanyById(@PathVariable Long id, @RequestBody Company company)
    {
        if(companyService.updateCompanyById(id,company))
            return new ResponseEntity("Company Updated Successfully",HttpStatus.CREATED);
        return new ResponseEntity("Company not found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id)
    {
        if(companyService.deleteCompanyById(id))
            return new ResponseEntity("Company Deleted Successfully",HttpStatus.CREATED);
        return new ResponseEntity("Company not found",HttpStatus.NOT_FOUND);
    }
}

package com.example.ads.repository;

import com.example.ads.Entity.Ad;
import com.example.ads.Entity.Categories;
import com.example.ads.Entity.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepo extends CrudRepository<Company,String>{
    Optional<Company> findByCompanyName(String companyName);

}

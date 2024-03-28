package com.example.ads.Service;

import com.example.ads.Dto.AdDto;
import com.example.ads.Dto.CompanyDto;
import com.example.ads.Entity.Ad;
import com.example.ads.Entity.Categories;
import com.example.ads.Entity.Company;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ads.repository.AdsRepo;
import com.example.ads.repository.CompanyRepo;

import java.util.*;

@Service
public class AdsService {
    @Autowired
    AdsRepo adsRepo;

    @Autowired
    CompanyRepo companyRepo;


    public List<Ad> getAllAd() {
        List<Ad> allAds = new ArrayList<>();
        adsRepo.findAll().forEach(allAds::add);
        return allAds;
    }


    public List<Company> getAllcompanies() {
        List<Company> allCompanies = new ArrayList<>();
        companyRepo.findAll().forEach(allCompanies::add);
        return allCompanies;
    }

    public List<Ad> getAdByCompanyId(String companyId) {
        Optional<Company> option = companyRepo.findById(companyId);
        if (option.isPresent()) {
            Company company = option.get();
            return company.getAds();
        } else {
            return null;
        }
    }

    public Ad addAd(AdDto adDto) {
        Ad adnew = new Ad();
        BeanUtils.copyProperties(adDto, adnew);
        Optional<Company> companyOptional = companyRepo.findByCompanyName(adDto.getCompanyName());
        Company company = companyOptional.get();
        adnew.setCompany(company);
        return adsRepo.save(adnew);
    }


    public Company addCompany(CompanyDto companyDto) {
        Company compnew = new Company();
        BeanUtils.copyProperties(companyDto, compnew);
        return companyRepo.save(compnew);
    }


    public Ad randomAd() {
        List<Ad> listAd = getAllAd();
        if (!listAd.isEmpty()) {
            int randomIndex = (int) (Math.random() * listAd.size());
            return listAd.get(randomIndex);
        } else
            return null;
    }

    public Ad getAdByCategory(String category) {
        List<Ad> options = adsRepo.findByCategory(category);
        if (!options.isEmpty()) {
            int random = (int) (Math.random() * options.size());
            return options.get(random);
        } else
            return null;
    }


//    public List<Ad> findAdsByCategories(List<Categories> categories) {
//        return adsRepo.findAdsByCategories(categories);
//    }
//    public Ad getAllCategory(){
//
//    }

}

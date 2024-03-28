package com.example.ads.controller;

import com.example.ads.Dto.AdDto;
import com.example.ads.Dto.CompanyDto;
import com.example.ads.Entity.Ad;
import com.example.ads.Entity.Categories;
import com.example.ads.Entity.Company;
import com.example.ads.Service.AdsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/ad")
public class AdsController {

    @Autowired
    private AdsService adsService;

//    @GetMapping("/ads")
//    public List<Ad> getAllAd(){
//        return adsService.getAllAd();
//    }
//
//    @GetMapping("/{companyId}")
//    public List<Ad> getAdByCompanyId(@PathVariable String companyId) {
//        return adsService.getAdByCompanyId(companyId);
//    }
//
//    @PostMapping("/add")
//    public Ad addAd(@RequestBody AdDto adDto){
//        return adsService.addAd(adDto);
//    }
//
//    @GetMapping("/randomad")
//    public Ad getRandomAd(){
//        return adsService.randomAd();
//    }
//
//    @PostMapping("/addCompany")
//    public Company addNewCompany(@RequestBody CompanyDto companyDto){
//        return adsService.addCompany(companyDto);
//    }

    public static AdDto adToAdDto(Ad ad,Company company){
        AdDto updatedAdDto = new AdDto();
        BeanUtils.copyProperties(ad,updatedAdDto);
        if (company!=null) {
            updatedAdDto.setCompanyId(company.getCompanyId());
            updatedAdDto.setCompanyName(company.getCompanyName());
        }
        return updatedAdDto;
    }

    public static CompanyDto companyToCompanyDto(Company company){
        CompanyDto updatedCompanyDto = new CompanyDto();
        BeanUtils.copyProperties(company,updatedCompanyDto);
        return updatedCompanyDto;
    }

    @GetMapping("/getAllAds")
    public ResponseEntity<List<AdDto>> getAllAd() {
        try {
            List<Ad> allAds = adsService.getAllAd();
            List<AdDto> allAdReturn = new ArrayList<>();
            for(Ad ars: allAds){
                allAdReturn.add(adToAdDto(ars,ars.getCompany()));
            }
            return ResponseEntity.status(HttpStatus.OK).body(allAdReturn);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAllCompanies")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        try {
            List<Company> allCompanies = adsService.getAllcompanies();
            List<CompanyDto> allCompanyReturn = new ArrayList<>();
            for(Company ars: allCompanies){
                allCompanyReturn.add(companyToCompanyDto(ars));
            }
            return ResponseEntity.status(HttpStatus.OK).body(allCompanyReturn);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<List<AdDto>> getAdByCompanyId(@PathVariable String companyId) {
        try{
            List<Ad> adbycomp = adsService.getAdByCompanyId(companyId);
//            AdDto adreturn = adToAdDto((companyId));
//            List<Course> allCourseList = courseService.getAllCourse();
            List<AdDto> adreturn = new ArrayList<>();
            for (Ad ad : adbycomp) {
                adreturn.add(adToAdDto(ad,ad.getCompany()));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(adreturn);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/addAd")
    public ResponseEntity<AdDto> addAd(@RequestBody AdDto adDto) {
        try{
            Ad ad = adsService.addAd(adDto);
//            AdDto adDtoReturn = adToAdDto(ad,ad.getCompany());
            return ResponseEntity.status(HttpStatus.CREATED).body(adDto);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/getAd")
    public ResponseEntity<AdDto> getRandomAd(@RequestParam String userId) {
        try {
            Ad randmAd = adsService.randomAd();
            AdDto adToReturn = adToAdDto(randmAd,randmAd.getCompany());
            return ResponseEntity.ok(adToReturn);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/addCompany")
    public ResponseEntity<CompanyDto> addNewCompany(@RequestBody CompanyDto companyDto) {
        try {
//            if(companyDto.getCompanyName()==null){
//                return null;

            Company company = adsService.addCompany(companyDto);
            CompanyDto companyDtoReturn = companyToCompanyDto(company);
            return ResponseEntity.status(HttpStatus.CREATED).body(companyDtoReturn);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/adByCategory/{category}")
    public ResponseEntity<AdDto> getAdByCategory(@PathVariable String category){

        try{
            Ad adsByCategory = adsService.getAdByCategory(category);
            AdDto adcategory = adToAdDto(adsByCategory,adsByCategory.getCompany());
            return ResponseEntity.status(HttpStatus.OK).body(adcategory);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("/categories")
    public List<Categories> getAllCategories(){
        return Arrays.asList(Categories.values());
    }
//
//    @GetMapping("/multiple")
//    public List<Ad> getAdByMultipleCategories(@RequestBody List<String> category){
//        List<Ad> adlis = adsService.findAdsByCategories(category);
//        return adlis;
//    }



}

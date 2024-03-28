package com.example.ads.repository;

import com.example.ads.Entity.Ad;
import com.example.ads.Entity.Categories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdsRepo extends CrudRepository<Ad,String> {

    List<Ad> findByCategory(String category);


}

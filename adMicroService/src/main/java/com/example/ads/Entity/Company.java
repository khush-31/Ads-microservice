package com.example.ads.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "Company")
public class Company {



    public static final String SEQ_GEN_ALIAS = "seq_gen_alias";


    @Id
    private String companyId = UUID.randomUUID().toString();
    private String companyName;

//    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Ad> ads;
}

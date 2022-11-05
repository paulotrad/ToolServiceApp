package com.toolapp.controller;

import com.toolapp.entity.Company;
import com.toolapp.repos.CompanyRepository;
import com.toolapp.repos.PartRepository;
import com.toolapp.service.PartGetScrape;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.sqlite.SQLiteException;

import java.util.List;


@Controller
public class Part {


    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    PartRepository partRepository;

    @Autowired
    PartGetScrape partGetScrape;



    @GetMapping("/getIndPartInfo")

    public String getIndPartInfo(@RequestParam String name, Model model){
       //takes in part number and makes a part out of JSoup Octopart query

        com.toolapp.entity.Part part = partGetScrape.getPartInfo(name);


        System.out.println("Need to be added:"+ String.valueOf(partRepository.findByName(name.toUpperCase())==null));
        if (partRepository.findByName(name.toUpperCase())==null) {

            try {
                partRepository.save(part);
                System.out.println(part.getName()+" saved in local database");
            } catch (JpaSystemException e) {

            }
        }else {
            //part=partRepository.findByName(part.getName());

        }




        //display companylist  based off pure connection
        List<Company> company=partGetScrape.getDistributors(partGetScrape.getPartInfo(name));
        model.addAttribute("companyList",company);
        model.addAttribute("part",part);

        return "partinfo";

    }



    }


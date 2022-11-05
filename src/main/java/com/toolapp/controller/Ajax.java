package com.toolapp.controller;

import com.toolapp.entity.Part;
import com.toolapp.repos.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Ajax {
    @Autowired
    PartRepository partRepository;

    @GetMapping("/updateList")
    public List<Part> getSimliarPart(@RequestParam String name, Model model) {
        System.out.println(name);
        System.out.println(name+" "+partRepository.findByNameLike(name));


        return partRepository.findByNameLike(name);
    }

}
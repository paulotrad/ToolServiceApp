package com.toolapp.controller;

import com.toolapp.repos.PartRepository;
import com.toolapp.service.FileParser;
import com.toolapp.entity.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;

@Controller
public class Additional {

    @Autowired
    PartRepository partRepository;

    @GetMapping("/setLoc")
    public String setLoc(@RequestParam String name, @RequestParam String description) {
        Part part = partRepository.findByName(name);
        part.setDescription(description);
        partRepository.save(part);
        System.out.println(part);
        return "ok";
    }



    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, Model model) throws IOException {

        model.addAttribute("partNumbers",FileParser.getPartNumbers(file));

        return "home";
    }}


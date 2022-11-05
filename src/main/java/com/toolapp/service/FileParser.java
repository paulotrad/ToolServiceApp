package com.toolapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileParser {

    /**
     * @param file MultipartFile required for parsing
     * creates Mapping of columns
     */
    public static List<String> getPartNumbers(MultipartFile file) throws IOException {
        List<String> partNumber=new ArrayList<>();
        System.out.println(file.getName()+ " "+file.getOriginalFilename());

        String content = new String(file.getBytes(), StandardCharsets.UTF_8);


        String [] fileLine=content.split("\n");
        Pattern pattern=Pattern.compile("[A-Z]\\s +[a-zA-Z0-9]{4}[a-zA-Z0-9]\\s([^\\s:]+\\s?[A-Z]?[A-Z]?[A-Z]?[A-Z]?[^ ]+)");
        Matcher matcher;

        PartGetScrape partGetScrape=new PartGetScrape();
        for (String free:fileLine) {

            if (free.length() > 0) {


                matcher=pattern.matcher(free);
                if (matcher.find()){
                    //System.out.println(partGetScrape.getPartInfo(matcher.group(1)));
                    System.out.println(matcher.group(1));

                        partNumber.add(matcher.group(1).strip());

                }
            }
        }
        System.out.println(partNumber);
        return partNumber;
    }
}

package com.toolapp.service;

import com.toolapp.entity.Company;
import com.toolapp.entity.Part;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PartGetScrape {
    Document doc;
    Elements newsHeadlines;
    String partNumber;
    List<Company> companyList;
    private Logger logger= LoggerFactory.getLogger(PartGetScrape.class);
    public PartGetScrape() {




    }
    public Part getPartInfo(String partNumber){
        logger.info("starting PArt grab");
        List<String> description =new ArrayList<>();
       //octoparts requires ++ instead of %20 for empty spaces
        this.partNumber=partNumber.replace(" ","++");
        //octoparts requires %2F instead of /
        this.partNumber=partNumber.replace("/","%2F");

        try {
                    doc = Jsoup.connect("https://octopart.com/search?q="+partNumber).userAgent("Mozilla")
                    .header("Accept", "text/html")
                    .header("Accept-Encoding", "gzip,deflate")
                    .header("Accept-Language", "it-IT,en;q=0.8,en-US;q=0.6,de;q=0.4,it;q=0.2,es;q=0.2")
                    .header("Connection", "keep-alive")
                    .ignoreContentType(true).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String part=doc.select("div.jsx-2172888034").get(0).select("div.jsx-3355510592").select("a").attr("href");

        try {
            doc = Jsoup.connect("https://octopart.com"+part).userAgent("Mozilla")
                    .header("Accept", "text/html")
                    .header("Accept-Encoding", "gzip,deflate")
                    .header("Accept-Language", "it-IT,en;q=0.8,en-US;q=0.6,de;q=0.4,it;q=0.2,es;q=0.2")
                    .header("Connection", "keep-alive")
                    .ignoreContentType(true).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String name=doc.select("div.jsx-3792447260").get(0).text();



       double avgCost;
        try {
            avgCost = Double.parseDouble(doc.select("div.jsx-496850923.price  > span.jsx-496850923.price").get(0).text().replace(",","."));


        }
        catch (Exception e)
        {
           avgCost =0.0;
        }

        String imgurl=doc.select("#__next > div.jsx-141717410.pdp > div > div.jsx-1075674400.header > div.jsx-1075674400.middle > div > button > div > img").attr("src");

        doc.select("#__next > div.jsx-141717410.pdp > div.jsx-141717410.part > div" ).forEach(element -> {
            if (element.select("h3").text().equals("Descriptions")){

               description.add(element.select("div.jsx-3114885901").get(0).text());
            }
        });



        return new Part(name,description.get(0),avgCost,imgurl);


        }

    private String parse(String partNumber) {

        return partNumber.replace(" ","++");
    }


    public List<Company> getDistributors(Part part) {
        companyList = new ArrayList<>();

        for (Element element : doc.select("#__next > div.jsx-141717410.pdp > div.jsx-141717410.part > div.jsx-712130013.jsx-2269260994.pdp-section.full-width > div.jsx-90419769.offers-tables > table:nth-child(2) > tbody")) {
            element.select("tr").forEach(element1 -> {
                Company company;
                //System.out.println(element1.siblingIndex());

                company = new Company(element1.select("a").get(1).text(),
                        element1.select("td.jsx-3355150991 > a").text(),
                        element1.select("a").get(4).attr("href"), part);

                try {
                    company.setCost(String.valueOf(Double.valueOf(element1.select("a").get(6).text())));
                    companyList.add(company);

                } catch (Exception e) {
                    company.setCost("0");
                    companyList.add(company);

                }


            });

        }

        return companyList;

    }
    public String getAssocTools(){
        ArrayList<String> link1 =new ArrayList();
        companyList.forEach(e->{
            try {
                switch (e.getName()){
                    case "Newark":
                        System.out.println("Newark");

                        doc=Jsoup.connect("https://www.newark.com/webapp/wcs/stores/servlet/AjaxSearchLookAhead?storeId=10194&catalogId=15003&langId=-1&searchTerm="+e.getPart().getName()).get();
                        link1.add(doc.select("a").attr("href")+"#techDocsHook");

                        break;
                }


            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });


        return link1.get(0);
    }

}

package com.toolapp.entity;

import javax.persistence.*;
import java.util.Set;

@Entity

public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column
    String name;

    @Column
    String description;
    @Column
    double avgCost;

    @Column
    String shopLocation;



    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Part(String name, String description, double avgCost, String imgUrl){
        this.name=name;
        this.description=description;
        this.avgCost=avgCost;
        this.imgUrl=imgUrl;
    }
    protected Part(){

    }
    @Column
    String imgUrl;

    public long getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAvgCost() {
        return avgCost;
    }

    public void setAvgCost(double avgCost) {
        this.avgCost = avgCost;
    }

    public String getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
    }



    @OneToMany(mappedBy = "part")
    private Set<Company> company = new java.util.LinkedHashSet<>();

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", avgCost=" + avgCost +
                ", imgUrl='" + imgUrl + '\'' +
                ", shopLocation='" + shopLocation + '\'' +
                ", company=" + company +
                '}';
    }



    public Set<Company> getCompany() {
        return company;
    }

    public void setCompany(Set<Company> company) {
        this.company = company;
    }


}

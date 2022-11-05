package com.toolapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;

    @Column
    private String stock;

    @Column
    private String cost="0";


    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", part=" + part +
                ", stock=" + stock +
                ", cost=" + cost +
                '}';
    }

    public Company(String name, String stock,String url, Part part){
        this.part=part;
        this.url=url;
        this.name=name;
        this.stock=stock;
        this.cost="0.0";

    }
    public Company(){

    }

    public Long getId() {
        return id;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {

        this.part = part;
    }
}
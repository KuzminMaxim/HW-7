package com.example.hw7.model;

import javax.persistence.*;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "district")
    private String district;

    @Column(name = "comission_percent")
    private int commissionPercent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getCommissionPercent() {
        return commissionPercent;
    }

    public void setCommissionPercent(int commissionPercent) {
        this.commissionPercent = commissionPercent;
    }
}

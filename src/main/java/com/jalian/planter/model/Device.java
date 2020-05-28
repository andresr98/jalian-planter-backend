package com.jalian.planter.model;

import javax.persistence.*;

@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, name="name")
    private String name;

    protected Device () {}

    public Device (String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.jalian.planter.model;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {

    @Id
    private int id;
    @Column(nullable = false)
    private String value;
    @Column(nullable = false)
    //date
    private int created_date;
    //@ForeignKey
    private int pot_device_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

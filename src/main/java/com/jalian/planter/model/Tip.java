package com.jalian.planter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "tip")
public class Tip {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, name = "message")
    private String message;

    protected Tip () {}

    public Tip(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

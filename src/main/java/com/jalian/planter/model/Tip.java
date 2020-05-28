package com.jalian.planter.model;

import javax.persistence.*;

@Entity
@Table(name = "tip")
public class Tip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

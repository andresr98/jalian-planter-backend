package com.jalian.planter.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, name = "value")
    private String value;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @ManyToOne
    private PotDevice potDevice;

    protected Message () {}

    public Message(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public PotDevice getPotDevice() {
        return potDevice;
    }

    public void setPotDevice(PotDevice potDevice) {
        this.potDevice = potDevice;
    }
}

package com.jalian.planter.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, name = "value")
    private int value;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @ManyToOne
    private PotDevice potDevice;

    protected Message () {}

    public Message(int value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
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

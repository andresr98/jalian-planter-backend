package com.jalian.planter.model;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pot_device")
public class PotDevice {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, name = "status")
    private boolean status;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    private Pot pot;

    @ManyToOne
    private Device device;

    @OneToMany(mappedBy = "potDevice")
    private List<Message> messages = new ArrayList<Message>();

    protected PotDevice () {}

    public PotDevice(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Pot getPot() {
        return pot;
    }

    public void setPot(Pot pot) {
        this.pot = pot;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<Message> getMessages () {
        return this.messages;
    }

    public void setMessage (Message message) {
        this.messages.add(message);
    }

    public void removeMessage (Message message) {
        this.messages.remove(message);
    }
}

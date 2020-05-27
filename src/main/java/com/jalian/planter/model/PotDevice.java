package com.jalian.planter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pot_device")
public class PotDevice {

    @Id
    private int id;
    @Column(nullable = false)
    private boolean status;
    @Column(nullable = false)
    //date
    private int updated_at;
    //@ForeignKey
    private int pot_id;
    //@ForeignKey
    private int device_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

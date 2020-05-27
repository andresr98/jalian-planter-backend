package com.jalian.planter.model;

import javax.persistence.*;

@Entity
@Table(name = "pot")
public class Pot {

    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String type;
    //@ForeignKey(ConstraintMode.CONSTRAINT)
    private int user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

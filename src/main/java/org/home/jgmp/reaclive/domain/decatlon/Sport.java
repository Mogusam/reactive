package org.home.jgmp.reaclive.domain.decatlon;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Sport implements Serializable {
    public Sport(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Sport() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column
    @org.springframework.data.annotation.Id
    private Integer id;
    @Column
    private String name;

    @Override
    public String toString() {
        return "Sport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

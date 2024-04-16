package com.tn.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String schoolName;

    private String address;

    private int numTeacher;

    public static void main(String[] args) {
        School school = new School();
    }

}

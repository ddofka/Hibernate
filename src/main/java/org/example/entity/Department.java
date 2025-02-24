package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Department {

    @Id
    private Integer id;
    private String department;
    @OneToMany(mappedBy = "dep", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Worker> workers = new ArrayList<>();

}

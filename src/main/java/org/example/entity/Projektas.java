package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Projektas {

    @Id
    private Integer id;
    private String projektas;
    @OneToMany(mappedBy = "projektas", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Darbuotojas> darbuotojai = new ArrayList<>();
}

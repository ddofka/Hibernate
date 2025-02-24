package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Darbuotojas {

    @Id
    private Long personalCode;
    private String name;
    private String lastName;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Skyrius dep;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Projektas projektas;
}

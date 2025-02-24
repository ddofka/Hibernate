package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class Worker {

    @Id
    private Long personalCode;
    private String name;
    private String lastName;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Department dep;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Project project;
}

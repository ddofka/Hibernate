package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Project {

    @Id
    private Integer id;
    private String project;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Worker> workers = new ArrayList<>();

    @Override
    public String toString() {
        return "Project{id=" + id + ", project='" + project + "'}";
        // DON'T include references to collections of other entities here
    }
}

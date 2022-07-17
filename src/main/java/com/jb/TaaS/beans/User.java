package com.jb.TaaS.beans;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String email;

    private String password;
    @Enumerated(EnumType.STRING)
    private ClientType type;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    @Singular
    @JsonManagedReference
    private List<Task> tasks = new ArrayList<>();
}

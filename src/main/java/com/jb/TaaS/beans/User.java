package com.jb.TaaS.beans;

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
    //@Column(unique = true)
    // TODO: 22/05/2022 unique: does it interfere?
    private String email;

    private String password;
    @Enumerated(EnumType.STRING)
    private ClientType type;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    @Singular
    private List<Task> tasks = new ArrayList<>();
}

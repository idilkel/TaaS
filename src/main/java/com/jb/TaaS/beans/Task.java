package com.jb.TaaS.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, name = "task_group")
    private String group;

    @Column(name = "task_time")
    private Timestamp when;

    @ManyToOne
    @ToString.Exclude
    @JsonBackReference
    private User user;
}

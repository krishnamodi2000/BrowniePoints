package com.GRP3.BPA.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="student")
@AllArgsConstructor
@NoArgsConstructor


public class Student {
    @Id
    @Getter @Setter
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    private String banner_id;
    @Getter @Setter
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}

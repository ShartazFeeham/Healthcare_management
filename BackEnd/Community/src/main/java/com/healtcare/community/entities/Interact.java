package com.healtcare.community.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Interact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer interactId;
    private String userId;
    private Integer type;
}

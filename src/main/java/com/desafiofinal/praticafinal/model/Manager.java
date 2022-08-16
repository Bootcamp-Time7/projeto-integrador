

package com.desafiofinal.praticafinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long managerId;
    private String managerName;

    @OneToOne (mappedBy = "manager", cascade = CascadeType.REFRESH)
    @JsonIgnore

    private WareHouse wareHouse;
}
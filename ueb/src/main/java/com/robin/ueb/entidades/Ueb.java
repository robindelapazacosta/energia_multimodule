package com.robin.ueb.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ueb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idueb;

    String nombreueb;




}

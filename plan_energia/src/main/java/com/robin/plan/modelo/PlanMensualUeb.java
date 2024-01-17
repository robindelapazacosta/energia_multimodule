package com.robin.plan.modelo;


import lombok.*;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"idueb","anno","mes"})})
public class PlanMensualUeb {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long id;

   @MapsId("anno")
   private int anno;

   @MapsId("mes")
   private int mes;

   @MapsId("idueb")
   private Integer idueb;

   private Long plan;
   private Long plan_ajustado;

   String detalles;





}

package com.robin.consumo_plan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DtoCumplimPlan {


        private int anno_omes;
        private Long plan;
        private Long plan_ajustado;
        private Long consumo;


        public Float getPorcGasto() {

            Float porc=   Float.parseFloat(consumo.toString()) / plan_ajustado * 100;
            return porc;
        }

}

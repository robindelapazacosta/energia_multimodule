package com.robin.plan.repository;


import com.robin.common_dto.dto.DtoMesPlan;
import com.robin.common_dto.dto.DtoPlanMensualUeb;
import com.robin.plan.modelo.PlanMensualUeb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanMensualRepository extends JpaRepository<PlanMensualUeb,Long> {


@Query("select plan from  PlanMensualUeb plan where plan.anno=?1 AND  plan.mes=?2")
public List<PlanMensualUeb> listarPlanMesUebs(int anno, int mes);

/*Plan de una UEB por meses dado el año*/
@Query("select new com.robin.common_dto.dto.DtoMesPlan(plan.idueb, plan.mes, plan.plan,plan.plan_ajustado) " +
        "from PlanMensualUeb plan where plan.idueb=?1 AND  plan.anno=?2" )
public List<DtoMesPlan>  planUebPorMeses(int idueb, int anno);







}

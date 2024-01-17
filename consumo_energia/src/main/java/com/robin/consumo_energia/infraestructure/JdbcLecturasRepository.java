package com.robin.consumo_energia.infraestructure;

import com.robin.common_dto.dto.DtoGastoDiario;
import com.robin.common_dto.dto.DtoGastoTotalUEB;
import com.robin.common_dto.dto.DtoConsumoUEBMes;
import com.robin.consumo_energia.domain.model.Lectura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class JdbcLecturasRepository extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void Initialize(){ setDataSource(dataSource);  }

    public void save(Lectura lectura)
    {
        String sql= "INSERT INTO lectura(pico, dia, madrugada, fecha, idcontador, general)  VALUES (?, ?, ?, ?, ?, ?)";
        getJdbcTemplate().update(sql,new Object[]{lectura.getPico(), lectura.getDia(), lectura.getMadrugada(),
                                     lectura.getFecha(), lectura.getIdcontador(), lectura.getGeneral()}    );
    }

    public Lectura getUltimaLectura(Integer idcontador)
    {
        String sql="select * from lectura where idcontador=? order by fecha desc limit 1";
        return (Lectura)getJdbcTemplate().queryForObject(sql, new Object[]{idcontador}, new RowMapper<Lectura>() {
            @Override
            public Lectura mapRow(ResultSet rs, int rowNum) throws SQLException {
                Lectura lectura= new Lectura();
                lectura.setFecha(rs.getDate("fecha"));
                lectura.setDia(rs.getInt("dia"));
                lectura.setPico(rs.getInt("pico"));
                lectura.setMadrugada(rs.getInt("madrugada"));
                lectura.setGeneral(rs.getInt("general"));
                lectura.setIdcontador(rs.getInt("idcontador"));

                return lectura;
            }
        });

    }

    /*Depende la vista */
     public List<DtoGastoDiario> listGastoDiarioContador(Integer idcontador, int anno, int mes)
     {
         String sql= "select date_part('dow',fecha) as diasemana, \n" +
                 "fecha,pico,realpico,\n" +
                 "dia,realdia,\n" +
                 "madrugada,realmadrugada,\n" +
                 "general,total_dia\n" +
                 "from vconsumodiario where date_part('year',fecha)="+anno+" AND date_part('month', fecha)="+mes+" AND idcontador="+idcontador;

         List<Map<String,Object>> rows= getJdbcTemplate().queryForList(sql);

         List<DtoGastoDiario> lgastodiario= new ArrayList<DtoGastoDiario>();
         for(Map<String,Object> row:rows)
         {
             DtoGastoDiario dtoGastoDiario= new DtoGastoDiario();
             dtoGastoDiario.setDiadelasema(row.get("diasemana").toString());
             dtoGastoDiario.setFecha(row.get("fecha").toString());
             dtoGastoDiario.setLecturapico((Integer) row.get("pico"));
             dtoGastoDiario.setGastopico((Integer) row.get("realpico"));
             dtoGastoDiario.setLecturadia((Integer) row.get("dia"));
             dtoGastoDiario.setGastodia((Integer) row.get("realdia"));
             dtoGastoDiario.setLecturamadrugada((Integer)row.get("madrugada"));
             dtoGastoDiario.setGastomadrugada((Integer)row.get("realmadrugada") );
             dtoGastoDiario.setLecturagral((Integer)row.get("general"));
             dtoGastoDiario.setConsumototal((Integer)row.get("total_dia"));

             lgastodiario.add(dtoGastoDiario);
         }


         return lgastodiario;

     }

    public List<DtoGastoDiario> listGastoDiarioUEB(Integer idueb, int anno, int mes)
    {
       String sql="select date_part('dow',fecha) as diasemana, fecha, cast(SUM(realpico) as Integer) as realpico, cast(SUM(realdia)as Integer)  as realdia, \n" +
               "cast(SUM(realmadrugada) as Integer) as realmadrugada, cast(SUM(total_dia) as Integer) as total_dia\n" +
               " from vconsumodiario where idueb="+idueb+" and anno="+anno+" and mes="+mes+" group by date_part('dow',fecha),fecha\n" +
               "order by fecha";

        List<DtoGastoDiario> lgasto= new ArrayList<>();
       List<Map<String, Object>> rows= getJdbcTemplate().queryForList(sql);
       for(Map<String,Object> row:rows)
       {
           DtoGastoDiario dtoGastoDiario= new DtoGastoDiario();
           dtoGastoDiario.setDiadelasema(row.get("diasemana").toString());
           dtoGastoDiario.setFecha(row.get("fecha").toString());
           dtoGastoDiario.setGastopico((Integer) row.get("realpico"));
           dtoGastoDiario.setGastodia((Integer) row.get("realdia"));
           dtoGastoDiario.setGastomadrugada((Integer) row.get("realmadrugada"));
           dtoGastoDiario.setConsumototal((Integer) row.get("total_dia"));


           lgasto.add(dtoGastoDiario);

       }

       return lgasto;
    }

    public List<DtoGastoDiario> listGastoDiarioEmpresa(int anno, int mes)
    {
        String sql= "select date_part('dow',fecha) as diasemana, fecha, cast(SUM(realpico) as Integer) as realpico, cast(SUM(realdia)as Integer)  as realdia,\n" +
                "               cast(SUM(realmadrugada) as Integer) as realmadrugada, cast(SUM(total_dia) as Integer) as totaldia\n" +
                "               from vconsumodiario where anno=? and mes=? group by date_part('dow',fecha),fecha order by fecha";

        List<Map<String, Object>> rows= getJdbcTemplate().queryForList(sql, new Object[]{anno,mes});
        List<DtoGastoDiario> lgasto= new ArrayList<>();

        for(Map<String,Object> row:rows)
        {
            DtoGastoDiario dtoGastoDiario= new DtoGastoDiario();
            dtoGastoDiario.setDiadelasema(row.get("diasemana").toString());
            dtoGastoDiario.setFecha(row.get("fecha").toString());
            dtoGastoDiario.setGastopico((Integer) row.get("realpico"));
            dtoGastoDiario.setGastodia((Integer) row.get("realdia"));
            dtoGastoDiario.setGastomadrugada((Integer) row.get("realmadrugada"));
            dtoGastoDiario.setConsumototal((Integer) row.get("totaldia"));
            lgasto.add(dtoGastoDiario);
        }

        return lgasto;
    }

    public List<DtoGastoTotalUEB> listGastoTotalUeb(int anno, int mes)
    {
        String sql="select idueb, SUM(total_dia) as gastototal from vconsumodiario where anno=? and mes=? group by idueb";
        List< Map<String,Object>> rows= getJdbcTemplate().queryForList(sql, new Object[]{anno,mes});

        List<DtoGastoTotalUEB> lgasto= new ArrayList<>();
        for(Map<String,Object> row:rows)
        {
            DtoGastoTotalUEB dtogasto= new DtoGastoTotalUEB();
            dtogasto.setIdueb((Integer)row.get("idueb"));
            dtogasto.setGastototal((Long)row.get("gastototal"));

            lgasto.add(dtogasto);
        }

        return lgasto;

    }

    /*Gasto mensual UEB dado el año y la ueb*/
    public List<DtoConsumoUEBMes> listConsumoMensualUeb(int anno, int idueb)
    {

        String sql="select idueb, cast(mes as int), SUM(total_dia) as consumo\n" +
                "from vconsumodiario\n" +
                "where anno= ? and idueb=?\n" +
                "group by idueb, mes";


        List<Map<String,Object>> rows= getJdbcTemplate().queryForList(sql, new Object[]{anno,idueb});
        List<DtoConsumoUEBMes> lconsumo=new ArrayList<>();
        for(Map<String,Object> row:rows )
        {
            DtoConsumoUEBMes dtoConsumo= new DtoConsumoUEBMes();
            dtoConsumo.setIdueb((Integer) row.get("idueb"));
            dtoConsumo.setMes((Integer)row.get("mes"));
            dtoConsumo.setConsumo((Long)row.get("consumo"));

            lconsumo.add(dtoConsumo);
        }

        return lconsumo;
    }





}

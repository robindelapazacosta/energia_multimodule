package com.robin.consumo_energia.infraestructure;


import com.robin.consumo_energia.domain.model.Contador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class JdbcContadoresRepository extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void Initialize() {
        setDataSource(dataSource);
    }

    public void save(Contador contador)
    {
        String sql="INSERT INTO contador(nombre_area, idueb) VALUES (?, ?)";
        getJdbcTemplate().update(sql, new Object[]{contador.getNombre_area(), contador.getIdueb()});
    }

    public List<Contador> listContadores(Integer idueb)
    {
        String sql="select*from contador";
        if(idueb!=0)
        {
            sql+=" where idueb="+idueb;
        }

        List<Contador> lcontadores= new ArrayList<>();
        List<Map<String,Object>> rows= getJdbcTemplate().queryForList(sql);

        for(Map<String,Object> row: rows)
        {
            Contador contador= new Contador();
            contador.setIdcontador((Integer) row.get("idcontador"));
            contador.setNombre_area(row.get("nombre_area").toString());
            contador.setIdueb((Integer) row.get("idueb"));
            lcontadores.add(contador);
        }

        return lcontadores;
    }


}

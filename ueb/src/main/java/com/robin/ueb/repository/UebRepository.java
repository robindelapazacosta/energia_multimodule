package com.robin.ueb.repository;



import com.robin.ueb.entidades.Ueb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UebRepository extends JpaRepository<Ueb,Long> {
}

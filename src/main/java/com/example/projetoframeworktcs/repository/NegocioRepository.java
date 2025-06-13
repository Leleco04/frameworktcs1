package com.example.projetoframeworktcs.repository;

import com.example.projetoframeworktcs.model.Negocio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegocioRepository extends JpaRepository<Negocio, Long> {
}

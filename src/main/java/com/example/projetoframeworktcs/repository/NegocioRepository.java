package com.example.projetoframeworktcs.repository;

import com.example.projetoframeworktcs.model.Negocio;
import com.example.projetoframeworktcs.model.enums.Status;
import com.example.projetoframeworktcs.model.enums.TipoNegocio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NegocioRepository extends JpaRepository<Negocio, Long> {
    public List<Negocio> findByTipo(TipoNegocio tipo);
    public List<Negocio> findByStatus(Status status);
}

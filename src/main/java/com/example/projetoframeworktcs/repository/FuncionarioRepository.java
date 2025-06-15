package com.example.projetoframeworktcs.repository;

import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.model.Transportadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("SELECT f FROM Funcionario f JOIN FETCH f.setor")
    List<Funcionario> findAllWithSetor();

}

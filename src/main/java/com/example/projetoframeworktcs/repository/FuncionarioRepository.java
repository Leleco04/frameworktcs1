package com.example.projetoframeworktcs.repository;

import com.example.projetoframeworktcs.model.Funcionario;
import com.example.projetoframeworktcs.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    @Query("SELECT f FROM Funcionario f JOIN FETCH f.setor")
    List<Funcionario> findAllWithSetor();

    List<Funcionario> findBySetor(Setor setor);
}

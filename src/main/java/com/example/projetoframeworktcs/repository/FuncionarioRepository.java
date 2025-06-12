package com.example.projetoframeworktcs.repository;

import com.example.projetoframeworktcs.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}

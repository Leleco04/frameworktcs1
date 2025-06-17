package com.example.projetoframeworktcs.repository;

import com.example.projetoframeworktcs.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}

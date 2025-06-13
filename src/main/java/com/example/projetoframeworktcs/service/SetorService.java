package com.example.projetoframeworktcs.service;

import com.example.projetoframeworktcs.model.Setor;
import com.example.projetoframeworktcs.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;

    public List<Setor> getSetores() {
        return setorRepository.findAll();
    }

}

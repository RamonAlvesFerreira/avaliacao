package com.rd.treinamentodev.AvaliacaoSpringBoot.service;

import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.AlunoDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.CursoDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.ResultData;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.CursoEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.PushBuilder;
import java.util.List;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public ResponseEntity salvarAlunoDTO (CursoDTO curso){
        CursoEntity entity = new CursoEntity();

        entity.setNomeCurso(curso.getNome());
        entity.setNrCargaHoraria(curso.getCargaHoraria());

        entity = cursoRepository.save(entity);

        ResultData resultData = new ResultData(HttpStatus.CREATED.value(), "Curso cadastrado", entity.getIdCurso());
        return ResponseEntity.status(HttpStatus.CREATED).body(resultData);

    }

    public List<CursoEntity> buscarPorNome(String nome){

        return cursoRepository.findByNomeCurso(nome);

    }

}

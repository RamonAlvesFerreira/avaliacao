package com.rd.treinamentodev.AvaliacaoSpringBoot.service;

import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.AlunoDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.ResultData;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.AlunoEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;


    public ResponseEntity gravar(AlunoDTO alunoDTO){
        //TODO validar se o CPF existe no banco antes de existir, caso exista retornar mensagem de erro

        AlunoEntity entity = new AlunoEntity();

        entity.setNomeAluno((alunoDTO.getNome()));
        entity.setCpf((alunoDTO.getCpf()));

        List<AlunoEntity> listAluno = alunoRepository.findAll();

        for(AlunoEntity entidadeFor : listAluno){
            if(entidadeFor.getCpf().equals(entity.getCpf()) ){
                ResultData resultData = new ResultData(HttpStatus.BAD_REQUEST.value(), "O cpf do aluno já esta cadastrado", entity.getIdAluno());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
            }else {

            }
        }

        entity = alunoRepository.save(entity);

        ResultData resultData = new ResultData(HttpStatus.CREATED.value(), "Aluno cadastrado com sucesso", entity.getIdAluno());
        return ResponseEntity.status(HttpStatus.CREATED).body(resultData);
    }
}

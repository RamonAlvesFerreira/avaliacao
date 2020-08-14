package com.rd.treinamentodev.AvaliacaoSpringBoot.service;

import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.AlunoDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.CursoDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.InstrutorDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.dto.TurmaDTO;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.AlunoEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.CursoEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.InstrutorEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.model.entity.TurmaEntity;
import com.rd.treinamentodev.AvaliacaoSpringBoot.repository.CursoRepository;
import com.rd.treinamentodev.AvaliacaoSpringBoot.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    public List<TurmaDTO> listar(){
        //TODO implementar a conversão da lista de objetos de TurmaEntity para TurmaDTO e retornar a listDTO preenchida

        List<TurmaEntity> listEntity = turmaRepository.findAll();
        List<TurmaDTO> listDTO = new ArrayList<>();

        for(TurmaEntity turma : listEntity){
            TurmaDTO dto = new TurmaDTO();
            CursoDTO curso  = new CursoDTO();

            CursoDTO cursoDaTurma = new CursoDTO();

            cursoDaTurma.setNome(turma.getCurso().getNomeCurso());
            cursoDaTurma.setCargaHoraria(turma.getCurso().getNrCargaHoraria());

            dto.setCurso(cursoDaTurma);

            dto.setDtInicio(SDF.format(turma.getDtFinal()));
            dto.setDtFim(SDF.format(turma.getDtFinal()));

            List<InstrutorDTO> instrutores = new ArrayList<>();

            for(InstrutorEntity instrutor : turma.getInstrutores()){
                InstrutorDTO instruDTO = new InstrutorDTO();

                instruDTO.setNome(instrutor.getNomeInstrutor());
                instruDTO.setValorHora(instrutor.getValorHora());

                instrutores.add(instruDTO);
            }
            dto.setInstrutores(instrutores);

            List<AlunoDTO> alunos = new ArrayList<>();

            for(AlunoEntity aluno : turma.getAlunos()){
                AlunoDTO aluDTO = new AlunoDTO();

                aluDTO.setNome(aluno.getNomeAluno());
                aluDTO.setCpf(aluno.getCpf());

                alunos.add(aluDTO);
            }
            dto.setAlunos(alunos);

            listDTO.add(dto);
        }

        return listDTO;
    }
}

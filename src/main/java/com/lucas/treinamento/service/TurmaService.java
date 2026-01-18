package com.lucas.treinamento.service;

import com.lucas.treinamento.model.Curso;
import com.lucas.treinamento.model.Turma;
import com.lucas.treinamento.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    @Autowired
    TurmaRepository turmaR;

    public Turma cadastrarTurma(Turma turma){

        if(turma.getInicio() == null){
            throw new IllegalArgumentException("Data de Inicio da Turma é obrigatório");
        }
        if(turma.getFim() == null){
            throw new IllegalArgumentException("Data de Fim da Turma é obrigatório");
        }

        if(turma.getInicio().isAfter(turma.getFim())) {
            throw new IllegalArgumentException("Data de Inicio da Turma deve ser antes do Fim");
        }
        if(turma.getFim().isBefore(turma.getInicio())) {
            throw new IllegalArgumentException("Data de Fim da Turma deve ser depois do Inicio");
        }
        turmaR.salvarTurma(turma);
        return turma;
    }

    public void removerTurma(int codigo){

        if(codigo <= 0){
            throw new IllegalArgumentException("Codigo da Turma inválido");
        }
        turmaR.deletarTurma(codigo);
    }

    public void alterarTurma(Turma turma){

        if(turma.getCodigo() == null){
            throw new IllegalArgumentException("Codigo da Turma inválido");
        }
        if(turma.getInicio() == null){
            throw new IllegalArgumentException("Data de Inicio da Turma é obrigatório");
        }
        if(turma.getFim() == null){
            throw new IllegalArgumentException("Data de Fim da Turma é obrigatório");
        }

        if(turma.getInicio().isAfter(turma.getFim())) {
            throw new IllegalArgumentException("Data de Inicio da Turma deve ser antes do Fim");
        }
        if(turma.getFim().isBefore(turma.getInicio())) {
            throw new IllegalArgumentException("Data de Fim da Turma deve ser depois do Inicio");
        }
        turmaR.alterarTurma(turma);
    }

    public List<Turma> listarTurmasPorCurso(Integer codigoCurso){

        List<Turma> listaDeTurmas = turmaR.buscarTurmasPorCurso(codigoCurso);

        if(listaDeTurmas.isEmpty()){
            throw new IllegalStateException("Nenhuma Turma cadastrada com esse curso");
        }
        return listaDeTurmas;
    }
}

package com.lucas.treinamento.service;

import com.lucas.treinamento.model.TurmaParticipante;
import com.lucas.treinamento.repository.TurmaParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TurmaParticipanteService {

    @Autowired
    TurmaParticipanteRepository turmaParticipanteR;

    public TurmaParticipante cadastrarTurmaParticipante(TurmaParticipante turmaP){

        if(turmaP.getCodigoTurma() <= 0){
            throw new IllegalArgumentException("Código da Turma é obrigatório");
        }
        if(turmaP.getCodigoFuncionario() <= 0){
            throw new IllegalArgumentException("Código do Funcionario é obrigatório");
        }

        int codigoTurma = turmaP.getCodigoTurma();
        int codigoFuncionario = turmaP.getCodigoFuncionario();

        List<TurmaParticipante> listaDeParticipantes = turmaParticipanteR.listarParticipantesDaTurma(codigoTurma);

        for(TurmaParticipante participante : listaDeParticipantes){
            if(participante.getCodigoFuncionario().equals(codigoFuncionario)){
                throw new IllegalStateException("O Funcionario já está cadastrado nessa turma");
            }
        }

        turmaParticipanteR.salvarTurmaParticipante(turmaP);
        return turmaP;
    }

    public void removerTurmaParticipante(int codigoTurma, int codigoFuncionario){

        if(codigoTurma <= 0){
            throw new IllegalArgumentException("Codigo da Turma inválido");
        }
        if(codigoFuncionario <= 0){
            throw new IllegalArgumentException("Codigo do Funcionário inválido");
        }

        turmaParticipanteR.deletarTurmaParticipante(codigoTurma, codigoFuncionario);
    }

    public List<TurmaParticipante> listarParticipantesDaTurma(Integer codigoTurma){

        List<TurmaParticipante> listaDeParticipantes = turmaParticipanteR.listarParticipantesDaTurma(codigoTurma);

        if(listaDeParticipantes.isEmpty()){
            throw new IllegalStateException("Nenhum Participante encontrado nessa Turma");
        }
        return listaDeParticipantes;
    }
}
package com.lucas.treinamento.service;

import com.lucas.treinamento.model.Curso;
import com.lucas.treinamento.repository.CursoRepository;
import com.lucas.treinamento.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoR;

    @Autowired
    TurmaRepository turmaR;

    public Curso cadastrarCurso(Curso curso){

        if(curso.getNome() == null || curso.getNome().isBlank()){
            throw new IllegalArgumentException("Nome do curso é obrigatório");
        }

        if(curso.getDescricao() == null || curso.getDescricao().isBlank()){
            throw new IllegalArgumentException("Descrição do curso é obrigatória");
        }

        if(curso.getDuracao() <= 0){
            throw new IllegalArgumentException("Duração deve ser maior que 0");
        }

        cursoR.salvarCurso(curso);
        return curso;
    }

    public void removerCurso(int codigo){

        if(codigo <= 0){
            throw new IllegalArgumentException("Código do curso inválido"); //recebeu algo invalido
        }
        turmaR.deletarTurmasPorCurso(codigo);
        cursoR.deletarCurso(codigo);
    }

    public void alterarCurso(Curso curso){

        if(curso.getCodigo() == null){
            throw new IllegalArgumentException("Código do curso inválido");
        }

        if(curso.getNome() == null || curso.getNome().isBlank()){
            throw new IllegalArgumentException("Nome do curso é obrigatório");
        }

        if(curso.getDescricao() == null || curso.getDescricao().isBlank()){
            throw new IllegalArgumentException("Descrição do curso é obrigatória");
        }

        if(curso.getDuracao() <= 0){
            throw new IllegalArgumentException("Duração deve ser maior que 0");
        }
        cursoR.alterarCurso(curso);
    }

    public List<Curso> listarCurso(){

        List<Curso> listaDeCursos = cursoR.listarCursos();

        if(listaDeCursos.isEmpty()){
            throw new IllegalStateException("Lista está vazia, nenhum curso cadastrado"); //recebe algo valido mas a operacao nao funciona
        }
        return listaDeCursos;
    }
}
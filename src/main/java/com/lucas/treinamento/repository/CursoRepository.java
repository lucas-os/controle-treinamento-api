package com.lucas.treinamento.repository;

import com.lucas.treinamento.model.Curso;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CursoRepository {

    //JdbcTemplate com RowMapper para executar SQL manualmente e mapear cada linha do ResultSet para objetos Java, sem uso de ORM.

    private final JdbcTemplate jdbcTemplate; //abre conexão, executa e depois fecha, final - definido uma vez e nao é mais trocado

    public CursoRepository(JdbcTemplate jdbcTemplate){ //usando construtor pq se eu der um new posso perder config, da erro de conexao...
        this.jdbcTemplate = jdbcTemplate;
    }

    public void salvarCurso(Curso curso){ //alguem vai me entregar um curso preenchido
        String sql = "INSERT INTO Curso (Nome, Descricao, Duracao) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, curso.getNome(), curso.getDescricao(), curso.getDuracao());
    }

    public void deletarCurso(int codigo){
        String sql = "DELETE FROM Curso WHERE Codigo = ?";
        jdbcTemplate.update(sql, codigo);
    }

    public List<Curso> listCursos(){
        String sql = "SELECT Codigo, Nome, Descricao, Duracao FROM Curso";

        RowMapper<Curso> mapper = (rs, rowNum) -> {
            Curso curso = new Curso();
            curso.setCodigo(rs.getInt("Codigo"));
            curso.setNome(rs.getString("Nome"));
            curso.setDescricao(rs.getString("Descricao"));
            curso.setDuracao(rs.getInt("Duracao"));
            return curso;
        };

        return jdbcTemplate.query(sql, mapper);

        //O RowMapper é responsável por converter cada linha do resultado da consulta em um objeto Curso, facilitando o uso dos dados no código.
    }

    public void alterarCurso(Curso curso){
        String sql = "UPDATE Curso SET Nome = ?, Descricao = ?, Duracao = ? WHERE Codigo = ?";
        jdbcTemplate.update(sql, curso.getNome(), curso.getDescricao(), curso.getDuracao(), curso.getCodigo());
    }
}
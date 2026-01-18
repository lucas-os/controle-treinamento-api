package com.lucas.treinamento.repository;

import com.lucas.treinamento.model.Curso;
import com.lucas.treinamento.model.Turma;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TurmaRepository {

    private final JdbcTemplate jdbcTemplate;

    public TurmaRepository(JdbcTemplate jdbcTemplate){ //usando construtor pq se eu der um new posso perder config, da erro de conexao...
        this.jdbcTemplate = jdbcTemplate;
    }

    public void salvarTurma(Turma turma){ //alguem vai me entregar uma turma preenchida
        String sql = "INSERT INTO Turma (Inicio, Fim, Local, Curso) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, turma.getInicio(), turma.getFim(), turma.getLocal(), turma.getCodigoCurso());
    }

    public void deletarTurma(int codigo){
        String sql = "DELETE FROM Turma WHERE Codigo = ?";
        jdbcTemplate.update(sql, codigo);
    }

    public void alterarTurma(Turma turma){
        String sql = "UPDATE Turma SET Inicio = ?, Fim = ?, Local = ? WHERE Codigo = ?";
        jdbcTemplate.update(sql, turma.getInicio(), turma.getFim(), turma.getLocal(), turma.getCodigo());
    }

    public List<Turma> buscarTurmasPorCurso(Integer codigoCurso){

        String sql = "SELECT Turma.Codigo, Inicio, Fim, Local, Curso, COUNT(TurmaParticipante.Codigo) AS quantidadeParticipantes FROM Turma " + //LEFT JOIN para cada turma tente achar registros na tabela TP que tem mesmo codigo da turma
                     "LEFT JOIN TurmaParticipante ON Turma.Codigo = TurmaParticipante.Turma WHERE Turma.Curso = ? " +
                     "GROUP BY Codigo, Inicio, Fim, Local, Curso ORDER BY Turma.Inicio, Turma.Fim";

        RowMapper<Turma> mapper = ((rs, rowNum) -> {
            Turma turma = new Turma();
            turma.setCodigo(rs.getInt("Codigo"));
            turma.setInicio(rs.getDate("Inicio").toLocalDate());
            turma.setFim(rs.getDate("Fim").toLocalDate());
            turma.setLocal(rs.getString("Local"));
            turma.setCodigoCurso(rs.getInt("Curso"));
            turma.setQuantidadeParticipantes((rs.getInt(("quantidadeParticipantes"))));
            return turma;
        });
        return jdbcTemplate.query(sql, mapper, codigoCurso);
    }
}
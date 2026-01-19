package com.lucas.treinamento.repository;

import com.lucas.treinamento.model.Funcionario;
import com.lucas.treinamento.model.TurmaParticipante;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TurmaParticipanteRepository {

    private final JdbcTemplate jdbcTemplate;

    public TurmaParticipanteRepository(JdbcTemplate jdbcTemplate){ //usando construtor pq se eu der um new posso perder config, da erro de conexao...
        this.jdbcTemplate = jdbcTemplate;
    }

    public void salvarTurmaParticipante(TurmaParticipante turmaP){
        String sql = "INSERT INTO TurmaParticipante (Turma, Funcionario) VALUES (?, ?)";
        jdbcTemplate.update(sql, turmaP.getCodigoTurma(), turmaP.getCodigoFuncionario());
    }

    public void deletarTurmaParticipante(int codigoTurma, int codigoFuncionario){
        String sql = "DELETE FROM TurmaParticipante WHERE Turma =  ? AND Funcionario = ?";
        jdbcTemplate.update(sql, codigoTurma, codigoFuncionario);
    }

    public List<TurmaParticipante> listarParticipantesDaTurma(int codigoTurma) {
        String sql = "SELECT TurmaParticipante.Codigo AS turmaParticipanteCodigo, TurmaParticipante.Turma AS codigoTurma, TurmaParticipante.Funcionario AS codigoFuncionario, Funcionario.Codigo AS funcionarioCodigo, Funcionario.Nome AS nomeFuncionario, Funcionario.CPF AS cpfFuncionario, " +
                     "Funcionario.Nascimento AS nascFuncionario, Funcionario.Cargo AS cargoFuncionario, Funcionario.Admissao AS admissaoFuncionario, Funcionario.Status AS statusFuncionario " +
                     "FROM TurmaParticipante LEFT JOIN Funcionario ON TurmaParticipante.Funcionario = Funcionario.codigo WHERE Turma = ?";

        RowMapper<TurmaParticipante> mapper = (rs, rowNum) -> {
            TurmaParticipante turmaP = new TurmaParticipante();
            Funcionario funcionario = new Funcionario();

            turmaP.setCodigo(rs.getInt("turmaParticipanteCodigo"));
            turmaP.setCodigoTurma(rs.getInt("codigoTurma"));
            turmaP.setCodigoFuncionario(rs.getInt("codigoFuncionario"));
            funcionario.setCodigo(rs.getInt("funcionarioCodigo"));
            funcionario.setNome(rs.getString("nomeFuncionario"));
            funcionario.setCpf(rs.getString("cpfFuncionario"));
            funcionario.setNascimento(rs.getDate("nascFuncionario").toLocalDate());
            funcionario.setCargo(rs.getString("cargoFuncionario"));
            funcionario.setAdmissao(rs.getDate("admissaoFuncionario").toLocalDate());
            funcionario.setStatus(rs.getBoolean("statusFuncionario"));
            turmaP.setFuncionario(funcionario);

            return turmaP;
        };
        return jdbcTemplate.query(sql, mapper, codigoTurma);
    }
}
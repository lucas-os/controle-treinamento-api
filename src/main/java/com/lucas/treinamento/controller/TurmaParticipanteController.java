package com.lucas.treinamento.controller;

import com.lucas.treinamento.model.Turma;
import com.lucas.treinamento.model.TurmaParticipante;
import com.lucas.treinamento.service.TurmaParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmasParticipantes")
public class TurmaParticipanteController {

    @Autowired
    TurmaParticipanteService turmaParticipanteService;

    @PostMapping
    public ResponseEntity<TurmaParticipante> cadastrarTurmaParticipante(@RequestBody TurmaParticipante turmaP){

        turmaParticipanteService.cadastrarTurmaParticipante(turmaP);
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaP);
    }

    @GetMapping("/{codigoTurma}")
    public ResponseEntity<List<TurmaParticipante>> listarParticipantesDaTurma(@PathVariable Integer codigoTurma){

       List<TurmaParticipante> listaDeParticipantes = turmaParticipanteService.listarParticipantesDaTurma(codigoTurma);
       return ResponseEntity.ok(listaDeParticipantes);
    }

    @DeleteMapping("/{codigoTurma}/{codigoFuncionario}")
    public ResponseEntity<Void> deletarTurma(@PathVariable Integer codigoTurma, @PathVariable Integer codigoFuncionario){

        turmaParticipanteService.removerTurmaParticipante(codigoTurma, codigoFuncionario);
        return ResponseEntity.status(204).build();
    }
}
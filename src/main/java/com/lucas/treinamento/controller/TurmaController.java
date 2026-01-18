package com.lucas.treinamento.controller;

import com.lucas.treinamento.model.Curso;
import com.lucas.treinamento.model.Turma;
import com.lucas.treinamento.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    TurmaService turmaService;

    @PostMapping
    public ResponseEntity<Turma> cadastrarTurma(@RequestBody Turma turma){

        turmaService.cadastrarTurma(turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(turma);
    }

    @GetMapping("/curso/{codigoCurso}")
    public ResponseEntity<List<Turma>> listarTurmasPorCurso(@PathVariable Integer codigoCurso){

       List<Turma> listaDeTurmas = turmaService.listarTurmasPorCurso(codigoCurso);
       return ResponseEntity.ok(listaDeTurmas);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletarTurma(@PathVariable Integer codigo){

        turmaService.removerTurma(codigo);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Turma> alterarTurma(@PathVariable Integer codigo, @RequestBody Turma turma){

        turma.setCodigo(codigo);
        turmaService.alterarTurma(turma);
        return ResponseEntity.ok(turma);
    }
}
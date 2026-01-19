package com.lucas.treinamento.controller;

import com.lucas.treinamento.model.Curso;
import com.lucas.treinamento.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController{

    @Autowired
    CursoService cursoService;

    @PostMapping
    public ResponseEntity<Curso> cadastrarCurso(@RequestBody Curso curso){

        cursoService.cadastrarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos(){

        List<Curso> listaCursos =  cursoService.listarCurso();
        return ResponseEntity.ok(listaCursos);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletarCurso(@PathVariable Integer codigo){

        cursoService.removerCurso(codigo);
        return ResponseEntity.status(204).build(); //remocao feita com sucesso e nao retorna nada
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Curso> alterarCurso(@PathVariable Integer codigo, @RequestBody Curso curso){ //evita confiar no body, path manda nao o json

        curso.setCodigo(codigo);
        cursoService.alterarCurso(curso);
        return ResponseEntity.ok(curso);
    }
}
package com.example.gestaoEscolar.controller;

import com.example.gestaoEscolar.dto.turma.CriarRequisicaoTurmaDto;
import com.example.gestaoEscolar.dto.turma.CriarRespostaTurmaDto;
import com.example.gestaoEscolar.service.TurmaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
    private final TurmaService service;

    public TurmaController(TurmaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CriarRespostaTurmaDto> create (@RequestBody CriarRequisicaoTurmaDto requisicaoTurmaDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requisicaoTurmaDto));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriarRespostaTurmaDto> put(@PathVariable int id, @RequestBody CriarRequisicaoTurmaDto requisicaoTurmaDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(id, requisicaoTurmaDto));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CriarRespostaTurmaDto>> get() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodos());
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriarRespostaTurmaDto> getById(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/cursos/{id}/turmas")
    public ResponseEntity<CriarRespostaTurmaDto> SearchClassesByCourse(@PathVariable int idCurso) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarTurmasPorCurso(idCurso));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable int id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
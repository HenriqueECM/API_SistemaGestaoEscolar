package com.example.gestaoEscolar.controller;

import com.example.gestaoEscolar.dto.curso.CriarRequisicaoCursoDto;
import com.example.gestaoEscolar.dto.curso.CriarRespostaCursoDto;
import com.example.gestaoEscolar.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {
    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CriarRespostaCursoDto> create (@RequestBody CriarRequisicaoCursoDto requisicaoCursoDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requisicaoCursoDto));
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CriarRespostaCursoDto>> get (){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodos());
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriarRespostaCursoDto> getById(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriarRespostaCursoDto> put (@PathVariable int id, @RequestBody CriarRequisicaoCursoDto requisicaoCursoDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(id, requisicaoCursoDto));
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable int id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

package com.example.gestaoEscolar.controller;

import com.example.gestaoEscolar.dto.request.CriarRequisicaoProfessorDto;
import com.example.gestaoEscolar.dto.response.CriarRespostaProfessorDto;
import com.example.gestaoEscolar.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CriarRespostaProfessorDto> create (@RequestBody CriarRequisicaoProfessorDto requisicaoProfessorDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requisicaoProfessorDto));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CriarRespostaProfessorDto>> get (){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodos());
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriarRespostaProfessorDto> getById (@PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriarRespostaProfessorDto> put (@PathVariable int id, @RequestBody CriarRequisicaoProfessorDto requisicaoProfessorDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(id, requisicaoProfessorDto));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable int id){
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

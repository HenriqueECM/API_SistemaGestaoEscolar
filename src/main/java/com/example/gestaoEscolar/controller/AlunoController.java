package com.example.gestaoEscolar.controller;

import com.example.gestaoEscolar.dto.request.CriarRequisaoAlunoDto;
import com.example.gestaoEscolar.dto.response.CriarRespostaAlunoDto;
import com.example.gestaoEscolar.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CriarRespostaAlunoDto> create (@Valid @RequestBody CriarRequisaoAlunoDto requisaoAlunoDto){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requisaoAlunoDto));

        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CriarRespostaAlunoDto>> get (){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodos());

        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriarRespostaAlunoDto> getById(@PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));

        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriarRespostaAlunoDto> update (@PathVariable int id, @RequestBody CriarRequisaoAlunoDto requisaoAlunoDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(id, requisaoAlunoDto));

        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("{id}")
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
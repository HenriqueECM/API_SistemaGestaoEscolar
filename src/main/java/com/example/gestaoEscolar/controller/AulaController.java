package com.example.gestaoEscolar.controller;

import com.example.gestaoEscolar.dto.request.CriarRequisicaoAulaDto;
import com.example.gestaoEscolar.dto.response.CriarRespostaAulaDto;
import com.example.gestaoEscolar.service.AulaService;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/aula")
public class AulaController {
    private final AulaService service;

    public AulaController(AulaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CriarRespostaAulaDto> create (@Valid @RequestBody CriarRequisicaoAulaDto requisicaoAulaDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requisicaoAulaDto));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<CriarRespostaAulaDto> update (@PathVariable int id, @RequestBody CriarRequisicaoAulaDto requisicaoAulaDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(id, requisicaoAulaDto));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CriarRespostaAulaDto>> buscarTodos(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarTodos());
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/id")
    public ResponseEntity<CriarRespostaAulaDto> buscarPorID(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
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

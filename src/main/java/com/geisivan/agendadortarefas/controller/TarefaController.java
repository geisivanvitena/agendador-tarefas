package com.geisivan.agendadortarefas.controller;

import com.geisivan.agendadortarefas.business.service.TarefaService;
import com.geisivan.agendadortarefas.business.dto.TarefasDTO;
import com.geisivan.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefasDTO> criarTarefa(@RequestBody TarefasDTO dto,
                                                  @RequestHeader("Authorization") String token){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tarefaService.criarTarefa(dto, token));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorEmail(
            @RequestHeader("Authorization") String token){
        List<TarefasDTO> listaTarefas = tarefaService.buscarTarefasPorEmail(token);
        return ResponseEntity.ok(listaTarefas);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(
                    iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(
                    iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim){
        return ResponseEntity.ok(
                tarefaService.buscarTarefasPorPeriodo(dataInicio, dataFim));
    }

    @PutMapping
    public ResponseEntity<TarefasDTO> atualizarTarefa(@RequestParam("id") String id,
                                                      @RequestBody TarefasDTO dto){
        return  ResponseEntity.ok(tarefaService.atualizarTarefa(id, dto));
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> atualizarStatusTarefa(
            @RequestParam("id") String id,
            @RequestParam("status") StatusNotificacaoEnum status){
        return ResponseEntity.ok(tarefaService.atualizarStatusTarefa(id, status));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTarefa(@RequestParam("id") String id){
        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }
}

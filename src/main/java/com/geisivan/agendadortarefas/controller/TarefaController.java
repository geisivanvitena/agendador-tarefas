package com.geisivan.agendadortarefas.controller;

import com.geisivan.agendadortarefas.business.TarefaService;
import com.geisivan.agendadortarefas.business.dto.TarefaDTO;
import com.geisivan.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
    public ResponseEntity<TarefaDTO> salvaTarefa(@RequestBody TarefaDTO tarefaDTO,
                                                 @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.salvaTarefa(token, tarefaDTO));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTO>> buscaTarefaAgendadaPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim){

        return ResponseEntity.ok(
                tarefaService.buscaTarefaAgendadaPorPeriodo(dataInicio, dataFim));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscaTarefaPorEmail(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.buscaTarefaPorEmail(token));
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> updateTarefa(@RequestBody TarefaDTO dto,
                                                  @RequestParam("id") String id){
        return  ResponseEntity.ok(tarefaService.updateTarefa(dto, id));

    }

    @PatchMapping
    public ResponseEntity<TarefaDTO> alteraStatusNotificacao(@RequestParam("status")StatusNotificacaoEnum satus,
                                                             @RequestParam("id") String id){
        return ResponseEntity.ok(tarefaService.alteraStatusTarefa(satus, id));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id){
        tarefaService.deletaTarefaPorId(id);
        return ResponseEntity.ok().build();
    }
}

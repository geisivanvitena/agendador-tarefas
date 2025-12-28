package com.geisivan.agendadortarefas.controller;

import com.geisivan.agendadortarefas.business.TarefaService;
import com.geisivan.agendadortarefas.business.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> salvaTarefa(@RequestBody TarefaDTO tarefaDTO,
                                                 @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.salvaTarefa(token, tarefaDTO));
    }
}

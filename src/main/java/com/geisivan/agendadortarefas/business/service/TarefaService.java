package com.geisivan.agendadortarefas.business.service;

import com.geisivan.agendadortarefas.business.dto.TarefasDTO;
import com.geisivan.agendadortarefas.business.mapper.TarefaConverter;
import com.geisivan.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.geisivan.agendadortarefas.infrastructure.entity.TarefaEntity;
import com.geisivan.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.geisivan.agendadortarefas.infrastructure.exception.ResourceNotFoundException;
import com.geisivan.agendadortarefas.infrastructure.repository.TarefaRepository;
import com.geisivan.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTO criarTarefa(TarefasDTO dto, String token){
        String email = jwtUtil.extrairEmailDoToken(token);
        TarefasDTO dtoFinal = new TarefasDTO(
                null,
                dto.nomeTarefa(),
                dto.descricao(),
                LocalDateTime.now(),
                dto.dataEvento(),
                email,
                null,
                StatusNotificacaoEnum.PENDENTE
        );
        TarefaEntity tarefaEntity = tarefaConverter.paraTarefaEntity(dtoFinal);
        return tarefaConverter.paraTarefasDTO(tarefaRepository.save(tarefaEntity));
    }

    public List<TarefasDTO> buscarTarefasPorEmail(String token){
        String email = jwtUtil.extrairEmailDoToken(token);
        List<TarefaEntity> listaTarefas = tarefaRepository.findByEmailUsuario(email);
        return tarefaConverter.paraListaTarefasDTO(listaTarefas);
    }

    public List<TarefasDTO> buscarTarefasPorPeriodo(LocalDateTime dataInicio,
                                                    LocalDateTime dataFim){
        return tarefaConverter.paraListaTarefasDTO(
                tarefaRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(
                        dataInicio, dataFim,
                        StatusNotificacaoEnum.PENDENTE));
    }

    public TarefasDTO atualizarTarefa(String id, TarefasDTO dto){
        TarefaEntity tarefaEntity = tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tarefa não encontrada com ID: " + id));
            tarefaUpdateConverter.updateTarefa(dto, tarefaEntity);
            return tarefaConverter.paraTarefasDTO(tarefaRepository.save(tarefaEntity));
    }

    public TarefasDTO atualizarStatusTarefa(String id, StatusNotificacaoEnum status){
        TarefaEntity tarefaEntity = tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tarefa não encontrada com ID: " + id));
            tarefaEntity.setStatusNotificacaoEnum(status);
            return tarefaConverter.paraTarefasDTO(
                    tarefaRepository.save(tarefaEntity));
    }

    public void deletarTarefa(String id){
        TarefaEntity tarefaEntity = tarefaRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Tarefa não encontrada com ID : " + id));
            tarefaRepository.delete(tarefaEntity);
    }
}

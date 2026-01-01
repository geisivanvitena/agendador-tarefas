package com.geisivan.agendadortarefas.business;

import com.geisivan.agendadortarefas.business.dto.TarefaDTO;
import com.geisivan.agendadortarefas.business.mapper.TarefaConverter;
import com.geisivan.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.geisivan.agendadortarefas.infrastructure.entity.TarefaEntity;
import com.geisivan.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.geisivan.agendadortarefas.infrastructure.exception.ResourceNotFoundException;
import com.geisivan.agendadortarefas.infrastructure.repository.TarefaRepository;
import com.geisivan.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.target.ThreadLocalTargetSource;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefaDTO salvaTarefa(String token, TarefaDTO tarefaDTO){
        String email = jwtUtil.extractUsername(token.substring(7));

        tarefaDTO.setEmailUsuario(email);
        tarefaDTO.setDataCriacao(LocalDateTime.now());
        tarefaDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);

        TarefaEntity tarefaEntity = tarefaConverter.paraTarefaEntity(tarefaDTO);

        return tarefaConverter.paraTarefaDTO(tarefaRepository.save(tarefaEntity));
    }

    public List<TarefaDTO> buscaTarefaAgendadaPorPeriodo(LocalDateTime dataInicio,
                                                         LocalDateTime dataFim){
        return tarefaConverter.paraListaTarefaDTO(
                tarefaRepository.findByDataEventoBetween(dataInicio, dataFim));
    }

    public List<TarefaDTO> buscaTarefaPorEmail(String token){
        String email = jwtUtil.extractUsername(token.substring(7));

        return tarefaConverter.paraListaTarefaDTO(
                tarefaRepository.findByEmailUsuario(email)
        );
    }

    public void deletaTarefaPorId(String id){
        try {
            tarefaRepository.deleteById(id);

        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(
                    "Nenhuma tarefa encontrada com ID : " + id, e.getCause());
        }
    }

    public TarefaDTO alteraStatusTarefa(StatusNotificacaoEnum status, String id){
        try {
            TarefaEntity tarefaEntity = tarefaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Tarefa não encontrada com o ID: " + id));
            tarefaEntity.setStatusNotificacaoEnum(status);
            return tarefaConverter.paraTarefaDTO(
                    tarefaRepository.save(tarefaEntity)
            );

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(
                    "Erro ao alterar status da tarefa. " + e.getCause());
        }
    }

    public TarefaDTO updateTarefa(TarefaDTO dto, String id){
        try {
           TarefaEntity entity = tarefaRepository.findById(id)
                   .orElseThrow(() -> new ResourceNotFoundException(
                           "Tarefa não encontrada com o ID: " + id));

           tarefaUpdateConverter.updateTarefa(dto, entity);
           return tarefaConverter.paraTarefaDTO(tarefaRepository.save(entity));

        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException(
                    "Erro ao alterar o staus da tarefa. " + e.getCause());
        }
    }





}

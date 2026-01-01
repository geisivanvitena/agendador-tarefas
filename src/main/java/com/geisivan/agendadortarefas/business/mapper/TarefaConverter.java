package com.geisivan.agendadortarefas.business.mapper;

import com.geisivan.agendadortarefas.business.dto.TarefaDTO;
import com.geisivan.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefaEntity paraTarefaEntity(TarefaDTO tarefaDTO);

    TarefaDTO paraTarefaDTO(TarefaEntity tarefaEntity);

    List<TarefaEntity> paraListaTarefaEntity(List<TarefaDTO> listaTarefaDTO);

    List<TarefaDTO> paraListaTarefaDTO(List<TarefaEntity> listaTarefaEntity);
}

package com.geisivan.agendadortarefas.business.mapper;

import com.geisivan.agendadortarefas.business.dto.TarefaDTO;
import com.geisivan.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefaEntity paraTarefaEntity(TarefaDTO tarefaDTO);

    TarefaDTO paraTarefaDTO(TarefaEntity tarefaEntity);
}

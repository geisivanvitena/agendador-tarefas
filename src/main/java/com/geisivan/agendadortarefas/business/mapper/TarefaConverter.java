package com.geisivan.agendadortarefas.business.mapper;

import com.geisivan.agendadortarefas.business.dto.TarefasDTO;
import com.geisivan.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefaEntity paraTarefaEntity(TarefasDTO TarefasDTO);

    TarefasDTO paraTarefasDTO(TarefaEntity tarefaEntity);

    List<TarefaEntity> paraListaTarefaEntity(List<TarefasDTO> listaTarefasDTO);

    List<TarefasDTO> paraListaTarefasDTO(List<TarefaEntity> listaTarefaEntity);
}

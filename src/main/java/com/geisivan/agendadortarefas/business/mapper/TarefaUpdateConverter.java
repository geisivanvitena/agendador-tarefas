package com.geisivan.agendadortarefas.business.mapper;

import com.geisivan.agendadortarefas.business.dto.TarefasDTO;
import com.geisivan.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefa(TarefasDTO dto, @MappingTarget TarefaEntity entity);
}

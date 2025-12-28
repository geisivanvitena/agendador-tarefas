package com.geisivan.agendadortarefas.infrastructure.repository;

import com.geisivan.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefaRepository extends MongoRepository<TarefaEntity, String> {

    List<TarefaEntity> findByDataEventoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    List<TarefaEntity> findByEmailUsuario(String email);
}

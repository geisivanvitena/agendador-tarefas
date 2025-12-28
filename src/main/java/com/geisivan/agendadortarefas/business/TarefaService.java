package com.geisivan.agendadortarefas.business;

import com.geisivan.agendadortarefas.business.dto.TarefaDTO;
import com.geisivan.agendadortarefas.business.mapper.TarefaConverter;
import com.geisivan.agendadortarefas.infrastructure.entity.TarefaEntity;
import com.geisivan.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.geisivan.agendadortarefas.infrastructure.repository.TarefaRepository;
import com.geisivan.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefaDTO salvaTarefa(String token, TarefaDTO tarefaDTO){
        String email = jwtUtil.extractUsername(token.substring(7));

        tarefaDTO.setEmailUsuario(email);
        tarefaDTO.setDataCriacao(LocalDateTime.now());
        tarefaDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);

        TarefaEntity tarefaEntity = tarefaConverter.paraTarefaEntity(tarefaDTO);

        return tarefaConverter.paraTarefaDTO(tarefaRepository.save(tarefaEntity));
    }
}

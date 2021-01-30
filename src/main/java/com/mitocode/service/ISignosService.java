package com.mitocode.service;

import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.model.Signos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface ISignosService extends ICRUD<Signos, Integer>{

	List<Signos> buscar(FiltroConsultaDTO filtro);

	List<Signos> buscarFecha(LocalDateTime fecha);

  Page<Signos> listarPageable(Pageable pageable);

}

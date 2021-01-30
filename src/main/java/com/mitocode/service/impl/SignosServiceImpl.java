package com.mitocode.service.impl;

import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.model.Signos;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.ISignosRepo;
import com.mitocode.service.ISignosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SignosServiceImpl extends CRUDImpl<Signos, Integer> implements ISignosService {

	@Autowired
	private ISignosRepo repo;


	@Override
	protected IGenericRepo<Signos, Integer> getRepo() {
		return repo;
	}


  @Override
  public Page<Signos> listarPageable(Pageable pageable) {
    return repo.findAll(pageable);
  }

	@Override
	public List<Signos> buscar(FiltroConsultaDTO filtro) {
		return repo.buscar(filtro.getDni(), filtro.getNombreCompleto());
	}

	@Override
	public List<Signos> buscarFecha(LocalDateTime fecha) {
		return repo.buscarFecha(fecha, fecha.plusDays(1));
	}

}

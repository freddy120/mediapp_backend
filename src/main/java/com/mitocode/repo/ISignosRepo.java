package com.mitocode.repo;

import com.mitocode.model.Signos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

//@Repository
public interface ISignosRepo extends IGenericRepo<Signos, Integer>{

	//@Query(JPQL)
	//SQL | SELECT p.* FROM Signos C INNER JOIN Paciente p ON c.id_paciente = p.id_paciente WHERE p.dni = ?
	@Query("FROM Signos c WHERE c.paciente.dni = :dni OR LOWER(c.paciente.nombres) LIKE %:nombreCompleto% OR LOWER(c.paciente.apellidos) LIKE %:nombreCompleto%")
	List<Signos> buscar(@Param("dni") String dni, @Param("nombreCompleto") String nombreCompleto);
	
	// X>= BETEWEEN Y< | 14-11-2020 - 15-11-2020 | ISODate yyyy-mm-ddTHH:mm:ss.mm
	@Query("FROM Signos c WHERE c.fecha BETWEEN :fechaSignos AND :fechaSgte")
	List<Signos> buscarFecha(@Param("fechaSignos") LocalDateTime fechaSignos, @Param("fechaSgte") LocalDateTime fechaSgte);

	
}
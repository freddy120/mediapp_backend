package com.mitocode.controller;

import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Signos;
import com.mitocode.service.ISignosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/signos")
public class SignosController {

  @Autowired
  private ISignosService service;

  @GetMapping
  public ResponseEntity<List<Signos>> listar() throws Exception {
    List<Signos> lista = service.listar();
    return new ResponseEntity<List<Signos>>(lista, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Signos> listarPorId(@PathVariable("id") Integer id) throws Exception {
    Signos obj = service.listarPorId(id);

    if (obj == null) {
      throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
    }
    return new ResponseEntity<Signos>(obj, HttpStatus.OK);
  }


  @PostMapping
  public ResponseEntity<Signos> registrar(@Valid @RequestBody Signos signos) throws Exception {
    Signos obj = service.registrar(signos);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(obj.getIdSignos()).toUri();
    return ResponseEntity.created(location).build();
  }

  @PutMapping
  public ResponseEntity<Signos> modificar(@Valid @RequestBody Signos p) throws Exception {
    Signos obj = service.modificar(p);
    return new ResponseEntity<Signos>(obj, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
    Signos obj = service.listarPorId(id);

    if (obj == null) {
      throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
    }

    service.eliminar(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/buscar")
  public ResponseEntity<List<Signos>> buscarFecha(@RequestParam("fecha") String fecha) {
    List<Signos> signos = new ArrayList<>();

    signos = service.buscarFecha(LocalDateTime.parse(fecha));

    return new ResponseEntity<List<Signos>>(signos, HttpStatus.OK);
  }

  @PostMapping("/buscar/otros")
  public ResponseEntity<List<Signos>> buscarOtro(@RequestBody FiltroConsultaDTO filtro) {
    List<Signos> signos = new ArrayList<>();

    signos = service.buscar(filtro);

    return new ResponseEntity<List<Signos>>(signos, HttpStatus.OK);
  }

  @GetMapping("/pageable")
  public ResponseEntity<Page<Signos>> listarPageable(Pageable pageable) throws Exception{
    Page<Signos> signos = service.listarPageable(pageable);
    return new ResponseEntity<Page<Signos>>(signos, HttpStatus.OK);
  }

}

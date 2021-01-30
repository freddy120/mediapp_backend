package com.mitocode.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "signos")
public class Signos {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idSignos;

  @ManyToOne
  @JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name = "FK_signos_paciente"))
  private Paciente paciente;

  @Column(name = "fecha", nullable = false)
  private LocalDateTime fecha;

  @Column(name = "temperatura")
  private String temperatura;

  @Column(name = "pulso")
  private String pulso;

  @Column(name = "ritmo_respiratorio")
  private String ritmoRespiratorio;


  public Integer getIdSignos() {
    return idSignos;
  }

  public void setIdSignos(Integer idSignos) {
    this.idSignos = idSignos;
  }

  public Paciente getPaciente() {
    return paciente;
  }

  public void setPaciente(Paciente paciente) {
    this.paciente = paciente;
  }

  public LocalDateTime getFecha() {
    return fecha;
  }

  public void setFecha(LocalDateTime fecha) {
    this.fecha = fecha;
  }

  public String getTemperatura() {
    return temperatura;
  }

  public void setTemperatura(String temperatura) {
    this.temperatura = temperatura;
  }

  public String getPulso() {
    return pulso;
  }

  public void setPulso(String pulso) {
    this.pulso = pulso;
  }

  public String getRitmoRespiratorio() {
    return ritmoRespiratorio;
  }

  public void setRitmoRespiratorio(String ritmoRespiratorio) {
    this.ritmoRespiratorio = ritmoRespiratorio;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Signos signos = (Signos) o;
    return idSignos.equals(signos.idSignos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idSignos);
  }
}

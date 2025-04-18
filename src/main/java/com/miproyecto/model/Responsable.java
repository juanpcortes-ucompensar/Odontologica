package com.miproyecto.model;

import javax.persistence.*;

@Entity
public class Responsable extends Persona {

    private String tipoResponsable;

    @OneToOne(mappedBy = "responsable")
    private Paciente paciente;

    // Getters y Setters

    public String getTipoResponsable() {
        return tipoResponsable;
    }

    public void setTipoResponsable(String tipoResponsable) {
        this.tipoResponsable = tipoResponsable;
    }

    public Paciente getPacientes() {
        return paciente;
    }

    public void setPacientes(Paciente paciente) {
        this.paciente = paciente;
    }
}

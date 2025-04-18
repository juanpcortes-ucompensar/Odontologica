package com.miproyecto.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Paciente extends Persona {
    
    private String tipoSangre;
    private boolean tieneEps;

    @OneToMany(mappedBy = "paciente")
    private List<Turno> turnos;

    @OneToOne
    @JoinColumn(name = "responsable_id")
    private Responsable responsable;

    // Getters y Setters

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public boolean isTieneEps() {
        return tieneEps;
    }

    public void setTieneEps(boolean tieneEps) {
        this.tieneEps = tieneEps;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }
}

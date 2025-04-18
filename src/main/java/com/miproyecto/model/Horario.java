package com.miproyecto.model;

import javax.persistence.*;

@Entity
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHorario;

    private String inicioHorario;
    private String finHorario;

    @OneToOne(mappedBy = "horario")
    private Odontologo odontologo;

    // Getters y Setters

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getInicioHorario() {
        return inicioHorario;
    }

    public void setInicioHorario(String inicioHorario) {
        this.inicioHorario = inicioHorario;
    }

    public String getFinHorario() {
        return finHorario;
    }

    public void setFinHorario(String finHorario) {
        this.finHorario = finHorario;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }
}

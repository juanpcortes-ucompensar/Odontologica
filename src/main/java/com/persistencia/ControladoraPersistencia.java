package com.persistencia;

import com.miproyecto.model.Horario;
import com.miproyecto.model.Odontologo;
import com.miproyecto.model.Paciente;
import com.miproyecto.model.Responsable;
import com.miproyecto.model.Secretario;
import com.miproyecto.model.Turno;
import com.miproyecto.model.Usuario;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ControladoraPersistencia {

    private UsuarioJpaController usuarioJpa = new UsuarioJpaController();
    private HorarioJpaController horarioJpa = new HorarioJpaController();
    private OdontologoJpaController odontologoJpa = new OdontologoJpaController();
    private PacienteJpaController pacienteJpa = new PacienteJpaController();
    private ResponsableJpaController responsableJpa = new ResponsableJpaController();
    private TurnoJpaController turnoJpa = new TurnoJpaController();
    private SecretarioJpaController secretarioJpa = new SecretarioJpaController();

    public void crearUsuario(Usuario usuario) throws Exception {
        usuarioJpa.create(usuario);
    }

    public List<Usuario> traerUsuarios() {
        return usuarioJpa.findUsuarioEntities();
    }

    public Usuario traerUsuario(int id) {
        return usuarioJpa.findUsuario(id);
    }

    public void borrarUsuario(int id) {
        try {
            usuarioJpa.destroy(id);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void editarUsuario(Usuario usuario) {
        try {
            usuarioJpa.edit(usuario);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void crearHorario(Horario horario) throws Exception {
        horarioJpa.create(horario);
    }

    public List<Horario> traerHorarios() {
        return horarioJpa.findHorarioEntities();
    }

    public Horario traerHorario(int id) {
        return horarioJpa.findHorario(id);
    }

    public void borrarHorario(int id) {
        try {
            horarioJpa.destroy(id);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void editarHorario(Horario horario) {
        try {
            horarioJpa.edit(horario);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void crearOdontologo(Odontologo odontologo) throws Exception {
        odontologoJpa.create(odontologo);
    }

    public List<Odontologo> traerOdontologos() {
        return odontologoJpa.findOdontologoEntities();
    }

    public Odontologo traerOdontologo(String dni) {
        return odontologoJpa.findOdontologo(dni);
    }

    public void borrarOdontologo(String dni) {
        try {
            odontologoJpa.destroy(dni);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void editarOdontologo(Odontologo odontologo) {
        try {
            odontologoJpa.edit(odontologo);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void crearPaciente(Paciente paciente) throws Exception {
        pacienteJpa.create(paciente);
    }

    public List<Paciente> traerPacientes() {
        return pacienteJpa.findPacienteEntities();
    }

    public Paciente traerPaciente(String dni) {
        return pacienteJpa.findPaciente(dni);
    }

    public void borrarPaciente(String dni) {
        try {
            pacienteJpa.destroy(dni);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void editarPaciente(Paciente paciente) {
        try {
            pacienteJpa.edit(paciente);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void crearResponsable(Responsable responsable) throws Exception {
        responsableJpa.create(responsable);
    }

    public List<Responsable> traerResponsables() {
        return responsableJpa.findResponsableEntities();
    }

    public Responsable traerResponsable(String dni) {
        return responsableJpa.findResponsable(dni);
    }

    public void borrarResponsable(String dni) {
        try {
            responsableJpa.destroy(dni);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void editarResponsable(Responsable responsable) {
        try {
            responsableJpa.edit(responsable);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void crearTurno(Turno turno) throws Exception {
        turnoJpa.create(turno);
    }

    public List<Turno> traerTurnos() {
        return turnoJpa.findTurnoEntities();
    }

    public Turno traerTurno(int id) {
        return turnoJpa.findTurno(id);
    }

    public void borrarTurno(int id) {
        try {
            turnoJpa.destroy(id);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void editarTurno(Turno turno) {
        try {
            turnoJpa.edit(turno);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void crearSecretario(Secretario secretario) throws Exception {
        secretarioJpa.create(secretario);
    }

    public List<Secretario> traerSecretarios() {
        return secretarioJpa.findSecretarioEntities();
    }

    public Secretario traerSecretario(String dni) {
        return secretarioJpa.findSecretario(dni);
    }

    public void borrarSecretario(String dni) {
        try {
            secretarioJpa.destroy(dni);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void editarSecretario(Secretario secretario) {
        try {
            secretarioJpa.edit(secretario);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<Turno> traerTurnosPorOdontologo(Odontologo odontologo) {
        return turnoJpa.findTurnosByOdontologo(odontologo);
    }

    public List<Paciente> traerPacientesPorResponsable(Responsable responsable) {
        return pacienteJpa.findPacientesByResponsable(responsable);
    }

    public List<Turno> traerTurnosPorPaciente(Paciente paciente) {
        return turnoJpa.findTurnosByPaciente(paciente);
    }

    public boolean pacienteTieneTurnoActivo(Paciente paciente) {
        // Traemos todos los turnos del paciente
        List<Turno> turnosPaciente = traerTurnosPorPaciente(paciente);
        
        // Si el paciente tiene más de 0 turnos, significa que tiene un turno activo
        return !turnosPaciente.isEmpty();
    }
    
    public boolean odontologoDisponible(Odontologo odontologo, String fecha, String hora) {
        // Traemos todos los turnos del odontólogo
        List<Turno> turnosOdontologo = traerTurnosPorOdontologo(odontologo);
        
        // Recorremos los turnos del odontólogo y verificamos si alguno está en el mismo horario
        for (Turno turno : turnosOdontologo) {
            if (turno.getFechaTurno().equals(fecha) && turno.getHoraTurno().equals(hora)) {
                return false;  // El odontólogo ya tiene un turno en ese horario
            }
        }
    
        // Validamos si la hora solicitada está dentro del rango del horario asignado al odontólogo
        Horario horarioOdontologo = odontologo.getHorario();
        
        if (horarioOdontologo != null) {
            String inicioHorario = horarioOdontologo.getInicioHorario();
            String finHorario = horarioOdontologo.getFinHorario();
    
            // Verificamos si la hora está dentro del rango horario
            if (isHoraDentroDelRango(hora, inicioHorario, finHorario)) {
                return true;  // El odontólogo está disponible en el horario solicitado
            } else {
                return false;  // El horario solicitado está fuera del horario asignado al odontólogo
            }
        }
    
        return false;  // Si no se tiene un horario asignado al odontólogo, no está disponible
    }
    
    // Método auxiliar para comparar si la hora está dentro del rango de horario
    private boolean isHoraDentroDelRango(String hora, String inicioHorario, String finHorario) {
        // Convertimos las horas de String a un formato comparable
        int horaSolicitada = convertirHoraAInt(hora);
        int horaInicio = convertirHoraAInt(inicioHorario);
        int horaFin = convertirHoraAInt(finHorario);
    
        return horaSolicitada >= horaInicio && horaSolicitada <= horaFin;
    }
    
    // Método para convertir la hora en formato "HH:mm" a un valor entero de la hora (para hacer la comparación)
    private int convertirHoraAInt(String hora) {
        String[] partes = hora.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
    
        // Combinamos las horas y minutos para obtener un valor numérico de comparación
        return horas * 60 + minutos;  // Convertimos a minutos para hacer la comparación más sencilla
    }

    public Usuario traerUsuarioPorUserName(String nombreUsuario) {
        return usuarioJpa.findUsuarioByUserName(nombreUsuario);
    }

    public Odontologo traerOdontologoPorUsuario(Usuario usuario) {
        return odontologoJpa.findOdontologoByUsuario(usuario);
    }    

}

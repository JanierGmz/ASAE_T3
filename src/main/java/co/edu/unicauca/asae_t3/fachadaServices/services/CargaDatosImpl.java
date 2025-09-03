package co.edu.unicauca.asae_t3.fachadaServices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import co.edu.unicauca.asae_t3.capaAccesoADatos.repositories.AsignaturaRepository;
import co.edu.unicauca.asae_t3.capaAccesoADatos.repositories.CursoRepository;
import co.edu.unicauca.asae_t3.capaAccesoADatos.repositories.DocenteRepository;
import co.edu.unicauca.asae_t3.capaAccesoADatos.repositories.EspacioFisicoRepository;
import co.edu.unicauca.asae_t3.capaAccesoADatos.repositories.FranjaHorariaRepository;
import java.util.ArrayList;
import java.util.Arrays;

@Service("IDCargaDatosService")
public class CargaDatosImpl implements ICargaDatos {

    @Autowired
    @Qualifier("IDAsignaturaRepository")
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    @Qualifier("IDCursoRepository")
    private CursoRepository cursoRepository;

    @Autowired
    @Qualifier("IDDocenteRepository")
    private DocenteRepository docenteRepository;

    @Autowired
    @Qualifier("IDEspacioFisicoRepository")
    private EspacioFisicoRepository espacioFisicoRepository;

    @Autowired
    @Qualifier("IDFranjaHorariaRepository")
    private FranjaHorariaRepository franjaHorariaRepository;

    @Override
    public void cargarDatos() {
        vincularAsignaturaCursos();
        vincularFranjaHorariaCurso();
        vincularFranjaHorariaEspacioFisico();
        vincularFranjaHorariaDocente();
        System.out.println("Datos vinculados correctamente.");
    }

    private void vincularAsignaturaCursos() {
        // Obtener asignaturas y cursos
        var asignaturasOpt = asignaturaRepository.findAll();
        var cursosOpt = cursoRepository.findAll();
        if (asignaturasOpt.isEmpty() || cursosOpt.isEmpty()) return;

        var asignaturas = new ArrayList<>(asignaturasOpt.get());
        var cursos = new ArrayList<>(cursosOpt.get());

        // Asignación manual: cada curso se asocia a una asignatura específica
        if (cursos.size() >= 5 && asignaturas.size() >= 5) {
            cursos.get(0).setAsignatura(asignaturas.get(0)); // Curso 1 -> Asignatura 1
            cursos.get(1).setAsignatura(asignaturas.get(1)); // Curso 2 -> Asignatura 2
            cursos.get(2).setAsignatura(asignaturas.get(2)); // Curso 3 -> Asignatura 3
            cursos.get(3).setAsignatura(asignaturas.get(2)); // Curso 4 -> Asignatura 3
            cursos.get(4).setAsignatura(asignaturas.get(3)); // Curso 5 -> Asignatura 4

            // Actualizar cursos en el repositorio
            for (int i = 0; i < 5; i++) {
                cursoRepository.update(cursos.get(i).getIdCurso(), cursos.get(i));
            }

            // Asignar cursos a cada asignatura manualmente
            asignaturas.get(0).setCursos(Arrays.asList(cursos.get(0)));
            asignaturas.get(1).setCursos(Arrays.asList(cursos.get(1)));
            asignaturas.get(2).setCursos(Arrays.asList(cursos.get(2), cursos.get(3)));
            asignaturas.get(3).setCursos(Arrays.asList(cursos.get(4)));

            // Actualizar asignaturas en el repositorio
            for (int i = 0; i < 5; i++) {
                asignaturaRepository.update(asignaturas.get(i).getIdAsignatura(), asignaturas.get(i));
            }
        }

        /**
        System.out.println("-------------Asignaturas vinculadas a cursos-------------");
        for (var asignatura : asignaturas) {
            System.out.println("Asignatura: " + asignatura.getNombre() + " - Cursos: " +
                    (asignatura.getCursos() != null ? asignatura.getCursos().stream()
                            .map(CursoEntity -> CursoEntity.getNombre()).reduce((a, b) -> a + ", " + b).orElse("Ninguno")
                            : "Ninguno"));
        }
        System.out.println("-------------Cursos vinculados a asignaturas-------------");
        for (var curso : cursos) {
            System.out.println("Curso: " + curso.getNombre() + " - Asignatura: " +
                    (curso.getAsignatura() != null ? curso.getAsignatura().getNombre() : "Ninguna"));
        }
        **/
    }

    public void vincularFranjaHorariaCurso(){
        var franjasOpt = franjaHorariaRepository.findAll();
        var cursosOpt = cursoRepository.findAll();
        if (franjasOpt.isEmpty() || cursosOpt.isEmpty()) return;

        var franjas = new ArrayList<>(franjasOpt.get());
        var cursos = new ArrayList<>(cursosOpt.get());

        // Vinculación coherente: cada franja se asocia a un curso y cada curso a varias franjas
        if (franjas.size() >= 3 && cursos.size() >= 5) {
            // Asignar franjas a cursos
            franjas.get(0).setCurso(cursos.get(0)); // Franja 1 -> Curso 1
            franjas.get(1).setCurso(cursos.get(1)); // Franja 2 -> Curso 2
            franjas.get(2).setCurso(cursos.get(2)); // Franja 3 -> Curso 3


            // Cada curso tendrá una lista de franjas
            cursos.get(0).setFranjasHorarias(Arrays.asList(franjas.get(0)));
            cursos.get(1).setFranjasHorarias(Arrays.asList(franjas.get(1)));
            cursos.get(2).setFranjasHorarias(Arrays.asList(franjas.get(2)));


            // Actualizar cursos y franjas en el repositorio
            for (int i = 0; i < 3; i++) {
                franjaHorariaRepository.update(franjas.get(i).getIdFranjaHoraria(), franjas.get(i));
                cursoRepository.update(cursos.get(i).getIdCurso(), cursos.get(i));
                //System.out.println("Franja " + franjas.get(i).getIdFranjaHoraria() + " vinculada a Curso " + cursos.get(i).getNombre());
            }
        }
        /**
        System.out.println("-------------Cursos vinculados a franjas horarias-------------");
        for (var curso : cursos) {
            System.out.println("Curso: " + curso.getNombre() + " - Franja: " +
                    (curso.getFranjasHorarias() != null ? curso.getFranjasHorarias().stream()
                            .map(franja -> franja.getIdFranjaHoraria().toString()).reduce((a, b) -> a + ", " + b).orElse("Ninguno")
                            : "Ninguno"));
        }**/
    }

    public void vincularFranjaHorariaEspacioFisico(){
        var franjasOpt = franjaHorariaRepository.findAll();
        var espaciosOpt = espacioFisicoRepository.findAll();
        if (franjasOpt.isEmpty() || espaciosOpt.isEmpty()) return;

        var franjas = new ArrayList<>(franjasOpt.get());
        var espacios = new ArrayList<>(espaciosOpt.get());

        // Vinculación coherente: cada franja se asocia a un espacio físico y cada espacio a varias franjas
        if (franjas.size() >= 3 && espacios.size() >= 5) {
            franjas.get(0).setEspacioFisico(espacios.get(1)); // Franja 1 -> Espacio 2
            franjas.get(1).setEspacioFisico(espacios.get(3)); // Franja 2 -> Espacio 4
            franjas.get(2).setEspacioFisico(espacios.get(2)); // Franja 3 -> Espacio 3

            // Cada espacio tendrá una lista de franjas
            espacios.get(1).setFranjasHorarias(Arrays.asList(franjas.get(0)));
            espacios.get(3).setFranjasHorarias(Arrays.asList(franjas.get(1)));
            espacios.get(2).setFranjasHorarias(Arrays.asList(franjas.get(2)));

            // Actualizar espacios y franjas en el repositorio
            for (int i = 0; i < 3; i++) {
                franjaHorariaRepository.update(franjas.get(i).getIdFranjaHoraria(), franjas.get(i));
                espacioFisicoRepository.update(espacios.get(i).getIdEspacioFisico(), espacios.get(i));
            }
        }
        /** 
        System.out.println("-------------Espacios físicos vinculados a franjas horarias-------------");
        for (var espacio : espacios) {
            System.out.println("Espacio: " + espacio.getNombre() + " - Franja: " +
                    (espacio.getFranjasHorarias() != null ? espacio.getFranjasHorarias().stream()
                            .map(franja -> franja.getIdFranjaHoraria().toString()).reduce((a, b) -> a + ", " + b).orElse("Ninguno")
                            : "Ninguno"));
        }
        System.out.println("-------------Franjas horarias vinculadas a espacios físicos-------------");
        for (var franja : franjas) {
            System.out.println("Franja: " + franja.getIdFranjaHoraria() + " - Espacio: " +
                    (franja.getEspacioFisico() != null ? franja.getEspacioFisico().getNombre() : "Ninguno"));
        }*/
    }

    public void vincularFranjaHorariaDocente(){
        var franjasOpt = franjaHorariaRepository.findAll();
        var docentesOpt = docenteRepository.findAll();
        if (franjasOpt.isEmpty() || docentesOpt.isEmpty()) return;

        var franjas = new ArrayList<>(franjasOpt.get());
        var docentes = new ArrayList<>(docentesOpt.get());

        // Vinculación coherente: cada franja se asocia a uno o varios docentes y cada docente a varias franjas
        if (franjas.size() >= 3 && docentes.size() >= 5) {
            franjas.get(0).setDocentes(Arrays.asList(docentes.get(0)));
            franjas.get(1).setDocentes(Arrays.asList(docentes.get(4)));
            franjas.get(2).setDocentes(Arrays.asList(docentes.get(2)));

            // Cada docente tendrá una lista de franjas 
            docentes.get(0).setFranjasHorarias(Arrays.asList(franjas.get(0)));
            docentes.get(4).setFranjasHorarias(Arrays.asList(franjas.get(1)));
            docentes.get(2).setFranjasHorarias(Arrays.asList(franjas.get(2)));

            // Actualizar docentes y franjas en el repositorio
            for (int i = 0; i < 3; i++) {
                franjaHorariaRepository.update(franjas.get(i).getIdFranjaHoraria(), franjas.get(i));
                docenteRepository.update(docentes.get(i).getIdDocente(), docentes.get(i));
            }
        }
        /**
        System.out.println("-------------Docentes vinculados a franjas horarias-------------");
        for (var docente : docentes) {
            System.out.println("Docente: " + docente.getNombres() + " - Franja: " +
                    (docente.getFranjasHorarias() != null ? docente.getFranjasHorarias().stream()
                            .map(franja -> franja.getIdFranjaHoraria().toString()).reduce((a, b) -> a + ", " + b).orElse("Ninguno")
                            : "Ninguno"));
        }
        System.out.println("-------------Franjas horarias vinculadas a docentes-------------");
        for (var franja : franjas) {
            System.out.println("Franja: " + franja.getIdFranjaHoraria() + " - Docente: " +
                    (franja.getDocentes() != null ? franja.getDocentes().stream()
                            .map(docente -> docente.getIdDocente().toString()).reduce((a, b) -> a + ", " + b).orElse("Ninguno")
                            : "Ninguno"));
        }*/
    }

}

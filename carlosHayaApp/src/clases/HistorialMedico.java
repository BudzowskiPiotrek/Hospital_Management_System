package clases;

import java.util.ArrayList;
import java.util.List;

public class HistorialMedico {
    private List<String> consultas;
    private List<String> diagnosticos;
    private List<String> intervenciones;
    private List<String> recetas;

    public HistorialMedico() {
        this.consultas = new ArrayList<>();
        this.diagnosticos = new ArrayList<>();
        this.intervenciones = new ArrayList<>();
        this.recetas = new ArrayList<>();
    }

    public List<String> getConsultas() {
        return consultas;
    }

    public List<String> getDiagnosticos() {
        return diagnosticos;
    }

    public List<String> getIntervenciones() {
        return intervenciones;
    }

    public List<String> getRecetas() {
        return recetas;
    }
}

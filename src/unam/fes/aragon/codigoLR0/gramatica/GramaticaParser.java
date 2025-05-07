package unam.fes.aragon.codigoLR0.gramatica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// los cometarios se dejaron para que sea más facil leer la Documentacion
public class GramaticaParser {
    private final Map<String, List<String[]>> gramatica = new LinkedHashMap<>();
    private String simboloInicial = "";

    public GramaticaParser() {
    }

    public GramaticaParser(String archivoGramatica) {
        cargarGramatica(archivoGramatica);
    }

    public void cargarGramatica(String archivo) {
        gramatica.clear();
        simboloInicial = "";

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("->") && simboloInicial.isEmpty()) {
                    simboloInicial = linea.split("->")[0].trim();
                }

                String[] partes = linea.split("->");
                String noTerminal = partes[0].trim();
                String[] alternativas = partes[1].split("\\|");

                List<String[]> producciones = new ArrayList<>();
                for (String alt : alternativas) {
                    String[] simbolos = alt.trim().split("\\s+");
                    producciones.add(simbolos);
                }

                gramatica.put(noTerminal, producciones);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo de gramática: " + e.getMessage(), e);
        }
    }

    public Map<String, List<String[]>> getGramatica() {
        return gramatica;
    }

    public String getSimboloInicial() {
        return simboloInicial;
    }

    public Map<String, List<String[]>> construirGramaticaAumentada() {
        if (simboloInicial.isEmpty() || gramatica.isEmpty()) {
            throw new IllegalStateException("Primero debe cargarse una gramática con cargarGramatica().");
        }

        Map<String, List<String[]>> gramaticaAumentada = new LinkedHashMap<>();
        String nuevoSimboloInicial = "S'";
        List<String[]> produccionesIniciales = new ArrayList<>();
        produccionesIniciales.add(new String[]{simboloInicial});
        gramaticaAumentada.put(nuevoSimboloInicial, produccionesIniciales);
        gramaticaAumentada.putAll(gramatica);

        return gramaticaAumentada;
    }
}
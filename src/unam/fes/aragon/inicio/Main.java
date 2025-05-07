package unam.fes.aragon.inicio;

import unam.fes.aragon.codigoLR0.gramatica.GramaticaParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String archivoGramatica = "Gramatica.txt";
        String simboloInicial = "";

        try (BufferedReader br = new BufferedReader(new FileReader(archivoGramatica))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("->")) {
                    simboloInicial = linea.split("->")[0].trim();
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de gramática: " + e.getMessage());
            return;
        }

        if (simboloInicial.isEmpty()) {
            System.err.println("No se encontró un símbolo inicial en el archivo de gramática.");
            return;
        }

        GramaticaParser parser = new GramaticaParser();
        Map<String, List<String[]>> gramatica = parser.parsear(archivoGramatica);
    }
}
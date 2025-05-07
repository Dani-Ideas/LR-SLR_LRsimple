package unam.fes.aragon.inicio;

import unam.fes.aragon.codigoLR0.gramatica.GramaticaParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String archivoGramatica = "gramatica.txt";

        File archivo = new File("src/unam/fes/aragon/recursos", archivoGramatica);

        if (!archivo.exists()) {
            System.err.println("ERROR: Archivo no encontrado en: " + archivo.getAbsolutePath());
            return;
        }

        String simboloInicial = "";
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("->")) {
                    simboloInicial = linea.split("->")[0].trim();
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo archivo: " + e.getMessage());
            return;
        }

        GramaticaParser gramatica = new GramaticaParser(archivo.getAbsolutePath());

        for (Map.Entry<String, List<String[]>> entry : gramatica.construirGramaticaAumentada().entrySet()) {
            System.out.print(entry.getKey() + " -> ");

            List<String[]> producciones = entry.getValue();
            for (int i = 0; i < producciones.size(); i++) {
                String[] simbolos = producciones.get(i);
                // Unir los símbolos con espacios
                System.out.print(String.join(" ", simbolos));

                // Agregar | entre producciones, pero no al final
                if (i < producciones.size() - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();  // Nueva línea para cada no terminal
        }

    }
}
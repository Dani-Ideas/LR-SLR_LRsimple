package unam.fes.aragon.codigoLR0.tablas;

import unam.fes.aragon.codigoLR0.elemnetosTablas.ElementoLR0;

import java.util.ArrayList;
import java.util.List;

public class Tabla_analisis_sintacticoG {
    /*
    public String accion(int estado, String simbolo) {
        if (tablaAccion[estado][simbolo] == "desplazar") {
            return "desplazar";
        } else if (tablaAccion[estado][simbolo] == "reducir") {
            return "reducir";
        } else if (tablaAccion[estado][simbolo] == "aceptar") {
            return "aceptar";
        } else {
            return "error";
        }
    }

    public List<ElementoLR0> irA(List<ElementoLR0> conjunto, String simbolo) {
        List<ElementoLR0> nuevoConjunto = new ArrayList<>();
        for (ElementoLR0 elemento : conjunto) {
            if (elemento.ladoDerecho.charAt(elemento.punto) == simbolo.charAt(0)) {
                nuevoConjunto.add(new ElementoLR0(elemento.ladoIzquierdo,
                        elemento.ladoDerecho,
                        elemento.punto + 1));
            }
        }
        return nuevoConjunto;
    }

    // Función para generar las tablas de análisis
    public void generarTablas() {
        // Generar estados y transiciones
        for (int i = 0; i < In.size(); i++) {
            List<ElementoLR0> estadoActual = In.get(i);
            for (String simbolo : terminales) {
                List<ElementoLR0> nuevoEstado = irA(estadoActual, simbolo);
                if (!nuevoEstado.isEmpty()) {
                    // Agregar nuevo estado e actualizar tabla ACCION
                    int nuevoEstadoID = obtenerEstadoID(nuevoEstado);
                    tablaAccion[i][simbolo] = "desplazar " + nuevoEstadoID;
                }
            }
        }

        // Generar reducciones y otras acciones
        for (int i = 0; i < In.size(); i++) {
            for (ElementoLR0 elemento : In.get(i)) {
                if (elemento.punto == elemento.ladoDerecho.length()) {
                    // Aplicar reducción
                    tablaAccion[i][elemento.ladoIzquierdo] = "reducir " + elemento.ladoIzquierdo + " → " + elemento.ladoDerecho;
                }
            }
        }
    }
    */

}

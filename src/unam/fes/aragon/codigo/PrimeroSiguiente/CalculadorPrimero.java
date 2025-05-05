package unam.fes.aragon.codigo.PrimeroSiguiente;

import java.util.*;

public class CalculadorPrimero {
    private Map<String, List<String[]>> gramatica;
    private Map<String, Set<String>> primero;

    public CalculadorPrimero(Map<String, List<String[]>> gramatica) {
        this.gramatica = gramatica;
        this.primero = new HashMap<>();
        inicializar();
        calcular();
    }

    private void inicializar() {
        for (String noTerminal : gramatica.keySet()) {
            primero.put(noTerminal, new HashSet<>());
        }
    }

    private void calcular() {
        boolean cambiado;

        do {
            cambiado = false;
            for (String noTerminal : gramatica.keySet()) {
                for (String[] produccion : gramatica.get(noTerminal)) {
                    for (int i = 0; i < produccion.length; i++) {
                        String simbolo = produccion[i];

                        if (!gramatica.containsKey(simbolo)) {
                            // Terminal
                            if (primero.get(noTerminal).add(simbolo)) {
                                cambiado = true;
                            }
                            break;
                        } else {
                            // No terminal
                            Set<String> primeroSimbolo = primero.get(simbolo);
                            for (String t : primeroSimbolo) {
                                if (!t.equals("ε") && primero.get(noTerminal).add(t)) {
                                    cambiado = true;
                                }
                            }
                            if (!primeroSimbolo.contains("ε")) break;
                            if (i == produccion.length - 1) {
                                if (primero.get(noTerminal).add("ε")) {
                                    cambiado = true;
                                }
                            }
                        }
                    }
                }
            }
        } while (cambiado);
    }

    public Set<String> obtenerPrimero(String noTerminal) {
        return primero.get(noTerminal);
    }

    public Map<String, Set<String>> obtenerTodos() {
        return primero;
    }
}

package unam.fes.aragon.codigo.PrimeroSiguiente;

import java.util.*;

public class CalculadorSiguiente {
    private Map<String, List<String[]>> gramatica;
    private Map<String, Set<String>> siguiente;
    private Map<String, Set<String>> primero;

    public CalculadorSiguiente(Map<String, List<String[]>> gramatica, Map<String, Set<String>> primero) {
        this.gramatica = gramatica;
        this.primero = primero;
        this.siguiente = new HashMap<>();
        inicializar();
        calcular();
    }

    private void inicializar() {
        for (String noTerminal : gramatica.keySet()) {
            siguiente.put(noTerminal, new HashSet<>());
        }
        // Agregar $ al símbolo inicial
        String simboloInicial = gramatica.keySet().iterator().next();
        siguiente.get(simboloInicial).add("$");
    }

    private void calcular() {
        boolean cambiado;

        do {
            cambiado = false;
            for (Map.Entry<String, List<String[]>> regla : gramatica.entrySet()) {
                String A = regla.getKey();
                for (String[] produccion : regla.getValue()) {
                    for (int i = 0; i < produccion.length; i++) {
                        String B = produccion[i];
                        if (!gramatica.containsKey(B)) continue; // Solo no terminales

                        Set<String> siguienteB = siguiente.get(B);
                        int originalSize = siguienteB.size();

                        // Caso 1: Símbolos después de B
                        boolean epsilonEnTodos = true;
                        for (int j = i + 1; j < produccion.length; j++) {
                            String simbolo = produccion[j];
                            Set<String> primeroSimbolo = primero.getOrDefault(simbolo, Set.of(simbolo));
                            siguienteB.addAll(sinEpsilon(primeroSimbolo));
                            if (!primeroSimbolo.contains("ε")) {
                                epsilonEnTodos = false;
                                break;
                            }
                        }

                        // Caso 2: Si todo lo siguiente deriva en ε, agrega Siguiente(A)
                        if (i == produccion.length - 1 || epsilonEnTodos) {
                            siguienteB.addAll(siguiente.get(A));
                        }

                        if (siguienteB.size() > originalSize) {
                            cambiado = true;
                        }
                    }
                }
            }
        } while (cambiado);
    }

    private Set<String> sinEpsilon(Set<String> conjunto) {
        Set<String> resultado = new HashSet<>(conjunto);
        resultado.remove("ε");
        return resultado;
    }

    public Set<String> obtenerSiguiente(String noTerminal) {
        return siguiente.get(noTerminal);
    }

    public Map<String, Set<String>> obtenerTodos() {
        return siguiente;
    }
}

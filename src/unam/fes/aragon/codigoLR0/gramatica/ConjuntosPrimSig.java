package unam.fes.aragon.codigoLR0.gramatica;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConjuntosPrimSig {
    public static Map<String, Set<String>> primero = new HashMap<>();
    public static Map<String, Set<String>> siguiente = new HashMap<>();

    static {
        primero.put("E", new HashSet<>(Arrays.asList("(", "id")));
        primero.put("T", new HashSet<>(Arrays.asList("(", "id")));
        primero.put("F", new HashSet<>(Arrays.asList("(", "id")));

        siguiente.put("E", new HashSet<>(Arrays.asList("$", "+", ")")));
        siguiente.put("T", new HashSet<>(Arrays.asList("$", "+", ")", "*")));
        siguiente.put("F", new HashSet<>(Arrays.asList("$", "+", ")", "*")));
    }
}

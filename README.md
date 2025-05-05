 Lista de Tareas para completar el proyecto:
Fase inicial:

Definir la gramática base en Cup.

Aumentar la gramática (añadir producción inicial con EOF) en Cup.

Definir los tokens en JFlex.

Definir la tabla de símbolos.

Calcular los conjuntos Primero y Siguiente de la gramática aumentada.

Fase intermedia:

Construir los conjuntos de elementos LR(0) o LR(1) (dependiendo del tipo de tabla que usarás: SLR, LALR, LR).

Desarrollar el algoritmo que construya la tabla LRs usando los conjuntos anteriores.

Usar estructuras como:

Conjuntos (Set) para almacenar los elementos de los items LR.

Mapas (HashMap) para transiciones.

Listas para almacenar los estados.

Pilas o colas para el análisis sintáctico si lo aplicarás luego con esa tabla.

Fase final:

Aplicar la tabla LRs en el análisis sintáctico.


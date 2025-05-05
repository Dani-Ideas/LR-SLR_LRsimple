Orden directa:

````md
# ✅ Lista de Tareas para completar el proyecto (versión mejorada)

## 🔹 Fase Inicial

### 1. ✅ Definición de la gramática

Se creó el objeto `GramaticaParser`, capaz de analizar una gramática libre de contexto desde un archivo de texto y transformarla en:

```java
Map<String, List<String[]>>
````

#### Ventajas:

* Eficiente lectura con `BufferedReader`.
* División por `->` y alternativas por `|`.
* Producciones convertidas en arrays de `String` para acceso rápido.

**Ejemplo:**

```txt
S -> A B | a
A -> a | ε
B -> b C
C -> c
```

Resultado:

```json
{
  "S": [["A","B"], ["a"]],
  "A": [["a"], ["ε"]],
  "B": [["b","C"]],
  "C": [["c"]]
}
```

**Ventajas de esta estructura:**

* Construcción de árboles sintácticos jerárquicos.
* Ejecución de derivaciones (acceso directo a producciones).
* Base para generar ítems LR y construir la tabla LR.
* Acceso rápido O(1), separación clara entre terminales y no terminales.
* Bajo overhead y fácil conversión a otras estructuras.

---

### 2. ✅ Creación del árbol sintáctico *(en evaluación de retiro)*

---

### 3. ✅ Selección del tipo de analizador

Se eligió el analizador ascendente **LR**, dado que el proyecto se titula *LRS*.

---

### 4. ✅ Método de pánico (`TError`)

---

### 5. ⛔ Análisis sintáctico de descenso recursivo descartado

---

### 6. ✅ Gramática aumentada

Agregado: producción inicial `S' → S` con EOF para aceptación explícita.

---

### 7. ✅ Definición de tokens en JFlex

---

### 8. 🔲 Definir tabla de símbolos

---

### 9. 🔲 Calcular conjuntos Primero y Siguiente

---

## 🔹 Fase Intermedia

### 10. 🔲 Generador de ítems LR(0)

Clase: `ItemLR`

* Representa producciones en forma: `A → α·β`.

---

### 11. 🔲 Cerradura (closure)

Clase: `ConstructorAutomataLR`

* Calcula las derivaciones inmediatas (expansión de no terminales tras el punto).

---

### 12. 🔲 Ir\_A (goto)

* Función que, dado un conjunto de ítems y un símbolo, retorna el conjunto resultante al mover el punto.

---

### 13. 🔲 Construcción del autómata LR(0)

Clase: `EstadoLR`

* Conjuntos de ítems LR
* Transiciones generadas por `goto`

---

### 14. 🔲 Construcción de la tabla LR (ACTION y GOTO)

Clase: `TablaAnalisisLR`

* Tabla ACTION: shift/reduce/accept
* Tabla GOTO: transiciones entre estados

Estructuras sugeridas:

```java
Map<Pair<Integer, String>, String> actionTable;
Map<Pair<Integer, String>, Integer> gotoTable;
```

---

## 🔹 Fase Final

### 15. 🔲 Algoritmo de análisis LR(0)

Clase: `AnalizadorLR`

* Pila de estados
* Manejo de entrada
* Consulta de la tabla y aplicación de acciones

---

### 16. 🔲 Manejador de errores

Clase: `ManejadorErrores`

* Rechazo al encontrar acción inválida
* Mensajes de error sintáctico

---

### 17. 🔲 (Opcional) Árbol sintáctico LR

* Construcción del árbol durante el análisis usando las reducciones
* Nodo raíz = producción inicial aumentada `S' → S`

---

## 🔸 Organización de clases sugerida

| Clase Java                   | Función principal                        |
| ---------------------------- | ---------------------------------------- |
| `GramaticaParser`            | Lectura y estructuración de reglas       |
| `ItemLR`                     | Representación de ítems LR               |
| `EstadoLR`                   | Agrupación de ítems en estados           |
| `ConstructorAutomataLR`      | Construcción de autómata LR              |
| `TablaAnalisisLR`            | Generación de ACTION y GOTO              |
| `AnalizadorLR`               | Ejecución del análisis sintáctico        |
| `PrimeroSiguienteCalculator` | Cálculo de First y Follow                |
| `Lexer` (JFlex)              | Análisis léxico                          |
| `ManejadorErrores`           | Gestión y reporte de errores sintácticos |

---

```
```

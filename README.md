# ✅ Lista de Tareas para Completar el Proyecto

## 🧩 Fase Inicial

### 1. ✅ Definir la Gramática

Se creó el objeto `GramaticaParser`, capaz de analizar una gramática libre de contexto desde un archivo de texto y transformarla en una estructura de datos eficiente:

```
Map<String, List<String[]>>
```

#### Ventajas:

* **Lectura eficiente**: Utiliza `BufferedReader` para leer el archivo línea por línea.
* **Procesamiento estructurado**:

    * Divide cada línea en cabeza y cuerpo usando `->`.
    * Separa las alternativas del cuerpo utilizando `|`.
    * Cada alternativa se divide en símbolos individuales usando `split("\\s+")`.
* **Almacenamiento eficiente**: Cada producción se guarda como un arreglo de `String` para acceso rápido por índice.

#### Ejemplo:

Archivo de entrada (`gramatica.txt`):

```
S -> A B | a
A -> a | ε
B -> b C
C -> c
```

Estructura resultante:

```
{
  "S": [["A", "B"], ["a"]],
  "A": [["a"], ["ε"]],
  "B": [["b", "C"]],
  "C": [["c"]]
}
```

#### Beneficios de esta estructura:

* **Construcción de árboles sintácticos**: Facilita la representación jerárquica de las producciones.
* **Derivaciones eficientes**: Permite acceder rápidamente a las producciones de cualquier no terminal.
* **Base para análisis LR**: Proporciona la información necesaria para generar ítems LR y construir la tabla de análisis.
* **Manejo de producciones ε**: Representa producciones vacías como arreglos vacíos o con el símbolo `"ε"` según la convención adoptada.

### 2. ✅ Creación del Árbol Sintáctico

* Se implementó la construcción del árbol sintáctico utilizando la estructura generada por `GramaticaParser`.
* Actualmente en evaluación para determinar su inclusión final en el proyecto.

### 3. ✅ Selección del Tipo de Analizador

* Se optó por implementar un analizador **ascendente (LR)**, alineado con el objetivo del proyecto y su denominación "LRS".

### 4. ✅ Inclusión del Método de Pánico: `TError`

* Se incorporó un mecanismo de recuperación ante errores sintácticos mediante el método `TError`.

### 5. ❌ Inclusión del Análisis Sintáctico de Descenso Recursivo

* Se descartó la implementación de un analizador de descenso recursivo, dado que el enfoque principal es el análisis ascendente.

### 6. ✅ Gramática Aumentada

* Se añadió una nueva producción inicial con EOF: `S' → E'`, donde `S` es el símbolo inicial original.

### 7. ✅ Definición de Tokens en JFlex

* Se definieron los tokens necesarios utilizando JFlex para el análisis léxico del lenguaje.

### 8. 🔲 Definición de la Tabla de Símbolos

* Errores con la implementación.

### 9. 🔲 Cálculo de los Conjuntos Primero y Siguiente

* Se requiere calcular los conjuntos **Primero** y **Siguiente** de la gramática aumentada para la construcción de la tabla de análisis.

## ⚙️ Fase Intermedia

### 10. 🔲 Generador de Ítems LR(0)

* Para cada producción, representar las formas con el punto (`·`) en diferentes posiciones, por ejemplo: `A → ·α`, `A → α·`.

### 11. 🔲 Cálculo de la Cerradura (Closure)

* Implementar el algoritmo que, dado un conjunto de ítems, agrega todos los derivados posibles con el punto al inicio.

### 12. 🔲 Función Ir\_A (Goto)

* Definir la función que indica el nuevo conjunto de ítems al mover el punto sobre un símbolo específico.

### 13. 🔲 Construcción del Autómata LR(0)

* Construir el autómata donde:

    * **Estados**: Conjuntos de ítems.
    * **Transiciones**: Definidas por la función Ir\_A para cada símbolo.

### 14. 🔲 Construcción de la Tabla de Análisis (ACTION y GOTO)

* Desarrollar la tabla con las acciones `shift`, `reduce`, `accept` según el autómata y las reglas de la gramática.

### 15. 🔲 Desarrollo del Algoritmo para Construir la Tabla LR

* Utilizar las estructuras generadas anteriormente para construir la tabla LR:

    * **Conjuntos (Set)**: Para almacenar los elementos de los ítems LR.
    * **Mapas (HashMap)**: Para las transiciones.
    * **Listas**: Para almacenar los estados.
    * **Pilas o Colas**: Para el análisis sintáctico posterior utilizando la tabla.

## 🧪 Fase Final

### 16. 🔲 Implementación del Algoritmo de Análisis LR(0)

* Desarrollar el algoritmo que utiliza la tabla LR para analizar cadenas de entrada:

    * **Pila de estados**: Para mantener el seguimiento de los estados durante el análisis.
    * **Manejo de entrada**: Procesar los tokens de entrada.
    * **Uso de la tabla**: Determinar las acciones a realizar (`shift`, `reduce`, `accept`).

### 17. 🔲 Manejador de Errores (Básico)

* Implementar un mecanismo básico de manejo de errores que rechace la entrada si no hay una acción válida en la tabla.

### 18. 🔲 (Opcional) Construcción del Árbol Sintáctico LR

* Aplicar la tabla LR en el análisis sintáctico para construir el árbol correspondiente.

---

## 📌 Notas Adicionales

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


* **Estructuras de Datos Utilizadas**:

    * `HashMap<String, List<String[]>>`: Para almacenar las producciones de la gramática.
    * `Set`: Para los conjuntos de ítems LR.
    * `List`: Para los estados del autómata.
    * `Stack` o `Queue`: Para el análisis sintáctico utilizando la tabla LR.

* **Convenciones Adoptadas**:

    * El símbolo `"ε"` representa producciones vacías.
    * Se utiliza `String[]` en lugar de `List<String>` para las producciones por eficiencia en el acceso por índice y menor overhead de memoria.

---


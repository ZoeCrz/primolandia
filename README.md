# Primolandia
Aplicación de consola Java que realiza división exacta entre números representados mediante factores primos y exponentes. Proyecto académico que muestra validación de entradas, uso de arreglos como pilas y razonamiento matemático.

## Características y alcance

- Divide números representados como factores primos sin convertirlos en enteros grandes.
- Comprueba el orden de los factores, la primalidad y que los exponentes permitan una división exacta.
- Muestra el procedimiento y el resultado en consola. Respeta las reglas didácticas del ejercicio y trabaja con enteros positivos.
- Créditos: Zoe Yeray Cruz Cruz, Edgar M. y Julián V.

## Ejecutar
Conserva Java y el proyecto NetBeans; usa JDK 23, versión configurada en el proyecto. No tiene dependencias externas.
```sh
mkdir -p out
javac -encoding UTF-8 -d out src/jvprimolandia/*.java
java -cp out jvprimolandia.JvPrimolandia
```
En Windows crea `out` con `mkdir out`. También puedes abrir la carpeta en NetBeans y ejecutar `JvPrimolandia`.

Selecciona la opción 3. Cada operando contiene parejas `primo exponente`, con primos en orden decreciente. Ejemplo: dividendo `5 4 2 3`, divisor `5 2 2 2`; resultado esperado `5 2 2 1`. Las entradas respetan las restricciones didácticas del programa y la división debe ser exacta. La opción 4 muestra los créditos del equipo.

## Organización
`JvPrimolandia.java` dirige la interacción; `clsProcesoPrimolandia.java` contiene operaciones y validaciones; `clsPrimolandia.java` representa cada factor. `nbproject/` y `build.xml` permiten trabajar en NetBeans/Ant.

Se corrigió el manejo de fin de entrada, espacios, opciones inválidas, números menores que 2 y desbordamiento al leer enteros. Numerador y denominador comparten el lector iterativo. La validación de división recorre ambos operandos ordenados, y la prueba de primalidad se detiene en la raíz cuadrada. Se compiló con `javac --release 23` y se verificaron diez escenarios de consola, incluidos división exacta, igualdad, factores ausentes, exponentes inválidos y entradas vacías o fuera de rango.

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jvprimolandia;

// Librerías de clase


/**
 *
 * @author zou3e
 */
public class clsProcesoPrimolandia {
    // 1.- DEFINICIÓN DE LOS ATRIBUTOS DE UN NODO DE OBJETOS
    
    static clsPrimolandia pilaNum[]; // Arreglo de objetos o nodos del numerador
    static clsPrimolandia pilaDen[]; // Arreglo de objetos o nodos del denominador
    static int limitePNum = 0; // Controla el número de elementos de la pila del numerador
    static int limitePDen = 0; // Controla el número de elementos de la pila del denominador
    clsPrimolandia elementoPila; // Generar un solo nodo de la estructura de la pila
    clsPrimolandia pilaAuxiliar[]; // Tener un arreglo de objetos o nodos auxiliar para pasar los nodos de la pila de forma temporal y poder redimensionar
    
    // 2.- VARIABLES DE LA CLASE

    int primExp = 0;
    int banTry = 0;
    /*
    banTry == 1 --> evaluación del orden decreciente en elementoPila.nPrimo
    banTry == 2 --> evaluación de factor primo y rango 2 - 231 en elementoPila.nPrimo
    banTry == 3 --> evaluación de rango 2 - 231 en elementoPila.exponente
    banTry == 4 --> validación para que el operador ingresado no esté incompleto
    banTry == 5 --> validación de caracteres diferentes a números o espacios en blanco en operador
    banTry == 6 --> valida que el exponente del factor encontrado en el denominador no sea mayor al del numerador
    banTry == 7 --> valida que todos los elementos del denominador estén en el numerador
    */
    
    // 3.- PROPIEDADES DE LECTURA Y ESCRITURA: get & set
    
    
    
    // 4.- CONSTRUCTOR DE LA CLASE
    
    
    
    // 5.- MÉTODOS DE LA CLASE
    
    // Método para mostrar el menú de opciones principal
    public void menuOpciones(){
        System.out.println("\n\t\033[35m.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.");
        System.out.println("\t\033[35m   ~~~~~~~ PRIMOLANDIA ~~~~~~~");
        System.out.println("\t\033[35m.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.");
        System.out.println("\n\t\033[34mOperaciones disponibles:");
        System.out.println("\n\t\033[35m1.\033[34m Descripción de la problemática");
        System.out.println("\t\033[35m2.\033[34m Descripción de la solución");
        System.out.println("\t\033[35m3.\033[34m Ejecución de la solución");
        System.out.println("\t\033[35m4.\033[34m Acerca de los autores");
        System.out.println("\t\033[35m5.\033[34m Salir.\n");
        System.out.print("\t\033[30mOpción: ");
    }
    
    // MÉTODOS Y FUNCIONES PARA AGREGAR ELEMENTOS AL NUMERADOR

    // Método para agregar elementos a la pila del numerador
    public void agregarNumerador(){
        // Antes de agregar un nuevo elemento a la pila, se verifica que se cumpla el orden decreciente de los factores
        if(limitePNum > 0 && elementoPila.getnPrimo() >= pilaNum[limitePNum - 1].getnPrimo()){
            // Si existe un error, se llama al método de reinicio y se envía bandera y mensaje de error
            reiniciarNum();
            banTry = 1;
            System.out.println("\n\033[31m[Error] -> \033[35mEl orden decreciente en los\n\033[35mfactores primos \033[31mNO\033[35m se ha respetado o un\n\033[35mfactor primo ha sido repetido...");
            return; // Se sale del método sin agregar nada
        }
        // Si el nuevo factor respeta el orden decreciente, se continúa agregándolo
        limitePNum++;
        if(limitePNum == 1){ // 1. Cuando la pila está vacía, se agrega directamente el nodo a la pila
            pilaNum = new clsPrimolandia[limitePNum];
            pilaNum[0] = elementoPila;
        }
        else{ // 2. Cuando la pila tiene 1 o más elementos
            pilaAuxiliar = pilaNum; // 2.1 Vaciar la pila en el arreglo auxiliar
            pilaNum = null; // 2.2 Borrar y redimensionar dinámicamente la pila principal
            pilaNum = new clsPrimolandia[pilaAuxiliar.length + 1]; // 2.3 Crear el nuevo arreglo con un nodo adicional
            // 3. Regresar los elementos a la pila principal
            for(int i = 0; i < pilaNum.length; i++){
                if(i < pilaAuxiliar.length){ // Se reacomodan los elementos de la pila auxiliar
                    pilaNum[i] = pilaAuxiliar[i];
                }else{ // Agregar el nuevo nodo al final
                    pilaNum[i] = elementoPila;
                }
            }
            pilaAuxiliar = null; // 4. Eliminar los valores de la pila auxiliar 
        } // Fin del else
    }
    
    // Método para evaluar si el número extraído de la cadena, es candidato a ser nPrimo o exponente
    public void validacionNum(int num){
        if(primExp == 0){ // Para evaluar que el número extraído es nPrimo, la variable primExp debe ser 0
            // Se evalúa que nPrimo siga las reglas de negocio (número primo y rango 2-231) llamando a la funcion evaluacionNPrimo() y haciendo comparaciones
            if((num > 1 && num < 232) && (evaluacionNPrimo(num, num - 1) == 1)){
                if(banTry == 0){
                    elementoPila = new clsPrimolandia(); // Se instancia la clase clsPrimolandia para poder ingresarla a la pila
                    elementoPila.setnPrimo(num); // Se asigna el número extraído de la cadena al atributo nPrimo
                }
                primExp = 1; // Se cambia el valor de primExp para indicar que el siguiente número extraído NO será nPrimo
            }else{
                // Si existe un error, se llama al método de reinicio y se envía bandera y mensaje de error mostrando los números que no cumplen con la regla definida
                reiniciarNum();
                banTry = 2;
                primExp = 1; // Se cambia el valor de primExp para que el siguiente número a evaluar sea el exponente aunque haya existido un error, para poder mostrar si es que existen más
                System.out.println("\n\033[31m[Error] -> \033[35mEl número [\033[34m"+num+"\033[35m] que se supone\n\033[35msería factor primo \033[31mNO\033[35m respeta la regla\n\033[35mde ser primo o estar dentro del rango de\n\033[35mnúmeros 2 - 231...");
            }
        }else{ // Ya que el valor de primExp es igual a 1, el siguiente número extraído es exponente
            // Se evalúa que exponente siga las reglas de negocio (rango 2-231) haciendo comparaciones
            if(num > 1 && num < 232){
                if(banTry == 0){ // Sólo si no ha habido ninguna bandera de error, se asigna un exponente al elemento que irá a la pila
                    elementoPila.setExponente(num); // Se asigna el número extraído de la cadena al atributo exponente
                    agregarNumerador(); // Se llama al método agregarNumerador() para agregar el elemento que se acaba de crear
                }
                primExp = 0; // Se cambia el valor de primExp para indicar que el siguiente número extraído será nPrimo
            }else{
                // Si existe un error, se llama al método de reinicio y se envía bandera y mensaje de error mostrando los números que no cumplen con la regla definida
                reiniciarNum();
                banTry = 3;
                System.out.println("\n\033[31m[Error] -> \033[35mEl número [\033[34m"+num+"\033[35m] que se supone\n\033[35msería exponente \033[31mNO\033[35m respeta la regla de\n\033[35mestar dentro del rango de números\n\033[35m2 - 231...");
            }
        }
    }
    
    // Función principal en la que se estará separando la cadena ingresada, evaluando y agregando a la pila
    public int operPrimolandiaNum(String operador, int inicio) {
        return leerOperando(operador.substring(inicio), this::validacionNum);
    }
    
    // MÉTODOS Y FUNCIONES PARA AGREGAR ELEMENTOS AL DENOMINADOR

    // Método para agregar elementos a la pila del denominador
    public void agregarDenominador(){
        // Antes de agregar un nuevo elemento a la pila, se verifica que se cumpla el orden decreciente de los factores
        if(limitePDen > 0 && elementoPila.getnPrimo() >= pilaDen[limitePDen - 1].getnPrimo()){
            // Si existe un error, se llama al método de reinicio y se envía bandera y mensaje de error
            reiniciarDen();
            banTry = 1;
            System.out.println("\n\033[31m[Error] -> \033[35mEl orden decreciente en los\n\033[35mfactores primos \033[31mNO\033[35m se ha respetado o un\n\033[35mfactor primo ha sido repetido...");
            return; // Se sale del método sin agregar nada
        }
        // Si el nuevo factor respeta el orden decreciente, se continúa agregándolo
        limitePDen++;
        if(limitePDen == 1){ // 1. Cuando la pila está vacía, se agrega directamente el nodo a la pila
            pilaDen = new clsPrimolandia[limitePDen];
            pilaDen[0] = elementoPila;
        }
        else{ // 2. Cuando la pila tiene 1 o más elementos
            pilaAuxiliar = pilaDen; // 2.1 Vaciar la pila en el arreglo auxiliar
            pilaDen = null; // 2.2 Borrar y redimensionar dinámicamente la pila principal
            pilaDen = new clsPrimolandia[pilaAuxiliar.length + 1]; // 2.3 Crear el nuevo arreglo con un nodo adicional
            // 3. Regresar los elementos a la pila principal
            for(int i = 0; i < pilaDen.length; i++){
                if(i < pilaAuxiliar.length){ // Se reacomodan los elementos de la pila auxiliar
                    pilaDen[i] = pilaAuxiliar[i];
                }else{ // Agregar el nuevo nodo al final
                    pilaDen[i] = elementoPila;
                }
            }
            pilaAuxiliar = null; // 4. Eliminar los valores de la pila auxiliar 
        } // Fin del else
    }
    
    // Método para evaluar si el número extraído de la cadena, es candidato a ser nPrimo o exponente
    public void validacionDen(int num){
        if(primExp == 0){ // Para evaluar que el número extraído es nPrimo, la variable primExp debe ser 0
            // Se evalúa que nPrimo siga las reglas de negocio (número primo y rango 2-231) llamando a la funcion evaluacionNPrimo() y haciendo comparaciones
            if((num > 1 && num < 232) && (evaluacionNPrimo(num, num - 1) == 1)){
                if(banTry == 0){
                    elementoPila = new clsPrimolandia(); // Se instancia la clase clsPrimolandia para poder ingresarla a la pila
                    elementoPila.setnPrimo(num); // Se asigna el número extraído de la cadena al atributo nPrimo
                }
                primExp = 1; // Se cambia el valor de primExp para indicar que el siguiente número extraído NO será nPrimo
            }else{
                // Si existe un error, se llama al método de reinicio y se envía bandera y mensaje de error mostrando los números que no cumplen con la regla definida
                reiniciarDen();
                banTry = 2;
                primExp = 1; // Se cambia el valor de primExp para que el siguiente número a evaluar sea el exponente aunque haya existido un error, para poder mostrar si es que existen más
                System.out.println("\n\033[31m[Error] -> \033[35mEl número [\033[34m"+num+"\033[35m] que se supone\n\033[35msería factor primo \033[31mNO\033[35m respeta la regla\n\033[35mde ser primo o estar dentro del rango de\n\033[35mnúmeros 2 - 231...");
            }
        }else{ // Ya que el valor de primExp es igual a 1, el siguiente número extraído es exponente
            // Se evalúa que exponente siga las reglas de negocio (rango 2-231) haciendo comparaciones
            if(num > 1 && num < 232){
                if(banTry == 0){ // Sólo si no ha habido ninguna bandera de error, se asigna un exponente al elemento que irá a la pila
                    elementoPila.setExponente(num); // Se asigna el número extraído de la cadena al atributo exponente
                    agregarDenominador(); // Se llama al método agregarNumerador() para agregar el elemento que se acaba de crear
                }
                primExp = 0; // Se cambia el valor de primExp para indicar que el siguiente número extraído será nPrimo
            }else{
                // Si existe un error, se llama al método de reinicio y se envía bandera y mensaje de error mostrando los números que no cumplen con la regla definida
                reiniciarDen();
                banTry = 3;
                System.out.println("\n\033[31m[Error] -> \033[35mEl número [\033[34m"+num+"\033[35m] que se supone\n\033[35msería exponente \033[31mNO\033[35m respeta la regla de\n\033[35mestar dentro del rango de números\n\033[35m2 - 231...");
            }
        }
    }
    
    // Función principal en la que se estará separando la cadena ingresada, evaluando y agregando a la pila
    public int operPrimolandiaDen(String operador, int inicio) {
        return leerOperando(operador.substring(inicio), this::validacionDen);
    }
    
    private int leerOperando(String entrada, java.util.function.IntConsumer validarValor) {
        if (entrada.isBlank()) return 0;
        String[] valores = entrada.trim().split("\\s+");
        if (valores.length % 2 != 0) {
            banTry = 4;
            System.out.println("Falta el exponente para el último factor primo.");
            return 0;
        }
        int numero = 0;
        for (String valor : valores) {
            if (!valor.matches("[0-9]+")) {
                banTry = 5;
                System.out.println("El operando solo debe contener números enteros.");
                return 0;
            }
            try {
                numero = Integer.parseInt(valor);
            } catch (NumberFormatException error) {
                banTry = 5;
                System.out.println("Cada valor debe estar entre 2 y 231.");
                return 0;
            }
            validarValor.accept(numero);
            if (banTry != 0) return numero;
        }
        return numero;
    }

    // Método para validar que la división es posible de hacer
    public void validarDivision(){
        int numerador = 0;
        for (int divisor = 0; divisor < limitePDen; divisor++) {
            int primo = pilaDen[divisor].getnPrimo();
            while (numerador < limitePNum && pilaNum[numerador].getnPrimo() > primo) {
                numerador++;
            }
            if (numerador == limitePNum || pilaNum[numerador].getnPrimo() != primo) {
                banTry = 7;
                System.out.println("Todos los factores primos del divisor deben estar en el dividendo.");
                return;
            }
            if (pilaDen[divisor].getExponente() > pilaNum[numerador].getExponente()) {
                banTry = 6;
                System.out.println("El exponente del divisor no puede superar al del dividendo.");
                return;
            }
        }
    }
        
    // Función recursiva para realizar la división y mostrar de manera desglozada la operación
    public void realizarDivision(){
        
        if(pilaDen.length == 0){ // Cuando la pila del denominador esté vacía se termina la función
            return;
        }
        // Mostrar el estado actual antes de modificar las pilas
        System.out.println();
        System.out.print("\t\t\033[34m");
        listarElementosPNum();
        System.out.println("\t\t\033[31m--------------");
        System.out.print("\t\t\033[34m");
        listarElementosPDen();
        System.out.println();
        for(int i = 0; i < pilaNum.length; i++){ // Se recorre el numerador buscando coincidencia con el último elemento del denominador
            if(pilaNum[i].getnPrimo() == pilaDen[pilaDen.length - 1].getnPrimo()){ 
                // Si coincide, restamos el exponente
                pilaNum[i].exponente -= pilaDen[pilaDen.length - 1].getExponente();
                break; // Salimos porque solo hay una coincidencia por factor primo
            }
        }
        // Eliminar el último elemento de pilaDen para la siguiente iteración
        pilaAuxiliar = new clsPrimolandia[pilaDen.length - 1];
        for(int j = 0; j < pilaDen.length - 1; j++){
            pilaAuxiliar[j] = pilaDen[j];
        }
        pilaDen = pilaAuxiliar;
        // Llamada recursiva para continuar con la siguiente división
        realizarDivision();
    }

    public int evaluacionNPrimo(int num, int divisor) {
        if (num < 2) return 0;
        for (int candidato = Math.min(divisor, (int) Math.sqrt(num)); candidato >= 2; candidato--) {
            if (num % candidato == 0) return 0;
        }
        return 1;
    }

    // Método para mostrar los elementos de la pila del numerador
    public void listarElementosPNum(){
        boolean resUno = true; // Variable para controlar si el resultadó de la división será 1 o no
        for(int i = 0; i < pilaNum.length; i++){
            if(pilaNum[i].getExponente() != 0){ // Sólo se imprimirán los números que no tienen exponente 0
               System.out.print(pilaNum[i].getnPrimo()+" "+pilaNum[i].getExponente()+" ");
               resUno = false;
            }
        }
        if(resUno){ // Si la variable termina como true significa que todos los exponentes resultaron en 0
            System.out.print("1 (Todos los exponentes\n\t\t\033[32mse redujeron a 0)"); // Se imprime explícitamente que el resultado es 1
        }
        System.out.println();
    }

    // Método para mostrar los elementos de la pila del denominador
    public void listarElementosPDen(){
        for(int i = 0; i < pilaDen.length; i++){
            System.out.print(pilaDen[i].getnPrimo()+" "+pilaDen[i].getExponente()+" ");
        }
    }
    
    // Método para reiniciar la pila del numerador y su límite en caso de error
    public void reiniciarNum(){
        pilaNum = null;
        limitePNum = 0;
        primExp = 0;
    }
    
    // Método para reiniciar la pila del denominador y su límite en caso de error
    public void reiniciarDen(){
        pilaDen = null;
        limitePDen = 0;
        primExp = 0;
    }

}
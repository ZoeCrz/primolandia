/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jvprimolandia;

// Librerías de clase
import java.util.Scanner;

/**
 *
 * @author zou3e
 */
public class JvPrimolandia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        // 1.- DECLARACIÓN DE VARIABLES LOCALES
        
        Scanner valores = new Scanner(System.in); // Objeto que se comunica con la entrada dde datos
        int op = 0;
        // Instanciar o crear copia de la clase de procesos
        clsProcesoPrimolandia objProcesoPL = new clsProcesoPrimolandia();
        
        String numerador = "", denominador = "";
                
        do{
            try{
                
        // 2.- CONFIGURACIÓN DE LA CONSOLA
        
                System.out.print("\033[H\033[2J"); // Esta linea hace que se limpie la consola
                System.out.flush(); // Esta linea hace que el cursor reaparezca en la consola
                
        // 3.- LECTURA DE DATOS
        
                objProcesoPL.menuOpciones(); // Se manda a llamar al método para imprimir el menú de opciones
                if (!valores.hasNextLine()) break;
                op = Integer.parseInt(valores.nextLine().trim()); // // Limpia el buffer antes de leer el String
                    
        // 4.- PROCESOS PRINCIPALES DEL PROGRAMA
        
                switch(op){
                    case 1: // Descripción de la problemática
                        System.out.println("\n\t\033[35m~ DESCRIPCIÓN DE LA PROBLEMÁTICA ~\n");
                        System.out.print("\033[30m   La problemática principal que se ha\n\033[30midentificado en Primolandia es la\n\033[30mdificultad para agilizar operaciones como\n\033[30mla división entera en su sistema de\n\033[30mnumeración basado en factores primos, lo\n\033[30mque ralentiza el aprendizaje matemático de\n\033[30msus habitantes.\n");
                        break;
                    case 2: // Descripción de la solución
                        System.out.println("\n\t\033[35m~ DESCRIPCIÓN DE LA SOLUCIÓN ~\n");
                        System.out.print("\033[30m   Se desarrolló un programa que automatiza\n\033[30mla división exacta en el sistema numérico de\n\033[30mPrimolandia. Este lee los operandos desde la\n\033[30mconsola, interpretando sus factores primos\n\033[30mordenados de forma decreciente. Además, se\n\033[30mimplementa una estructura de datos eficiente\n\033[30my metodos de validación para garantizar la\n\033[30mviabilidad de la operación. Finalmente,\n\033[30mpresenta el resultado en el formato adecuado,\n\033[30masegurando claridad y precisión.\n");
                        break;
                    case 3: // Ejecución de la solución
                        System.out.println("\n\t\033[35m~ DIVISIÓN EXACTA PRIMOLANDÉZA ~\n");
                        System.out.print("\t\033[31m¡¡¡\033[30mA continuación ingresa los\n\t\033[30moperandos de la división,\n\t\033[30mrecuerda seguir las reglas de\n\t\033[30mPrimolandia para poder obtener\n\t\033[30mel resultado esperado de la\n\t\033[30mdivisión exacta\033[31m!!!\n\t\033[31mEjemplo de operando válido:\n\t\033[31m[\033[30m17 5 11 10 5 2\033[31m]\n");
                        do{
                            do{ // Se preguntará el dividendo mientras se esté dejando vacío
                                objProcesoPL.reiniciarNum(); // Cada que se quiera ejecutar la solución del problema, se reinician las pilas necesarias y sus límites
                                objProcesoPL.banTry = 0;
                                System.out.print("\n\033[34mDividendo:\t");
                                if (!valores.hasNextLine()) return;
                                numerador = valores.nextLine().trim();
                                objProcesoPL.operPrimolandiaNum(numerador, 0); // Se llama a la función principal para llevar a cabo la descomposición del numerador
                                if(numerador.isEmpty()){ // Se valida que el dividendo no esté vacío
                                    System.out.println("\n\033[31m[Error] -> \033[33mEl dividendo \033[31mNO\033[33m pude estar vacío...");
                                }
                            }while(numerador.isEmpty() || objProcesoPL.banTry >= 1 && objProcesoPL.banTry <= 5);
                            do{ // Se preguntará el divisor mientras se esté dejando vacío
                                objProcesoPL.reiniciarDen(); // Cada que se quiera ejecutar la solución del problema, se reinician las pilas necesarias y sus límites
                                objProcesoPL.banTry = 0;
                                System.out.print("\n\033[34mDivisor:\t");
                                if (!valores.hasNextLine()) return;
                                denominador = valores.nextLine().trim();
                                objProcesoPL.operPrimolandiaDen(denominador, 0); // Se llama a la función principal para llevar a cabo la descomposición del denominador
                                if(denominador.isEmpty()){ // Se valida que el dividendo no esté vacío
                                    System.out.println("\n\033[31m[Error] -> \033[33mEl divisor \033[31mNO\033[33m pude estar vacío...");
                                }
                            }while(denominador.isEmpty() || objProcesoPL.banTry >= 1 && objProcesoPL.banTry <= 5);
                            objProcesoPL.validarDivision(); // Se llama al método de validación para la división 
                            if(objProcesoPL.banTry == 0){ // Si no existe problema alguno con los operandos de la división, se lleva a cabo la misma
                                // Se lleva a cabo la división y se va mostrando el proceso para llegar al resultado
                                System.out.print("\n\033[31mOperación... ");
                                objProcesoPL.realizarDivision();
                                // Se muestra el resultado de la división
                                System.out.print("\n\033[31mResultado: ");
                                System.out.print("\t\033[32m");
                                objProcesoPL.listarElementosPNum();  
                            }
                        }while(objProcesoPL.banTry == 6 || objProcesoPL.banTry == 7);
                        break;
                    case 4: // Acerca de los autores
                        System.out.println("\n\t\033[35m~ ACERCA DE LOS AUTORES ~\n");
                        System.out.print("\033[30mDesarrollado por:\n\033[33m-> \033[35mZoe Yeray Cruz Cruz \033[33m\n\033[33m--> \033[35mEdgar M. \033[33m\n\033[33m---> \033[35mJulián V. \033[33m\n");
                        break;
                    default:
                        if(op == 5){
                            break;
                        }else{
                           System.out.println("\n\033[31m[Error] -> \033[33mOpción \033[31mNO\033[33m válida, intenta de nuevo..."); 
                        }
                        break;
                }
            }
            catch(IllegalArgumentException ex){
                System.out.println("\n\033[33m[Error] -> \033[31mDebes ingresar solo números...");
                System.out.println(ex.getMessage());
            }
        }while(op != 5); // El cilo while terminará hasta que op == 5 es decir, se ingrese la opción salir   
    }
    
}
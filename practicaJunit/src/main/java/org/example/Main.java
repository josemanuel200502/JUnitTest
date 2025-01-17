package org.example;

import java.util.Scanner;

public class Main {

    static Scanner strings=new Scanner(System.in);
    static Scanner doubles=new Scanner(System.in);
    static Scanner ints=new Scanner(System.in);

    static SQLite BBDD=new SQLite();

    // variable que almacena al usuario

    static Usuario usuarioActual=null;

    public static void main(String[] args) {

        // Si el usuario existe, se logueará, si no existe se deberá registrar

        while(usuarioActual==null) usuarioActual = loginyRegistro(BBDD);

        gestion();

    }

    private static void gestion() {

        System.out.println("Bienvenido "+usuarioActual.getNombre()+", su saldo es de: "+String.format("%.2f",usuarioActual.getSaldo()));

        int opcion;

        do {
            System.out.println("""
                    ¿Qué desea hacer?
                    1.- Gastar 
                    2.- Ingresar
                    0.- Salir del programa
                    """);

            opcion = ints.nextInt();

            switch(opcion){
                case 1:
                    menuGastar();
                    break;
                case 2:
                    menuIngresar();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Esta opción no está contemplada...");
                    break;
            }

        } while (opcion != 0);
    }

    private static void menuIngresar() {

        int opcion;

        do {
            System.out.println("""
                    ¿Cuál será el ingreso?
                    1.- Nomina 
                    2.- Venta en páginas de segunda mano
                    0.- Volver atrás
                    """);

            opcion = ints.nextInt();

            switch(opcion) {
                case 1:
                    usuarioActual.ingresar(usuarioActual.getNomina() - (usuarioActual.getNomina() * 15 / 100));
                    break;
                case 2:
                    usuarioActual.ingresar(99.99);
                    break;
                case 0:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Esta opción no está contemplada...");
                    break;
            }
            BBDD.modificarSaldo(usuarioActual.getDNI(),usuarioActual.getSaldo());
        } while (opcion != 0);

    }

    private static void menuGastar() {

        int opcion;

        do {
            System.out.println("""
                    ¿En qué te vas a gastar los dineros?
                    1.- Vacaciones
                    2.- Alquiler
                    3.- Vicios variados
                    0.- Volver atrás
                    """);

            opcion = ints.nextInt();

            switch(opcion){
                case 1:
                    usuarioActual.gastar(1280);
                    break;
                case 2:
                    usuarioActual.gastar(750);
                    break;
                case 3:
                    usuarioActual.gastar(60);
                    break;
                case 0:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Esta opción no está contemplada...");
                    break;
            }
            BBDD.modificarSaldo(usuarioActual.getDNI(),usuarioActual.getSaldo());
        } while (opcion != 0);
    }

    public static Usuario loginyRegistro(SQLite BBDD){

        System.out.println("Bienvenido a la gestión de tus dineros");

        System.out.println("Introduce tu DNI: ");

        String dni=strings.nextLine();

        if(!Usuario.comprobarDni(dni)){
            System.out.println("Este DNI no es correcto...");
            return null;
        }

        System.out.println("DNI recogido correctamente...");

        if(!BBDD.usuarioExiste(dni)) registro(BBDD,dni);

        return BBDD.getUsuario(dni);
    }

    public static void registro(SQLite BBDD,String dni){

        System.out.println("Introduce un nombre");
        String nombre=strings.nextLine();

        System.out.println("Introduce un saldo");
        double saldo=doubles.nextDouble();

        Usuario usuario = new Usuario(nombre,dni,saldo);

        BBDD.insertarUsuario(usuario);
    }
}
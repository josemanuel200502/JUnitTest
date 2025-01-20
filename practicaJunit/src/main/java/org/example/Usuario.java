package org.example;

public class Usuario {
    private String nombre;
    private String DNI;
    private double nomina;
    private double saldo;

    public Usuario(String nombre, String DNI,double saldo){
        this.nombre=nombre;
        this.DNI=DNI;
        this.nomina=saldo;
        this.saldo=saldo;
    }

    public Usuario(String nombre, String DNI,double saldo, double nomina){
        this.nombre=nombre;
        this.DNI=DNI;
        this.nomina=nomina;
        this.saldo=saldo;
    }

    public void gastar(double importe){
        if(this.saldo - importe < 0){
            System.out.println("No tienes suficiente dinero para realizar esta operación...");
            return;
        }
        this.saldo = this.saldo - importe;
        System.out.println("Después de gastar: "+importe+"€ tu nuevo saldo es: "+String.format("%.2f",this.saldo)+"€");
    }

    public void ingresar(double ingreso){
        if(ingreso < 0) {
            System.out.println("No se puede ingresar una cantidad negativa.");
            return; // Sale del método sin realizar ninguna acción
        }
        this.saldo = this.saldo + ingreso;
        System.out.println("Nuevo saldo: "+this.saldo);
        System.out.println("Después de ingresar: "+ingreso+"€ tu nuevo saldo es: "+String.format("%.2f",this.saldo)+"€");
    }


    public static boolean comprobarDni(String dni) {
        if (dni == null) return false;

        // Validar longitud
        if (dni.length() != 9) {
            System.out.println("El DNI debe tener 9 caracteres.");
            return false;
        }

        // Validar los primeros 8 caracteres como dígitos
        if (!dni.substring(0, 8).matches("\\d{8}")) {
            System.out.println("Los primeros 8 caracteres deben ser dígitos.");
            return false;
        }

        // Validar que el último carácter sea una letra
        if (!Character.isLetter(dni.charAt(8))) {
            System.out.println("El último carácter debe ser una letra.");
            return false;
        }

        // Calcular la letra a partir de los primeros 8 dígitos
        int numero = Integer.parseInt(dni.substring(0, 8));
        String[] letras = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        String letraCalculada = letras[numero % 23];

        // Comparar la letra calculada con la letra del DNI proporcionado
        if (!letraCalculada.equalsIgnoreCase(String.valueOf(dni.charAt(8)))) {
            System.out.println("La letra del DNI no es correcta.");
            return false;
        }

        return true;  // El DNI es válido
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public double getNomina() {
        return nomina;
    }

    public void setNomina(double nomina) {
        this.nomina = nomina;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

import java.io.File;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        int cont = 0;
        Bicho[][] bichos = new Bicho[2][2];
        boolean nio = true;
        while(nio){
            System.out.println("0. Salir del juego");
            System.out.println("1. Iniciar juego");

            Scanner scan = new Scanner(System.in);
            int opcion1 = scan.nextInt();

            switch (opcion1){
                case 0:
                    nio = false;
                    break;
                case 1:
                    crearTableroDeJuego(cont, bichos);
                    mostrarTableroDeJuego(bichos);
                    boolean pare = false;
                    while(!pare){
                        System.out.println("1. Disparar una bala");
                        System.out.println("2. Activar bomba atómica");
                        System.out.println("3. Activar bicho mutante");
                        System.out.println("4. La frase de la abuela");
                        int opcion2 = scan.nextInt();

                        switch (opcion2){
                            case 1:
                                System.out.println("Ingrese la posicion a la que desea disparar sin puntos de por medio");
                                int posicion = scan.nextInt();
                                dispararBala(bichos, posicion);
                                mostrarTableroDeJuego(bichos);
                                pare = estadoCampoDeBatalla(bichos);
                                break;
                            case 2:
                                matarAleatoriamente(bichos);
                                mostrarTableroDeJuego(bichos);
                                pare = estadoCampoDeBatalla(bichos);
                                break;
                            case 3:
                                activarBichoMutante(bichos);
                                mostrarTableroDeJuego(bichos);
                                pare = estadoCampoDeBatalla(bichos);
                                break;
                            case 4:
                                mostrarFrasesDeLaAbuela();
                                mostrarTableroDeJuego(bichos);
                                pare = estadoCampoDeBatalla(bichos);
                                break;
                        }
                    }
                    break;
            }
        }


        mostrarTableroDeJuego(bichos);
    }

    private static void mostrarTableroDeJuego(Bicho[][] bichos) {
        System.out.println("----------------");
        System.out.println("|" + Bicho.toString(bichos[0][0]) +" | "+ Bicho.toString(bichos[0][1]) +" | ");
        System.out.println("----------------");
        System.out.println("|" + Bicho.toString(bichos[1][0]) +" | "+ Bicho.toString(bichos[1][1])+" | ");
        System.out.println("----------------");
    }

    private static void crearTableroDeJuego(int cont, Bicho[][] bichos) {
        int numeroAleatorio = (int) (Math.random()*4+1);

        for(int i = 0; i < numeroAleatorio; i++){
           int numeroAleatorioBichos = (int) (Math.random()*2+1);
               if(numeroAleatorioBichos == 1){
                   var bichoNormal = new BichoNormal();
                   anadirAMatriz(cont, bichos, bichoNormal);
               }else{
                   var bichoAlien = new BichoAlien();
                    anadirAMatriz(cont, bichos, bichoAlien);
               }
               cont = cont +1;
        }
    }

    private static void anadirAMatriz(int cont, Bicho[][] bichos, Bicho bicho) {
        if(cont == 0){
            bichos[0][0] = bicho;
        }else if(cont == 1){
            bichos[0][1] = bicho;
        }else if(cont == 2){
            bichos[1][0] = bicho;
        }else{
            bichos[1][1] = bicho;
        }
    }

    private static void dispararBala(Bicho[][] bichos, int posicion){
        if(posicion == 00){
            bichos[0][0].setSalud(bichos[0][0].getSalud() - 5);
        }else if(posicion == 01){
            bichos[0][1].setSalud(bichos[0][1].getSalud() - 5);
        }else if(posicion == 10){
            bichos[1][0].setSalud(bichos[1][0].getSalud() - 5);
        }else{
            bichos[1][1].setSalud(bichos[1][1].getSalud() - 5);
        }
    }

    private static void matarAleatoriamente(Bicho[][] bichos){
        int cont = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                if(bichos[i][j] != null){
                    cont = cont + 1;
                }
            }
        }
        int numeroAleatorio = (int) (Math.random()*cont+1);
        if(numeroAleatorio == 1){
            bichos[0][0].setSalud(0);
            System.out.println("Se ha eliminado el bicho en la posición 0.0");
        }else if(numeroAleatorio == 2){
            bichos[0][1].setSalud(0);
            System.out.println("Se ha eliminado el bicho en la posición 0.1");
        }else if(numeroAleatorio == 3){
            bichos[1][0].setSalud(0);
            System.out.println("Se ha eliminado el bicho en la posición 1.0");
        }else{
            bichos[1][1].setSalud(0);
            System.out.println("Se ha eliminado el bicho en la posición 1.1");
        }
    }

    private static void activarBichoMutante(Bicho[][] bichos){
        var menor = bichos[0][0];
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 2; j++){
                if(bichos[i][j] != null){
                    if(bichos[i][j].getSalud() < menor.getSalud() && bichos[i][j].getSalud() != 0){
                        menor = bichos[i][j];
                    }
                }
            }
        }

        menor.setSalud(menor.getSalud()*2);
    }

    private static void mostrarFrasesDeLaAbuela(){
        int numeroAleatorio = (int) (Math.random()*3+1);
        switch (numeroAleatorio){
            case 1:
                System.out.println("Muere insectosaurio");
                break;
            case 2:
                System.out.println("O lo mato o lo mato");
                break;
            case 3:
                System.out.println("Donde pongo el ojo pongo la bala");
                break;
        }
    }

    private static boolean estadoCampoDeBatalla(Bicho[][] bichos){
        int cont = 0;
        boolean isGameOver = false;
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 2; j++){
                if(bichos[i][j]== null || bichos[i][j].getSalud() <= 0  ){
                    cont = cont + 1;
                }
            }
        }

        if (cont == 4){
            isGameOver = true;
            System.out.println("Todos los bichos están muertos, juego terminado");
        }

        return isGameOver;
    }


}

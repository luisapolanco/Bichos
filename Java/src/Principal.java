public class Principal {

    public static void main(String[] args) {
        Bicho[][] bichos = new Bicho[2][2];
        int numeroAleatorio = (int) (Math.random()*4+1);

        for(int i = 0; i < numeroAleatorio; i++){
            int numeroAleatorioBichos = (int) (Math.random()*2+1);
            for(int j = 0; j < 2; j++){
                for(int k = 0; k < 2; k++){
                    if(numeroAleatorioBichos == 1){
                        var bichoNormal = new BichoNormal();
                        bichos[j][k] = bichoNormal;
                    }else{
                        var bichoAlien = new BichoAlien();
                        bichos[j][k] = bichoAlien;
                    }
                }
            }

        }

        System.out.println("---------------------------");
        System.out.println("|" + Bicho.toString(bichos[0][0]) +" | "+ Bicho.toString(bichos[0][1]) +" | ");
        System.out.println("---------------------------");
        System.out.println("|" + Bicho.toString(bichos[1][0]) +" | "+ Bicho.toString(bichos[1][1])+" | ");
        System.out.println("---------------------------");
    }
}

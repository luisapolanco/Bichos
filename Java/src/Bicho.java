public class Bicho {

    private int salud;

    public int getSalud() {
        return this.salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public static String toString(Bicho bicho) {
        if (bicho == null) {
            return "     ";
        } else {
            if(bicho.getClass().getSimpleName().equals("BichoAlien")){
                return "BA-" + bicho.getSalud();
            }else{
                return "BN-" + bicho.getSalud();
            }
        }

    }
}


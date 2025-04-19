public class Ejercicio3_a {
    private final static double E = 0.0001;
    private final static int MIN = 1000;
    private final static int CANT = 3;

    public static void main(String[] args) {
        int[] emisiones = { 0, 0, 0 };
        double[] estacionario_act = { 0, 0, 0 };
        double[] estacionario_ant = { -1.0, -1.0, -1.0 };        
        getVectorEstado(emisiones, estacionario_ant, estacionario_act);
        System.out.println();
        mostrarArreglo(estacionario_act); 
        mostrarEmisiones(emisiones);
    }

    public static void getVectorEstado(int[] emisiones, double[] ant, double[] act) {
        int mensajes = 0;
        while (!converge(ant, act) || mensajes < MIN) {
            int s = getPrimerSimbolo();
            for (int i = 0; i < CANT; i++) {
                s = getSigDadoAnt(s);
            }
            emisiones[s]++;
            mensajes++;
            for (int i = 0; i < CANT; i++) {
                ant[i] = act[i];
                act[i] = (double) emisiones[i] / mensajes;
            }
        }
    }

    public static boolean converge(double[] ant, double[] act) {
        for (int i = 0; i < CANT; i++) 
            if (Math.abs(ant[i] - act[i]) < E)
                return true;
        return false;
    }

    public static void mostrarArreglo(double[] arr) {
        System.out.println("Mostrando el vector de estado");
        for (int i = 0; i < CANT; i++)
            System.out.print("| " + arr[i] + " |");
        System.out.println();
    }

    public static void mostrarEmisiones(int[] arr) {
        System.out.println("Mostrando las emisiones: ");
        for (int i = 0; i < CANT; i++)
            System.out.print("| " + arr[i] + " |");
        System.out.println();
    }

    public static int getPrimerSimbolo() {
        double [] acum = { 1.0, 1.0, 1.0 };
        double p = Math.random();
        for (int i = 0; i < CANT; i++) 
            if (p < acum[i])
                return i;
        return -1; 
    }

    public static int getSigDadoAnt(int s) {
        double [][] mat_acum = {
            {0.25, 0.75, 0.0},
            {0.50, 1.00, 0.5},
            {1.00, 1.00, 1.0}
        };
        double p = Math.random();
        for (int i = 0; i < CANT; i++)
            if (p < mat_acum[i][s])
                return i;
        return -1;
    }
}
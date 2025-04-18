public class Ejercicio3_a {
    private final static double E = 0.0001;
    private final static int MIN = 1000;
    private final static int MAX = 3;
    private final static int CANT = 3;

    public static void main(String[] args) {
        int[] emisiones = { 0, 0, 0 };
        double[] estacionario_act = { 0, 0, 0 };
        double[] estacionario_ant = { -1.0, -1.0, -1.0 };        
        getVectorEstado(emisiones, estacionario_ant, estacionario_act);
        mostrarArreglo(estacionario_act); 
    }

    public static void getVectorEstado(int[] emisiones, double[] ant, double[] act) {
        int mensajes = 0;
        while (!converge(ant, act) || mensajes < MIN) {
            int s = getPrimerSimbolo();
            for (int i = 0; i < MAX; i++) {
                s = getSigDadoAnt(s);
            }
            emisiones[s]++;
            mensajes++;
            for (int i = 0; i < MAX; i++) {
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
        for (int i = 0; i < CANT; i++)
            System.out.print(arr[i] + " ");
    }

    // Placeholder methods for getPrimerSimbolo and getSigDadoAnt
    public static int getPrimerSimbolo() {
        // Implementation needed
        return 0; // Placeholder return
    }

    public static int getSigDadoAnt(int s) {
        // Implementation needed
        return s; // Placeholder return
    }
}
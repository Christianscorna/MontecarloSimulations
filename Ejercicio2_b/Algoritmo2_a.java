/*
 *  Se desea transmitir diariamente el estado del tiempo en cierta región, en la que se pueden presentar
días soleados, nublados o lluviosos. Estadísticamente, se conoce que nunca se han presentado dos días
soleados seguidos. Si un día está soleado, el siguiente puede estar nublado o lluvioso con igual
probabilidad. Si el día se presenta con lluvia o nublado, entonces hay una probabilidad de ½ de que el
siguiente día tenga las mismas características; y si cambia, entonces es igualmente probable que sea de
cualquiera de las otras posibilidades.
b) Determine la proporción de días que serán soleados, nublados y lluviosos en estado estacionario
*/

public class Algoritmo2_a {
    public final static int MIN = 1000;
    public final static double E = 0.0001;
    public final static int CANT = 3;

    public static void main(String[] args) {
        int[] emisiones = { 0, 0, 0 };
        double[] estacionario_act = { 0, 0, 0 };
        double[] estacionario_ant = { -1.0, -1.0, -1.0 };        
        getVectorEstacionario(emisiones, estacionario_ant, estacionario_act);
        mostrarArreglo(estacionario_act);
    }

    public static void getVectorEstacionario(int[] emi, double[] ant, double[] act) {
        int cant_simbolos = 0;
        int s = getSimbolo();
        while (!converge(ant, act) || (cant_simbolos < MIN)) {
            s = sigDadoAnt(s);
            emi[s]++;
            cant_simbolos++;

            for (int i = 0; i < CANT; i++) 
                ant[i] = act[i];

            // Actualizar el vector estacionario
            for (int i = 0; i < CANT; i++) 
                act[i] = (double) emi[i] / cant_simbolos;
        }
    }

    public static int getSimbolo() {
        //double[] acumulada = { 1.0 / 3.0, 2.0 / 3.0, 1.0 };
        double[] acumulada = { 0, 1.0, 1.0 };
        double p = Math.random();
        for (int i = 0; i < CANT; i++) 
            if (p < acumulada[i]) 
                return i;
        return -1;
    }

    public static int sigDadoAnt(int index) {
        double[][] acumulada = {
            { 0, 0.5, 0.25 },
            { 0.5, 0.75, 0.5 }, 
            { 1.0, 1.0, 1.0 } 
        };
        double p = Math.random();
        for (int i = 0; i < CANT; i++)
            if (p < acumulada[i][index])
                return i;
        return -1; 
    }

    public static boolean converge(double[] ant, double[] act) {
        for (int i = 0; i < CANT; i++) 
            if (Math.abs(ant[i] - act[i]) < E)
                return true;
        return false;
    }

    public static void mostrarArreglo(double [] arr) {
        for (int i = 0; i < CANT; i++)
            System.out.print(arr[i] + " ");
    }
}

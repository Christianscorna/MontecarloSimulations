public class Ejercicio3_a2 {
    private final static int MAX = 3;

    public static void main(String[] args) {
        double [][] transicion = {
            {0.25, 0.75, 0.0},
            {0.50, 0.25, 0.5},
            {0.25, 0.00, 0.5}
        };

        double [] estado_inicial = {1.0, 0.0, 0.0};
        double [] estado = {0.0, 0.0, 0.0};

        for (int paso = 1; paso <= MAX; paso++) {
            multVectByMatr(estado_inicial, estado, transicion);
            System.out.print("Paso " + paso + " -->");
            System.out.println(estado[0]);

            for (int i = 0; i < MAX; i++)
                estado_inicial[i] = estado[i];
                
            for (int i = 0; i < MAX; i++)
                estado[i] = 0.0;
        }
    }

    public static void multVectByMatr(double[] e, double [] resultado, double[][] m) {
        for (int i = 0; i < MAX; i++)
            for (int j = 0; j < MAX; j++)
                resultado[i] += e[j] * m[j][i];
    }
}

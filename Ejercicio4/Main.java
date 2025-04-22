public class Main {
    public static void main(String[] args) {
        MonteCarlo simulacion = new MonteCarlo(0.0001, 1000);
        System.out.println(simulacion.getVectorEstacionario4_b());
    }
}

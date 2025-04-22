import java.util.ArrayList;

public class MonteCarlo {
    private static final int MAX = 3;
    private double epsilon;
    private int iteraciones;
    private ArrayList<Integer> emisiones;
    private ArrayList<Double> ant, act;

    public MonteCarlo(double epsilon, int iteraciones) {
        this.epsilon = epsilon;
        this.iteraciones = iteraciones;
        this.emisiones = new ArrayList<>();
        this.ant = new ArrayList<>();
        this.act = new ArrayList<>();

        for (int i = 0; i < MAX; i++) {
            this.emisiones.add(0); 
            this.ant.add(0.0);     
            this.act.add(0.0);     
        }
    }

    private int getPrimerSimbolo() {
        int[] prob_acum = { 0, 1, 1 };
        double p = Math.random();
        for (int i = 0; i < MAX; i++) {
            if (p < prob_acum[i]) {
                return i;
            }
        }
        return -1;
    }

    private int getSegundoDadoAnt(int anterior) {
        double [][] prob_acum = {
            { 0.5, 1.0/3.0, 0 },
            { 1.0, 2.0/3.0, 1.0},
            { 1.0, 1.0, 1.0 }
        };
        double p = Math.random();
        for (int i = 0; i < MAX; i++) 
            if (p < prob_acum[i][anterior])
                return i;
        return -1;
    }

    private boolean converge(ArrayList<Double> ant, ArrayList<Double> act) {
        for (int i = 0; i < MAX; i++)
            if (Math.abs(ant.get(i) - act.get(i)) > this.epsilon)
                return false;
        return true;
    }

    public ArrayList<Double> getVectorEstacionario4_c() {
        int cant = 0;
        int s = this.getPrimerSimbolo();
        while (!this.converge(this.ant, this.act) || cant < this.getIteraciones()) {
            int anterior = s;
            int nuevo = this.getSegundoDadoAnt(anterior);
            if (anterior == nuevo) {
                this.emisiones.set(nuevo, this.emisiones.get(nuevo) + 1);
            }
            cant++;
            actualizarVectores(cant);
        }
        return act;
    }

    public ArrayList<Double> getVectorEstacionario4_b() {
        int cant = 0;
        int s = this.getPrimerSimbolo();
        while (!this.converge(this.ant, this.act) || cant < this.getIteraciones()) {
            s = this.getSegundoDadoAnt(s);
            this.emisiones.set(s, this.emisiones.get(s) + 1);
            cant++;
            actualizarVectores(cant);
        }
        return act;
    }

    private void actualizarVectores(int cant) {
        for (int i = 0; i < MAX; i++) {
            double prob = this.act.get(i);
            this.ant.set(i, prob);
            double prob_actual = (double) this.emisiones.get(i) / cant;
            this.act.set(i, prob_actual);
        }
    }

    public double getEpsilon() { return epsilon; }
    public void setEpsilon(double epsilon) { this.epsilon = epsilon; }
    public int getIteraciones() { return iteraciones; }
    public void setIteraciones(int iteraciones) { this.iteraciones = iteraciones; }

    

    
}

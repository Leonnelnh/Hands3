import java.lang.Math;

public class Tabla {

    private double[] dataSet;
    private double min;
    private double a;
    private int k;
    private int n;
    private int[] f;
    private double[] fr;
    private double[] porcentaje;

    public Tabla(double[] dataSet, double min, double a, int k) {
        this.dataSet = dataSet;
        this.min = min;
        this.a = a;
        this.k = k;
        this.n = dataSet.length;

        this.f = new int[k];
        this.fr = new double[k];
        this.porcentaje = new double[k];

        contarFrecuenciasAbsolutas();
        calcularFrecuenciasBasicas();
    }

    private void contarFrecuenciasAbsolutas() {
        for (int i = 0; i < k; i++) {
            double limiteInferior = min + (i * a);
            double limiteSuperior = min + ((i + 1) * a);

            for (double dato : dataSet) {
                if (i == k - 1) {
                    if (dato >= limiteInferior && dato <= limiteSuperior) {
                        f[i]++;
                    }
                }
                else {
                    if (dato >= limiteInferior && dato < limiteSuperior) {
                        f[i]++;
                    }
                }
            }
        }
    }

    private void calcularFrecuenciasBasicas() {
        double sumaFr = 0;

        for (int i = 0; i < k; i++) {
            fr[i] = (double) f[i] / n;
            sumaFr += fr[i];
            porcentaje[i] = fr[i] * 100;
        }

        if (Math.abs(sumaFr - 1.0) < 0.001) { sumaFr = 1.0; }
    }

    public void imprimirTablaBasica() {
        double totalFr = 0;
        double totalPorcentaje = 0;
        for (int i = 0; i < k; i++) {
            totalFr += fr[i];
            totalPorcentaje += porcentaje[i];
        }

        System.out.println("╔════════╤═══════╤════════════╤═════════╗");
        System.out.printf("║ %-6s │ %-5s │ %-10s │ %-7s ║\n",
                "Clase", "f", "fr", "%");
        System.out.println("╠════════╪═══════╪════════════╪═════════╣");

        for (int i = 0; i < k; i++) {

            String clas = String.format("%d", i+1);

            System.out.printf("║ %-6s │ %-5d │ %-10.4f │ %-7.2f ║\n",
                    clas, f[i], fr[i], porcentaje[i]);
        }

        System.out.println("╠════════╪═══════╪════════════╪═════════╣");
        System.out.printf("║ %-6s │ %-5d │ %-10.4f │ %-7.2f ║\n",
                "TOTAL", n, totalFr, totalPorcentaje);
        System.out.println("╚════════╧═══════╧════════════╧═════════╝");
    }
}
import java.lang.Math;

public class Tabla {
    private double[] xc;
    private int[] fa;
    private double[] fra;
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

        this.xc = new double[k];
        this.fa = new int[k];
        this.fra = new double[k];

        contarFrecuenciasAbsolutas();
        calcularFrecuenciasBasicas();
        calcularFrecuenciasExtendidas();
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

    private void calcularFrecuenciasExtendidas() {
        int acumuladorF = 0;
        double acumuladorFr = 0.0;

        for (int i = 0; i < k; i++) {
            double limiteInferior = min + (i * a);
            double limiteSuperior = min + ((i + 1) * a);

            xc[i] = (limiteInferior + limiteSuperior) / 2.0;

            acumuladorF += f[i];
            fa[i] = acumuladorF;

            acumuladorFr += fr[i];
            fra[i] = acumuladorFr;
        }

        if (Math.abs(fra[k - 1] - 1.0) < 0.001) {
            fra[k - 1] = 1.0;
        }
    }

    public void imprimirTabla() {
        double totalFr = 0;
        double totalPorcentaje = 0;
        for (int i = 0; i < k; i++) {
            totalFr += fr[i];
            totalPorcentaje += porcentaje[i];
        }

        System.out.println("╔════════╤══════════════════╤═══════╤═══════╤═══════╤════════════╤════════════╤═════════╗");
        System.out.printf("║ %-6s │ %-16s │ %-5s │ %-5s │ %-5s │ %-10s │ %-10s │ %-7s ║\n",
                "Clase", "Lim C", "f", "Xc", "Fa", "fr", "fra", "%");
        System.out.println("╠════════╪══════════════════╪═══════╪═══════╪═══════╪════════════╪════════════╪═════════╣");


        for (int i = 0; i < k; i++) {

            double limiteInferior = min + (i * a);
            double limiteSuperior = min + ((i + 1) * a);
            String limc;

            if (i < k - 1) {
                limc = String.format("[%.2f - %.2f)", limiteInferior, limiteSuperior);
            } else {
                limc = String.format("[%.2f - %.2f]", limiteInferior, limiteSuperior);
            }

            String clas = String.format("%d", i+1);

            System.out.printf("║ %-6s │ %-16s │ %-5d │ %-5.2f │ %-5d │ %-10.4f │ %-10.4f │ %-7.2f ║\n",
                    clas, limc, f[i], xc[i], fa[i], fr[i], fra[i], porcentaje[i]);
        }

        System.out.println("╠════════╧══════════════════╧═══════╧═══════╧═══════╪════════════╪════════════╪═════════╣");
        System.out.printf("║ %-49s │ %-10.4f │ %-10.4f │ %-7.2f ║\n",
                "TOTAL", totalFr, fra[k-1], totalPorcentaje);
        System.out.println("╚═══════════════════════════════════════════════════╧════════════╧════════════╧═════════╝");
    }
}
import java.util.Arrays;
import java.lang.Math;

public class Calculadora {

    private double min;
    private int k;
    private double a;

    public Calculadora(double[] data) {
        int n = data.length;

        double[] sortedData = Arrays.copyOf(data, n);
        Arrays.sort(sortedData);

        this.min = sortedData[0];
        double max = sortedData[n - 1];

        double rango = max - min;

        double k_sturges = 1 + 3.322 * Math.log10(n);
        this.k = (int) Math.round(k_sturges);

        double amplitud = rango / this.k;
        this.a = Math.ceil(amplitud * 100.0) / 100.0;
    }

    public double getMinimo() { return min; }
    public int getNumClases() { return k; }
    public double getAmplitud() { return a; }
}
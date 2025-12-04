import java.util.Arrays;
import java.lang.Math;

public class TablaDeFrecuencia {

    public static void main(String[] args) {

        double[] dataSet = {
                1.05, 1.25, 1.80, 1.95, 2.10,
                2.50, 2.75, 2.99, 3.01, 3.20,
                3.40, 3.61, 3.88, 3.99, 4.05,
                4.15, 4.21, 4.30, 4.48, 4.75,
                5.05, 5.18, 5.30, 5.55, 5.60,
                5.78
        };

        // El nombre de la clase Calculadora debe coincidir con el archivo Calculadora.java
        Calculadora calc = new Calculadora(dataSet);

        // El nombre de la clase Tabla debe coincidir con el archivo Tabla.java
        Tabla generador = new Tabla(dataSet,
                calc.getMinimo(),
                calc.getAmplitud(),
                calc.getNumClases());

        System.out.println("----- RESULTADOS HANDS-ON 3 (Tabla Básica - Continua) -----");
        generador.imprimirTablaBasica();
    }
}
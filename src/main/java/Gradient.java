import java.util.Arrays;
import java.util.Random;

public class Gradient {
    public static double EPS = 0.00001;

    public static void main(String[] args) {

        int border = 10;
        double minX = 0, maxX = 3;
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int n = 10;
            double[][] a = new double[n][n];
            double[] b = new double[n];
            double c = 0;
            for (int j = 0; j < n; j++) {
                b[j] = minX + (maxX - minX) * random.nextDouble();
                a[j][j] = minX + (maxX - minX) * random.nextDouble();
            }
            QuadraticFunctionNotDiagonalised function = new QuadraticFunctionNotDiagonalised(a, b, c);
            double[] x0 = new double[function.getDimension()];
            Arrays.fill(x0, 10);
            System.out.println("first");
            StepGradient.gradient(x0, function, EPS, -border, border);
            System.out.println("second");
            GreatDescentGradient.gradient(x0, function, EPS, -border, border);
            System.out.println("Third");
            ConjugateGradient.gradient(x0, function, EPS, -border, border, 10);
            System.out.println();
        }
    }
}

import java.util.Random;

public class QuadraticFunctionFactory {
    public static QuadraticFunctionDiagonalised randomD(int n, double maxX) {
        Random random = new Random();
        double minX = 1;
        double[] a = new double[n];
        double[] b = new double[n];
        double c = 0;
        for (int j = 0; j < n; j++) {
            b[j] = minX + (maxX - minX) * random.nextDouble();
            a[j] = minX + (maxX - minX) * random.nextDouble();
        }
        a[n - 1] = maxX;
        return new QuadraticFunctionDiagonalised(a, b, c);
    }

    public static QuadraticFunctionNotDiagonalised randomND(int n, double maxX) {
        Random random = new Random();
        double minX = 1;
        double[][] a = new double[n][n];
        double[] b = new double[n];
        double c = 0;
        for (int j = 0; j < n; j++) {
            b[j] = minX + (maxX - minX) * random.nextDouble();
            for (int i = 0; i < n; i++) {
                a[i][j] = minX + (maxX - minX) * random.nextDouble();
            }
        }
        return new QuadraticFunctionNotDiagonalised(a, b, c);
    }
}

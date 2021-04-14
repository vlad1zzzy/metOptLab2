import java.util.Arrays;
import java.util.Random;

public class Gradient {
    public static double EPS = 0.0001;

    public static void main(String[] args) {
        int border = 10;
        for (int i = 0; i < 10; i++) {
            QuadraticFunctionDiagonalised function = QuadraticFunctionFactory.randomD(1000, 3);
            double[] x0 = new double[function.getDimension()];
            Arrays.fill(x0, 10);
            System.out.println("first");
            StepGradient.gradient(x0, function, MinimisationMethod.BRENT, EPS, -border, border);
            System.out.println("second");
            GreatDescentGradient.gradient(x0, function, MinimisationMethod.BRENT, EPS, -border, border);
            System.out.println("Third");
            ConjugateGradient.gradient(x0, function, MinimisationMethod.BRENT, EPS, -border, border, 10);
            System.out.println();
        }
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvergenceRate {
    public static void main(String[] args) {
        final double EPS = 0.001;
        final int border = 100;
        for (int i = 10; i < 1000; i += 100) {
            for (int j = 0; j < 100; j += 10) {
                QuadraticFunction function = QuadraticFunctionFactory.randomD(i, j);

                double[] x0 = new double[function.getDimension()];
                Arrays.fill(x0, 100);

                System.out.println("STEP:");
                Answer answer = StepGradient.gradient(x0, function, MinimisationMethod.BRENT, EPS, -border, border);
                answer.printAns();

                System.out.println("FASTEST:");
                answer = GreatDescentGradient.gradient(x0, function, MinimisationMethod.BRENT, EPS, -border, border);
                answer.printAns();


                System.out.println("CONJUGATE:");
                answer = ConjugateGradient.gradient(x0, function, MinimisationMethod.BRENT, EPS, -border, border, function.getDimension());
                answer.printAns();

                System.out.println("\n");

            }
        }
    }
}

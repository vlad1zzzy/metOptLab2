import java.util.Arrays;
import java.util.List;

public class Different {
    public static void main(String[] args) {
        List<QuadraticFunction> quadraticFunctionList = List.of(
                new QuadraticFunctionNotDiagonalised(new double[][]{{64, 126}, {126, 64}}, new double[]{-10, 30}, 13),
                new QuadraticFunctionNotDiagonalised(new double[][]{{24, 56}, {56, 24}}, new double[]{5, 13}, -11),
                new QuadraticFunctionNotDiagonalised(new double[][]{{211, -420}, {-420, 211}}, new double[]{-192, 50}, -25));
        final double EPS = 0.0001;
        final int border = 100;
        double[] x0 = new double[2];
        Arrays.fill(x0, 100);
        for (QuadraticFunction function : quadraticFunctionList) {
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

import java.util.Arrays;
import java.util.List;

public class Gradient {
    public static double EPS = 0.001;

    public static void main(String[] args) {
        //test1();
        test2();
    }

    public static void test1() {
        int border = 1000;
        int n = 1000;
        MinimisationMethod[] methods = new MinimisationMethod[] {
                MinimisationMethod.DICHOTOMY,
                MinimisationMethod.FIBONACCI,
                MinimisationMethod.GOLDEN_SECTION,
                MinimisationMethod.PARABOLA,
                MinimisationMethod.BRENT
        };
        for (int i = 0; i < 10; i++) {
            QuadraticFunctionDiagonalised function = QuadraticFunctionFactory.randomD(n, 20);
            double[] x0 = new double[function.getDimension()];
            Arrays.fill(x0, 100);

            System.out.println("STEP:");
            for (MinimisationMethod method : methods) {
                Answer answer = StepGradient.gradient(x0, function, method, EPS, -border, border);
                answer.printAns();
                System.out.println("  Method: " + method);
            }

            System.out.println("FASTEST:");
            for (MinimisationMethod method : methods) {
                Answer answer = GreatDescentGradient.gradient(x0, function, method, EPS, -border, border);
                answer.printAns();
                System.out.println("  Method: " + method);
            }

            System.out.println("CONJUGATE:");
            for (MinimisationMethod method : methods) {
                Answer answer = ConjugateGradient.gradient(x0, function, method, EPS, -border, border, n);
                answer.printAns();
                System.out.println("  Method: " + method);
            }
        }
    }

    public static void test2() {
        List<QuadraticFunction> quadraticFunctionList = List.of(
                new QuadraticFunctionNotDiagonalised(new double[][]{{64, 126}, {126, 64}}, new double[]{-10, 30}, 13),
                new QuadraticFunctionNotDiagonalised(new double[][]{{24, 6}, {6, 14}}, new double[]{-5, 10}, -11),
                new QuadraticFunctionNotDiagonalised(new double[][]{{35, -69}, {-69, 35}}, new double[]{-34, 25}, -24));
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

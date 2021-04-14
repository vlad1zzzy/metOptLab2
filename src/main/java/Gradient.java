import java.util.Arrays;

public class Gradient {
    public static double EPS = 0.001;

    public static void main(String[] args) {
        int border = 1000;
        int n = 100;
        MinimisationMethod[] methods = new MinimisationMethod[] {
                MinimisationMethod.DICHOTOMY,
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
            }

            System.out.println("FASTEST:");
            for (MinimisationMethod method : methods) {
                Answer answer = GreatDescentGradient.gradient(x0, function, method, EPS, -border, border);
                //System.out.print(method + ") ");
                answer.printAns();
            }

            System.out.println("CONJUGATE:");
            for (MinimisationMethod method : methods) {
                Answer answer = ConjugateGradient.gradient(x0, function, method, EPS, -border, border, n);
                answer.printAns();
            }

            /*System.out.println("first");
            StepGradient.gradient(x0, function, MinimisationMethod.BRENT, EPS, -border, border);
            System.out.println("second");
            GreatDescentGradient.gradient(x0, function, MinimisationMethod.BRENT, EPS, -border, border);
            System.out.println("Third");
            ConjugateGradient.gradient(x0, function, MinimisationMethod.BRENT, EPS, -border, border, 10);
            System.out.println();*/
        }
    }
}

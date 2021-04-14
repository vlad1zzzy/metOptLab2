
public class GreatDescentGradient {

    public static double gradient(double[] xk, QuadraticFunction function, double epsilon, int a, int b) {
        return ConjugateGradient.gradient(xk, function, epsilon, a, b, 1);
    }
}

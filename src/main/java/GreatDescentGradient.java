public class GreatDescentGradient {

    public static Answer gradient(double[] xk, QuadraticFunction function, MinimisationMethod method, double epsilon, int a, int b) {
        return ConjugateGradient.gradient(xk, function, method, epsilon, a, b, 1);
    }
}

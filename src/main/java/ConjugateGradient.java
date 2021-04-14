public class ConjugateGradient {

    public static Answer gradient(double[] xk, QuadraticFunction function, MinimisationMethod method, double epsilon, int a, int b, int n) {
        int k = 0;
        double lambda, beta, g1;
        double[] x, p = function.findGradient(xk, -1);
        do {
            k++;
            x = xk;
            lambda = function.findMin(method, x, a, b, epsilon);
            xk = function.reducedAddVectors(x, p, lambda);
            g1 = function.dfNormalize(xk);
            if (k % n == 0) {
                p = function.findGradient(xk, -1);
            } else {
                double g = function.dfNormalize(x);
                beta = g1 * g1 / (g * g);
                p = function.reducedAddVectors(function.findGradient(xk, -1), p, beta);
            }
        } while (g1 > epsilon && k < 1000);
        return new Answer(xk, function.findFx(xk), k);
    }
}

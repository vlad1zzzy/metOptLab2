public class StepGradient {
    public static Answer gradient(double[] xk, QuadraticFunction function, MinimisationMethod method, double epsilon, int a, int b) {
        int k = 0;
        double lambda0 = 0.1;
        double lambda = lambda0;
        double[] x;
        while (function.dfNormalize(xk) > epsilon && k < 10000) {
            x = function.reducedAddVectors(xk, function.findGradient(xk, -1), lambda);
            while (function.findFx(x) - function.findFx(xk) > -lambda * 0.5 * function.dfNormalize(xk) * function.dfNormalize(xk)) {
                lambda /= 2;
                x = function.reducedAddVectors(xk, function.findGradient(xk, -1), lambda);
            }
            x = function.reducedAddVectors(xk, function.findGradient(xk, -1), lambda);
            xk = x;
            lambda = lambda0;
            k++;
        }
        return new Answer(xk, function.findFx(xk), k);
    }
}

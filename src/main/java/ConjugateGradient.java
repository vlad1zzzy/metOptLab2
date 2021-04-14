import java.util.Arrays;
import java.util.stream.Collectors;

public class ConjugateGradient {

    public static double gradient(double[] xk, QuadraticFunction function, double epsilon, int a, int b, int n) {
        int k = 0;
        int q = 0;
        double lambda, beta, g1;
        double[] x, p = function.findGradient(xk, -1);
        do {
            k++;
            x = xk;
            lambda = function.parabola(x, a, b, epsilon);
            xk = function.reducedAddVectors(x, p, lambda);
            g1 = function.dfNormalize(xk);
            if (k == n) {
                k = 0;
                p = function.findGradient(xk, -1);
            } else {
                double g = function.dfNormalize(x);
                beta = g1 * g1 / (g * g);
                p = function.reducedAddVectors(function.findGradient(xk, -1), p, beta);
            }
            q++;
        } while (g1 > epsilon && q < 1000);
        System.out.println(Arrays.stream(xk).mapToObj(String::valueOf)
                .collect(Collectors.joining(", ", q + ") ANSWER : f( ", " ) = ")) + function.findFx(xk));
        return function.findFx(xk);
    }
}

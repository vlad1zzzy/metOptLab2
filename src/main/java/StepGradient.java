import java.util.Arrays;
import java.util.stream.Collectors;

public class StepGradient {
    public static double gradient(double[] xk, QuadraticFunction function, double epsilon, int a, int b) {
        int k = 0;
        double lambda = function.parabola(xk, a, b, epsilon);
        double[] x;
        do {
            x = xk;
            xk = function.reducedAddVectors(x, function.findGradient(x, -1), lambda);
            while (function.findFx(xk) > function.findFx(x)) {
                if (k > 1000) {
                    return function.findFx(x);
                }
                lambda /= 2;
                xk = function.reducedAddVectors(x, function.findGradient(x, -1), lambda);
                k++;
            }
            k++;
        } while (function.dfNormalize(xk) > epsilon && k < 1000);

        System.out.println(Arrays.stream(xk).mapToObj(String::valueOf)
                .collect(Collectors.joining(", ", k + ") ANSWER : f( ", " ) = ")) + function.findFx(xk));
        return function.findFx(xk);
    }
}

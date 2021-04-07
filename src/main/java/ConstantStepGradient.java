import java.util.Arrays;
import java.util.stream.Collectors;

public class ConstantStepGradient {
    public static void gradient(double[] xk, QuadraticFunction function, double epsilon, int a, int b) {
        double lambda = function.findMin(a, b, epsilon, xk);
        double[] x = xk;
        do {
            xk = function.reducedAddVectors(x, function.findGradient(x, -1), lambda);
            if (function.findFx(xk) < function.findFx(x)) {
                x = xk;
            } else {
                lambda /= 2;
            }
            System.out.println(function.dfNormalize(xk));
        } while (function.dfNormalize(xk) > epsilon);
        System.out.println(Arrays.stream(xk).mapToObj(String::valueOf)
                .collect(Collectors.joining(", ", "ANSWER : f( ", " ) = ")) + function.findFx(xk));
    }
}

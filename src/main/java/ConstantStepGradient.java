import java.util.Arrays;
import java.util.stream.Collectors;

public class ConstantStepGradient {
    public static double gradient(double[] xk, QuadraticFunction function, double epsilon, int a, int b) {
        int k = 0;
        double lambda = function.findMin(a, b, xk);
        double[] x;
        do {
            x = xk;
            xk = function.reducedAddVectors(x, function.findGradient(x, -1), lambda);
            k++;
        } while (function.dfNormalize(xk) > epsilon && k < 10000);

        if (function.findFx(xk) == function.findFx(xk)) {
            System.out.println(Arrays.stream(xk).mapToObj(String::valueOf)
                    .collect(Collectors.joining(", ", "ANSWER : f( ", " ) = ")) + function.findFx(xk));
            System.out.println(k + "\n");
        }
        return function.findFx(xk);
    }
}

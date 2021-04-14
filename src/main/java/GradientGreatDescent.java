import java.util.Arrays;
import java.util.stream.Collectors;

public class GradientGreatDescent {

    public static void gradient(double[] xk, QuadraticFunctionNotDiagonalised function, double epsilon, int a, int b) {

        int k = 0;
        double lambda;
        double[] x;
        do {
            x = xk;
            lambda = function.findMin(a, b, x);
            xk = function.reducedAddVectors(x, function.findGradient(x, -1), lambda);
            k++;
        } while (function.dfNormalize(xk) > epsilon);

        System.out.println(Arrays.stream(xk).mapToObj(String::valueOf)
                .collect(Collectors.joining(", ", "ANSWER : f( ", " ) = ")) + function.findFx(xk));
    }
}

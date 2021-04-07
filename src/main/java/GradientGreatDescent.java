import java.util.Arrays;
import java.util.stream.Collectors;

public class GradientGreatDescent {

    public static double gradient(double[] xk, QuadraticFunction function, double epsilon, int a, int b) {
        return ConjugateGradient.gradient(xk, function, epsilon, a, b, 1);
        /*
        int k = 0;
        double lambda;
        double[] x;
        do {
            x = Arrays.copyOf(xk, xk.length);
            lambda = function.findMin(a, b, epsilon, x);
            xk = function.reducedAddVectors(x, function.findGradient(x, -1), lambda);

            //System.out.println(k + ") " + Arrays.toString(xk));

            k++;
        } while (function.dfNormalize(xk) > epsilon);
        System.out.println(Arrays.stream(xk).mapToObj(String::valueOf)
                .collect(Collectors.joining(", ", "ANSWER : f( ", " ) = ")) + function.findFx(xk));*/
    }
}

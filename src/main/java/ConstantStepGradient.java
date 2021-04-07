import java.util.Arrays;
import java.util.stream.Collectors;

public class ConstantStepGradient {
    public static void gradient(double[] xk, QuadraticFunction function, double epsilon, int a, int b) {
        int k = 0;
        double lambda =  function.findMin(a,b, xk);
        double[] x;
        do {
            x = xk;
            xk = function.reducedAddVectors(x, function.findGradient(x, -1), lambda);

            //System.out.println(k + ") " + Arrays.toString(xk));

            k++;
        }  while (function.dfNormalize(xk) > epsilon );
        System.out.println(Arrays.stream(xk).mapToObj(String::valueOf)
                .collect(Collectors.joining(", ","ANSWER : f( "," ) = ")) + function.findFx(xk));
    }
}

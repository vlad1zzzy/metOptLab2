import java.util.Arrays;
import java.util.stream.Collectors;

public class ConjugateGradient {
    public double findMin(double a, double b, QuadraticFunction function, final double eps, double[] x) {
        double s = 0.00000001;
        double x1, x2, f1, f2;
        if (s > eps) {
            s = eps;
        }
        do {
            x1 = (a + b - s) / 2;
            x2 = (a + b + s) / 2;
            f1 = function.findG(x, x1);
            f2 = function.findG(x, x2);
            if (f1 <= f2) {
                b = x2;
            } else {
                a = x1;
            }
        } while ((b - a) / 2 >= eps);
        return (a + b) / 2;
    }

    public void gradient(double[] x0, int n, QuadraticFunction function, double epsilon) {
        int k = 0, q = 0;
        double lambda, beta, g1;
        double[] x = Arrays.copyOf(x0, x0.length);
        double[] p;
        double[] xk ;
        p = function.findGradient(x, -1);
        do {
            q++;
            k++;
            lambda = findMin(-10,10, function, epsilon, x);
            xk = reducedAddVectors(x, p, lambda);
            //System.out.println(Arrays.toString(xk));
            //System.out.println(k + ")  f(" + xk + ", " + yk + ") = " + f(xk, yk));
            //System.out.print(xk + ", ");
            //System.out.print(yk + ", ");
            g1 = function.dfNormalize(xk);
            //System.out.println(g1);
            if (k == n) {
                k = 0;
                p = function.findGradient(xk, -1);
            } else {
                double g = function.dfNormalize(x);
                beta = g1 * g1 / g / g;
                p = reducedAddVectors(function.findGradient(xk, -1), p, beta);
            }
            x = Arrays.copyOf(xk, xk.length);
        } while (g1 > epsilon && q < 10000);
        System.out.println(Arrays.stream(xk).mapToObj(String::valueOf).collect(Collectors.joining(", ","ANSWER : f( "," ) = ")) + function.findFx(xk));
    }

    public double[] reducedAddVectors(double[] x1, double[] x2, double k2) {
        double[] ans = new double[x1.length];
        for (int i = 0; i < x1.length; i++) {
            ans[i] = x1[i] + k2 * x2[i];
        }
        return ans;
    }
}

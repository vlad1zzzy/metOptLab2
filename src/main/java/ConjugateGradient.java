public class ConjugateGradient {
    public double findMin(double a, double b, final double eps, double x, double y) {
        double s = 0.00000001;
        double x1, x2, f1, f2;
        if (s > eps) {
            s = eps;
        }
        do {
            x1 = (a + b - s) / 2;
            x2 = (a + b + s) / 2;
            f1 = Gradient.g(x, y, x1);
            f2 = Gradient.g(x, y, x2);
            if (f1 <= f2) {
                b = x2;
            } else {
                a = x1;
            }
        } while ((b - a) / 2 >= eps);
        return (a + b) / 2;
    }

    public double gradient(double a, double b, int n, double epsilon) {
        int k = 0;
        double x = a, y = b, lambda, xk = a, yk = b, beta, px, py;
        px = -Gradient.f_dx(x, y);
        py = -Gradient.f_dy(x, y);
        do {
            x = xk;
            y = yk;
            k++;
            lambda = findMin(-10, 10, epsilon, x, y);
            xk = x + lambda * px;
            yk = y + lambda * py;

            //System.out.println(k + ")  f(" + xk + ", " + yk + ") = " + f(xk, yk));
            //System.out.print(xk + ", ");
            //System.out.print(yk + ", ");

            if (k == n) {
                k = 0;
                px = -Gradient.f_dx(xk, yk);
                py = -Gradient.f_dy(xk, yk);
            } else {
                double g = Gradient.df_normalize(x, y);
                double g1 = Gradient.df_normalize(xk, yk);
                beta = g1 * g1 / g / g;
                px = -Gradient.f_dx(xk, yk) + beta * px;
                py = -Gradient.f_dy(xk, yk) + beta * py;
            }
        } while (Gradient.df_normalize(xk, yk) > epsilon && k < 1000);
        System.out.println("ANSWER : f( " + xk + ", " + yk + " ) = " + Gradient.f(xk, yk));
        return Gradient.f(xk, yk);
    }
}

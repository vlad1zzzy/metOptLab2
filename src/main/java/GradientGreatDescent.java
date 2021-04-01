
public class GradientGreatDescent {

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

    public double gradient(double a, double b, double epsilon) {
        int k = 0;
        double x = a, y = b, lambda, xk, yk;
        do {
            lambda = findMin(-10, 10, epsilon, x, y);

            xk = x - lambda * Gradient.f_dx(x, y);
            yk = y - lambda * Gradient.f_dy(x, y);

            //System.out.println(k + ")  f(" + xk + ", " + yk + ") = " + f(xk, yk));
            //System.out.println(xk +", " + yk);

            x = xk;
            y = yk;
            k++;

        } while (Gradient.df_normalize(xk, yk) > epsilon );
        System.out.println("ANSWER : f( " + xk + ", " + yk + " ) = " + Gradient.f(xk, yk));
        return Gradient.f(xk, yk);
    }
}

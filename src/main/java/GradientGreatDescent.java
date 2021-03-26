
public class GradientGreatDescent {

    public static double f(double x, double y) {
        return 2 * x * x + 3 * y * y + 5 * x - 4 * y;
    }

    public static double f_dx(double x, double y) {
        return 4 * x + 5;
    }

    public static double f_dy(double x, double y) {
        return 6 * y - 4;
    }

    public static double g(double x, double y, double lambda) {
        return f(x - lambda * f_dx(x, y), y - lambda * f_dy(x, y));
    }

    public static double df_normalize(double x, double y) {
        return Math.sqrt((f_dx(x, y)) * (f_dx(x, y)) + (f_dy(x, y)) * (f_dy(x, y)));
    }

    public static double findMin(double a, double b, final double eps, double x, double y) {
        double s = 0.00000001;
        double x1, x2, f1, f2;
        if (s > eps) {
            s = eps;
        }
        do {
            x1 = (a + b - s) / 2;
            x2 = (a + b + s) / 2;
            f1 = g(x, y, x1);
            f2 = g(x, y, x2);
            if (f1 <= f2) {
                b = x2;
            } else {
                a = x1;
            }
        } while ((b - a) / 2 >= eps);
        return (a + b) / 2;
    }

    public static double gradient(double a, double b, double epsilon) {
        int k = 0;
        double x = a, y = b, lambda, xk, yk;
        do {
            lambda = findMin(-10, 10, epsilon, x, y);

            xk = x - lambda * f_dx(x, y);
            yk = y - lambda * f_dy(x, y);

            System.out.println(k + ")  f(" + xk + ", " + yk + ") = " + f(xk, yk));

            x = xk;
            y = yk;
            k++;

        } while (df_normalize(xk, yk) > epsilon);

        System.out.println("ANSWER : f( " + xk + ", " + yk + " ) = " + f(xk, yk));
        return f(xk, yk);
    }

    public static void main(String[] args) {
        gradient(-10, 10, 0.00001);
    }
}

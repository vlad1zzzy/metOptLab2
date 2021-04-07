import static java.lang.Math.*;
import static java.lang.Math.abs;

public class QuadraticFunction {
    final private double[][] a;
    final private double[] b;
    final private double c;

    public QuadraticFunction(double[][] a, double[] b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double findFdkX(int k, double[] x) {
        double ans = reducedMultiplyVectors(a[k], x);
        ans += a[k][k] * x[k];
        ans += b[k];
        return ans;
    }

    public double findFx(double[] x) {
        double ans = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                ans += a[i][j] * x[i] * x[j];
            }
        }
        ans += reducedMultiplyVectors(b, x);
        ans += c;
        return ans;
    }

    public double findG(double[] x, double lambda) {
        double[] newX = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            newX[i] = x[i] - lambda * findFdkX(i, x);
        }
        return findFx(newX);
    }

    public double[] findGradient(double[] x, double k) {
        double[] ans = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            ans[i] = k * findFdkX(i, x);
        }
        return ans;
    }

    public double dfNormalize(double[] x) {
        double[] gradient = findGradient(x, 1);
        return Math.sqrt(reducedMultiplyVectors(gradient, gradient));
    }

    public double[] reducedAddVectors(double[] x1, double[] x2, double k2) {
        double[] ans = new double[x1.length];
        for (int i = 0; i < x1.length; i++) {
            ans[i] = x1[i] + k2 * x2[i];
        }
        return ans;
    }

    public double reducedMultiplyVectors(double[] x1, double[] x2) {
        double ans = 0;
        for (int i = 0; i < x1.length; i++) {
            ans += x1[i] * x2[i];
        }
        return ans;
    }

    public double findMin(double a, double b,  double[] x) {
        double eps = 0.000001;
        double s = 0.00000001;
        double x1, x2, f1, f2;
        if (s > eps) {
            s = eps;
        }
        do {
            x1 = (a + b - s) / 2;
            x2 = (a + b + s) / 2;
            f1 = findG(x, x1);
            f2 = findG(x, x2);
            if (f1 <= f2) {
                b = x2;
            } else {
                a = x1;
            }
        } while ((b - a) / 2 >= eps);
        return (a + b) / 2;
    }

    public double findMinBrent(double a, double c, final double eps, double[] x) {
        final double phi = (3 - sqrt(5)) / 2;
        double x2, x1, x3, xi = 0, f2, f1, f3, fi, d, e, g, tol;
        boolean accepted;
        x2 = x1 = x3 = a + phi * (c - a);
        f2 = f1 = f3 = findG(x,x1);
        d = e = c - a;
        do {
            accepted = false;
            g = e;
            e = d;
            tol = eps * abs(x2) + eps / 10;
            if (abs(x2 - (a + c) / 2) + (c - a) / 2 <= 2 * tol) {
                break;
            }
            if (threeNotEquals(x2, x3, x1, eps)) {
                xi = minOfParabola(x1, x2, x3, f1, f2, f3);
                if (a <= xi && xi <= c && abs(xi - x2) < g / 2) {
                    accepted = true;
                    if (xi - a < 2 * tol || c - xi < 2 * tol) {
                        xi = x2 - signum(x2 - (a + c) / 2) * tol;
                    }
                }
            }
            if (!accepted) {
                if (x2 < (a + c) / 2) {
                    xi = x2 + phi * (c - x2);
                    e = c - x2;
                } else {
                    xi = x2 - phi * (x2 - a);
                    e = x2 - a;
                }
            }
            if (abs(xi - x2) < tol) {
                xi = x2 + signum(xi - x2) * tol;
            }
            d = abs(xi - x2);
            fi = findG(x,x1);
            if (fi <= f2) {
                if (xi >= x2) {
                    a = x2;
                } else {
                    c = x2;
                }
                x3 = x1;
                x1 = x2;
                x2 = xi;
                f3 = f1;
                f1 = f2;
                f2 = fi;
            } else {
                if (xi >= x2) {
                    c = xi;
                } else {
                    a = xi;
                }
                if (fi <= f1 || equals(x1, x2, eps)) {
                    x3 = x1;
                    x1 = xi;
                    f3 = f1;
                    f1 = fi;
                } else if (fi <= f3 || equals(x3, x2, eps) || equals(x3, x1, eps)) {
                    x3 = xi;
                    f3 = fi;
                }
            }
        } while (true);
        return x2;
    }
    boolean threeNotEquals(double a, double b, double c, double eps) {
        return !equals(a, b, eps) && !equals(a, c, eps) && !equals(b, c, eps);
    }
    boolean equals(double a, double b, double eps) {
        return abs(a - b) < eps;
    }

    static double minOfParabola(double x1, double x2, double x3, double f1, double f2, double f3) {
        double a0, a1, a2;
        a0 = f1;
        a1 = (f2 - a0) / (x2 - x1);
        a2 = ((f3 - f1) / (x3 - x1) - a1) / (x3 - x2);
        return 0.5 * (x1 + x2 - a1 / a2);
    }
}

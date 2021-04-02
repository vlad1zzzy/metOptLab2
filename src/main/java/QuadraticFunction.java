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

    public double findMin(double a, double b, final double eps, double[] x) {
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

}

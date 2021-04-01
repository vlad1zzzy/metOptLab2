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

    public double reducedMultiplyVectors(double[] x1, double[] x2) {
        double ans = 0;
        for (int i = 0; i < x1.length; i++) {
            ans += x1[i] * x2[i];
        }
        return ans;
    }

}

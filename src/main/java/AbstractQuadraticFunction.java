public abstract class AbstractQuadraticFunction implements QuadraticFunction {
    @Override
    public double[] findGradient(double[] x, double k) {
        double[] ans = new double[getDimension()];
        for (int i = 0; i < getDimension(); i++) {
            ans[i] = k * findFdkX(i, x);
        }
        return ans;
    }



    @Override
    public double dfNormalize(double[] x) {
        double[] gradient = findGradient(x, 1);
        return Math.sqrt(reducedMultiplyVectors(gradient, gradient));
    }

    @Override
    public double[] reducedAddVectors(double[] x1, double[] x2, double k2) {
        double[] ans = new double[x1.length];
        for (int i = 0; i < x1.length; i++) {
            ans[i] = x1[i] + k2 * x2[i];
        }
        return ans;
    }

    protected double reducedMultiplyVectors(double[] x1, double[] x2) {
        double ans = 0;
        for (int i = 0; i < x1.length; i++) {
            ans += x1[i] * x2[i];
        }
        return ans;
    }

    private double findG(double[] x, double lambda) {
        double[] newX = new double[getDimension()];
        for (int i = 0; i < getDimension(); i++) {
            newX[i] = x[i] - lambda * findFdkX(i, x);
        }
        return findFx(newX);
    }
    @Override
    public double findMin(double a, double b, double[] x) {
        double eps = 0.000001;
        double s = 0.00000001;
        double x1, x2, f1, f2;
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

public interface QuadraticFunction {
    double findFx(double[] x);

    double findFdkX(int k, double[] x);

    double[] findGradient(double[] x, double k);

    double dfNormalize(double[] x);

    double[] reducedAddVectors(double[] x1, double[] x2, double k2);

    int getDimension();
    }

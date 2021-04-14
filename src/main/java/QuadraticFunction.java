public interface QuadraticFunction {
    double findFx(double[] x);

    double findFdkX(int k, double[] x);

    double[] findGradient(double[] x, double k);

    double dfNormalize(double[] x);

    double[] reducedAddVectors(double[] x1, double[] x2, double k2);

    double dichotomy(double[] x, double a, double b, double eps);

    double fibonacci(double[] x, double a, double b, double eps);

    double goldenSection(double[] x, double a, double b, double eps);

    double brent(double[] x, double a, double c, double eps);

    double parabola(double[] x, double a, double b, double eps);

    int getDimension();
}

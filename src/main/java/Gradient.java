
public class Gradient {
    public static double EPS = 0.00001;

    public static double[][] a = {{4, 0}, {0, 1}};
    public static double[] b = {4, -6};
    public static double c = 10;

    public static void main(String[] args) {
        QuadraticFunction function = new QuadraticFunction(a, b, c);
        GradientGreatDescent.gradient(new double[]{10, 10}, function, EPS, -10, 10);
        ConjugateGradient.gradient(new double[]{10, 10}, function, EPS, -10, 10, 2);
    }
}

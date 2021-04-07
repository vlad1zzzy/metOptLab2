
public class Gradient {
    public static double EPS = 0.00001;

    public static double[][] a = {{64, 126}, {126, 64}};
    public static double[] b = {-10, 30};
    public static double c = 13;

    public static void main(String[] args) {
        QuadraticFunction function = new QuadraticFunction(a, b, c);
        GradientGreatDescent.gradient(new double[]{10, 10}, function, EPS, -10, 10);
        ConjugateGradient.gradient(new double[]{10, 10}, function, EPS, -10, 10, 2);
        ConstantStepGradient.gradient(new double[]{10, 10}, function, EPS, -10, 10);
    }
}

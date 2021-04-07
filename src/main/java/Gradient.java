
public class Gradient {
    public static double EPS = 0.0001;

    public static void main(String[] args) {
        QuadraticFunction function1 = new QuadraticFunction(new double[][]{{64, 126}, {126, 64}}, new double[]{-10, 30}, 13);
        QuadraticFunction function2 = new QuadraticFunction(new double[][]{{254, 506}, {506, 254}}, new double[]{50, 130}, -111);
        QuadraticFunction function3 = new QuadraticFunction(new double[][]{{211, -420}, {-420, 211}}, new double[]{-192, 50}, -25);
        QuadraticFunction function = function3;
        for (int a = 100; a <= 1000000; a *= 10) {
            GradientGreatDescent.gradient(new double[]{10, 10}, function, EPS, -a, a);
            ConjugateGradient.gradient(new double[]{10, 10}, function, EPS, -a, a, 2);
            ConstantStepGradient.gradient(new double[]{10, 10}, function, EPS, -a, a);
            System.out.println();
        }
    }
}


public class Gradient {
    public static double EPS = 0.0000001;

    public static double f(double x, double y) {
        return 4 * x * x + 4 * x + y * y - 6 * y + 10;
    }

    public static double f_dx(double x, double y) {
        return (f(x + EPS, y) - f(x, y)) / EPS;
    }

    public static double f_dy(double x, double y) {
        return (f(x, y + EPS) - f(x, y)) / EPS;
    }

    public static double g(double x, double y, double lambda) {
        return f(x - lambda * f_dx(x, y), y - lambda * f_dy(x, y));
    }

    public static double df_normalize(double x, double y) {
        return Math.sqrt((f_dx(x, y)) * (f_dx(x, y)) + (f_dy(x, y)) * (f_dy(x, y)));
    }

    public static double[][] a = {{4, 0}, {0, 1}};
    public static double[] b = {4, -6};
    public static double c = 10;

    public static void main(String[] args) {
        GradientGreatDescent ggd = new GradientGreatDescent();
        ConjugateGradient conjugateGradient = new ConjugateGradient();
        QuadraticFunction function = new QuadraticFunction(a, b, c);
        System.out.println(function.findFx(new double[]{1, 1}));
        System.out.println(function.findFdkX(0,new double[]{1, 1}));
        System.out.println(function.findFdkX(1,new double[]{1, 1}));
        ggd.gradient(100, 100, EPS);
        conjugateGradient.gradient(new double[]{100, 100}, 2, function, EPS);
    }
    /*
    24.555909447114303, -11.023900002708501
     */
}

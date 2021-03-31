
public class Gradient {

    public static double EPS;

    public static double f(double x, double y) {
        return 2 * x * x + 3 * y * y + 5 * x - 4 * y;
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

    public static void main(String[] args) {
        final double eps = 0.0000001;
        GradientGreatDescent ggd = new GradientGreatDescent();
        ggd.gradient(100, 100, eps);
    }
}

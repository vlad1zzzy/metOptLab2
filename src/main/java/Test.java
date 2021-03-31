import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;

public class Test {
    static double eps = 0.0000001;
    static MultivariateDifferentiableFunction f = new MultivariateDifferentiableFunction() {
        @Override
        public DerivativeStructure value(DerivativeStructure[] derivativeStructures) throws MathIllegalArgumentException {
            return derivativeStructures[0].multiply(derivativeStructures[0]).multiply(derivativeStructures[1]);
        }

        @Override
        public double value(double[] doubles) {
            return 0;
        }
    };

    public static void main(String[] args) {

    }
}

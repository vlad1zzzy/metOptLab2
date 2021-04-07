import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.linear.*;

import java.util.Arrays;

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
        int[] a = new int[]{1,2,3};
        int[] b = a;
        b[0] = 2;
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }
}

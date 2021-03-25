import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;

public class Test {
    public static void main(String[] args) {
        int params = 2;
        int order = 2;
        double xRealValue =  2.5;
        double yRealValue = -1.3;
        DerivativeStructure x = new DerivativeStructure(params, order, 0, xRealValue);
        DerivativeStructure y = new DerivativeStructure(params, order, 1, yRealValue);
        DerivativeStructure f = DerivativeStructure.hypot(x, y);
        DerivativeStructure g = f.log();
        System.out.println("g        = " + g.getValue());
        System.out.println("dg/dx    = " + g.getPartialDerivative(1, 0));
        System.out.println("dg/dy    = " + g.getPartialDerivative(0, 1));
        System.out.println("d2g/dx2  = " + g.getPartialDerivative(2, 0));
        System.out.println("d2g/dxdy = " + g.getPartialDerivative(1, 1));
        System.out.println("d2g/dy2  = " + g.getPartialDerivative(0, 2));
    }
}

import java.util.function.Supplier;

public class MathThread extends Thread {
    private final Supplier<Double> expression;
    private double result;

    public MathThread(Supplier<Double> expression) {
        this.expression = expression;
    }

    public void run() { result = expression.get(); }

    public double getValue() { return result; }
}
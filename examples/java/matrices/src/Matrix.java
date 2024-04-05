import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private final double[][] values;

    public Matrix(double[][] values) {
        this.values = values;
    }

    public double sum() {
        var result = 0.0;
        for (double[] value : values) {
            result += addRow(value);
        }
        return result;
    }

    public double sumParallel() {
        List<MathThread> mathThread = new ArrayList<>();

        // se crean los threads, acá se podrían startear
        for (double[] value : values) {
            mathThread.add(new MathThread(() -> addRow(value)));
        }

        // se startean todos
        mathThread.forEach(Thread::start);

        // acá se calcula el valor
        mathThread.forEach(m -> {
            try {
                m.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // acá se pasan todos los getValue a una colección y luego se suma
        return mathThread.stream().mapToDouble(MathThread::getValue).sum();
    }

    public Matrix addSerial(Matrix other) {
        double[][] result = new double[values.length][];
        for (int i = 0; i < values.length; i++) {
            int cols = values[i].length;
            var row = new double[cols];
            for (int j = 0; j < cols; j++) {
                row[j] = values[i][j] + other.values[i][j];
            }
            result[i] = row;
        }
        return new Matrix(result);
    }

    public Matrix addParallel(Matrix other) {
        throw new RuntimeException("Implement me!");
    }

    private double addRow(double[] value) {
        var result = 0.0;
        for (double v : value) {
            result += v;
        }
        return result;
    }


}
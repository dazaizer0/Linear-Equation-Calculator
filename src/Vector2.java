public class Vector2<T> {
    private T X;
    private T Y;

    public Vector2(T x, T y) {
        this.X = x;
        this.Y = y;
    }

    public T getXValue() {
        return this.X;
    }

    public T getYValue() {
        return this.Y;
    }

    public void setXValue(T x) {
        this.X = x;
    }

    public void setYValue(T y) {
        this.Y = y;
    }
}
package task3_1;

public abstract class Shape implements Drawable {
    protected String shapeColor;

    public Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public String getShapeColor() {
        return shapeColor;
    }

    public abstract double calcArea();

    @Override
    public void draw() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("%-10s | Колір: %-10s | Площа: %8.2f",
                getClass().getSimpleName(), shapeColor, calcArea());
    }
}

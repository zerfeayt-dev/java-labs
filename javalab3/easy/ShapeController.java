package task3_1;

import java.util.Arrays;
import java.util.Comparator;

public class ShapeController {
    private final Shape[] shapes;
    private final ShapeView view;

    public ShapeController(Shape[] shapes, ShapeView view) {
        this.shapes = shapes;
        this.view = view;
    }

    public void displayAll() {
        view.printShapes(shapes);
    }

    public double calcTotalArea() {
        double sum = 0;
        for (Shape s : shapes) {
            sum += s.calcArea();
        }
        return sum;
    }

    public double calcAreaByType(Class<? extends Shape> type) {
        double sum = 0;
        for (Shape s : shapes) {
            if (type.isInstance(s)) {
                sum += s.calcArea();
            }
        }
        return sum;
    }

    public void sortByArea() {
        Comparator<Shape> areaComparator = Comparator.comparingDouble(Shape::calcArea);
        Arrays.sort(shapes, areaComparator);
    }

    public void sortByColor() {
        Comparator<Shape> colorComparator = Comparator.comparing(Shape::getShapeColor);
        Arrays.sort(shapes, colorComparator);
    }
}

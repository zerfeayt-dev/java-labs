package task3_1;

public class ShapeView {
    public void printMessage(String message) {
        System.out.println("\n=== " + message + " ===");
    }

    public void printShapes(Shape[] shapes) {
        for (Shape shape : shapes) {
            shape.draw();
        }
    }

    public void printArea(String label, double area) {
        System.out.printf("%s: %.2f%n", label, area);
    }
}

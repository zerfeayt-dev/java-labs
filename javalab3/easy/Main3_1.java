package task3_1;

public class Main3_1 {
    public static void main(String[] args) {
        // Масив із 10 фігур із заздалегідь підготовленими даними
        Shape[] dataset = new Shape[]{
                new Rectangle("Червоний", 4.0, 5.0),
                new Circle("Синій", 3.0),
                new Triangle("Зелений", 6.0, 4.0),
                new Rectangle("Жовтий", 2.5, 8.0),
                new Circle("Червоний", 2.0),
                new Triangle("Білий", 5.0, 3.0),
                new Rectangle("Синій", 7.0, 3.0),
                new Circle("Зелений", 4.5),
                new Triangle("Жовтий", 8.0, 5.0),
                new Rectangle("Білий", 3.0, 3.0)
        };

        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(dataset, view);

        // 1. Відображення початкового набору даних
        view.printMessage("Початковий набір фігур (10 елементів)");
        controller.displayAll();

        // 2. Обчислення сумарної площі всіх фігур
        view.printMessage("Обчислення площ");
        view.printArea("Сумарна площа всіх фігур", controller.calcTotalArea());

        // 3. Обчислення сумарної площі фігур заданого виду
        view.printArea("Сумарна площа прямокутників (Rectangle)", controller.calcAreaByType(Rectangle.class));
        view.printArea("Сумарна площа кіл (Circle)", controller.calcAreaByType(Circle.class));
        view.printArea("Сумарна площа трикутників (Triangle)", controller.calcAreaByType(Triangle.class));

        // 4. Сортування за збільшенням площі (Comparator)
        view.printMessage("Впорядкування за зростанням площі");
        controller.sortByArea();
        controller.displayAll();

        // 5. Сортування за кольором (Comparator)
        view.printMessage("Впорядкування за кольором");
        controller.sortByColor();
        controller.displayAll();
    }
}

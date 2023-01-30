public class Application {

    public static void main(String[] args) {
        Circle circle = new Circle();
        System.out.println(circle.toString());
        Circle circle1 = new Circle(5);
        System.out.println(circle1.toString());
        Circle circle2 = new Circle(5, "Yellow");
        System.out.println(circle2.toString());
    }

}

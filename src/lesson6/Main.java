package lesson6;

public class Main {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Барсик");
        Dog dog1 = new Dog("Шарик");

        cat1.run(65);
        cat1.jump(1.1);

        dog1.run(350);
        dog1.jump(0.9);
        dog1.swim(300);
    }
}

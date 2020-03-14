package lesson6;

import java.util.Random;

public class Cat extends Animal {

    public Cat(String name) {
        super(name, new Random().nextInt(21)/10.0, new Random().nextInt(91) + 10);
    }

    public Cat(String name, double maxJumpHeight, int maxRunDistance) {
        super(name, maxJumpHeight, maxRunDistance);
    }

    @Override
    protected void run(int distance) {
        System.out.printf("Кот %s ", getName());
        super.run(distance);
    }

    @Override
    protected void jump(double height) {
        System.out.printf("Кот %s ", getName());
        super.jump(height);
    }
}

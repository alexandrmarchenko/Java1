package lesson6;

import java.util.Random;

public class Dog extends SwimmingAnimal {


    public Dog(String name, double maxJumpHeight, int maxRunDistance, int maxSwimDistance) {
        super(name, maxJumpHeight, maxRunDistance, maxSwimDistance);
    }


    public Dog(String name) {
        this(name, (new Random().nextInt(5)+ 10)/10.0, new Random().nextInt(501) + 100, new Random().nextInt(301) + 100);
    }

    @Override
    protected void run(int distance) {
        System.out.printf("Собака %s ", getName());
        super.run(distance);
    }

    @Override
    protected void jump(double height) {
        System.out.printf("Собака %s ", getName());
        super.jump(height);
    }

    @Override
    protected void swim(int distance) {
        System.out.printf("Собака %s ", getName());
        super.swim(distance);
    }
}

package lesson6;

import java.util.Random;

public class Dog extends Animal implements ISwimmingAnimal {

    private int maxSwimDistance;

    public Dog(String name, double maxJumpHeight, int maxRunDistance, int maxSwimDistance) {
        super(name, maxJumpHeight, maxRunDistance);
        this.maxSwimDistance = maxSwimDistance;
    }

    public Dog(String name) {
        super(name, (new Random().nextInt(5)+ 10)/10.0, new Random().nextInt(501) + 100);
        this.maxSwimDistance = new Random().nextInt(301) + 100;
    }

    public int getMaxSwimDistance() {
        return maxSwimDistance;
    }

    public void setMaxSwimDistance(int maxSwimDistance) {
        this.maxSwimDistance = maxSwimDistance;
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

    protected void swim(int distance) {
        System.out.printf("Собака %s заплыв на %d м, результат -> %s\n", getName(), distance, canSwim(distance));
    }

    @Override
    public boolean canSwim(int distance) {
        return distance < maxSwimDistance;
    }
}

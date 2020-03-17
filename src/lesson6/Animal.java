package lesson6;

import java.util.Random;

public class Animal {

    private String name;

    private double maxJumpHeight;
    private int maxRunDistance;

    public Animal(String name, double maxJumpHeight, int maxRunDistance) {
        this.name = name;
        this.maxJumpHeight = maxJumpHeight;
        this.maxRunDistance = maxRunDistance;
    }

    public Animal(String name) {
        this(name, 0.0, 0);
    }

    protected void run(int distance) {
        System.out.printf("бег на %d м, результат -> %s\n", distance, canRun(distance));
    }

    protected void jump(double height) {
        System.out.printf("прыжок на %s м, результат -> %s\n", height, canJump(height));
    }

    private boolean canRun(int distance) {
        return distance < maxRunDistance;
    }

    private boolean canJump(double height) {
        return  height < maxJumpHeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxJumpHeight() {
        return maxJumpHeight;
    }

    public void setMaxJumpHeight(double maxJumpHeight) {
        this.maxJumpHeight = maxJumpHeight;
    }

    public int getMaxRunDistance() {
        return maxRunDistance;
    }

    public void setMaxRunDistance(int maxRunDistance) {
        this.maxRunDistance = maxRunDistance;
    }
}

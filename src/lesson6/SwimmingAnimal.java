package lesson6;

import java.util.Random;

public class SwimmingAnimal extends Animal {

    private int maxSwimDistance;

    public SwimmingAnimal(String name, double maxJumpHeight, int maxRunDistance, int maxSwimDistance) {
        super(name, maxJumpHeight, maxRunDistance);
        this.maxSwimDistance = maxSwimDistance;
    }

    protected void swim(int distance) {
        System.out.printf("заплыв на %d м, результат -> %s\n", distance, canSwim(distance));
    }

    private boolean canSwim (int distance){
        return distance < maxSwimDistance;
    }

    public int getMaxSwimDistance() {
        return maxSwimDistance;
    }

    public void setMaxSwimDistance(int maxSwimDistance) {
        this.maxSwimDistance = maxSwimDistance;
    }
}

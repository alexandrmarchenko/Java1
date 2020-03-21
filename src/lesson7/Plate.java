package lesson7;

public class Plate {

    private final int MAX_FOOD = 70;
    private int food;
    public Plate(int food) {
        this.food = food;
    }

    public Plate() {
        this(0);
    }

    public int getFood() {
        return food;
    }
    public void decreaseFood(int food) {
        this.food -= food;
    }
    public void addFood (int food){
        this.food = this.food + food >= MAX_FOOD ? MAX_FOOD : this.food + food;
    }

    public void info() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Plate{" +
                "food=" + food +
                '}';
    }
}

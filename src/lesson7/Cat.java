package lesson7;

public class Cat {

    private final int HUNGRY_LIMIT = 30;
    private final int APPETITE_PER_TIME = 5;

    private final String name;
    private int appetite;
    private boolean hungry;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }

    public void eat(Plate plate) {
        if(hungry) {
            int foodEat = appetite >= plate.getFood() ? plate.getFood() : appetite;
            appetite -= foodEat;
            doEat(plate, foodEat);
            hungry = appetite < HUNGRY_LIMIT;
        }
    }

    private void doEat (Plate plate, int food) {
        plate.decreaseFood(food);
    }

    public void increaseAppetite () {
        appetite += APPETITE_PER_TIME;
        hungry = appetite >= HUNGRY_LIMIT;
    }

    public void info() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", appetite=" + appetite +
                ", hungry=" + hungry +
                '}';
    }
}

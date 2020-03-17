package lesson7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Feeder {

    private List<Cat> cats = new ArrayList<>();

    public void addCat(Cat cat) {
        this.cats.add(cat);
    }

    public void removeCat(Cat cat) {
        this.cats.remove(cat);
    }

    public void fillPlate(Plate plate, int food) {
        plate.addFood(food);
        cats.sort((o1, o2) -> o2.getAppetite() - o1.getAppetite());
        for (Cat cat : cats) {
            cat.eat(plate);
        }
    }
}

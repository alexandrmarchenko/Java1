package lesson7;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Feeder feeder = new Feeder();

        Cat[] cats = new Cat[]{
                new Cat("Пушок", 10),
                new Cat("Мурзик", 20),
                new Cat("Борис", 15),
        };

        Plate[] plates = new Plate[] {
          new Plate(),
          new Plate()
        };

        for (Cat cat : cats) {
            feeder.addCat(cat);
        }

        new CatThread("CatThread", cats).start();
        for (;;) {
            Thread.sleep(4000);
            for (Plate plate : plates) {
                    feeder.fillPlate(plate,10);
                    plate.info();
                }
        }
    }
}


class CatThread extends Thread {

    Cat[] cats;
    CatThread(String name, Cat[] cats){
        super(name);
        this.cats = cats;
    }

    public void run(){
        for(;;) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted");
            }
            for (Cat cat : cats) {
                cat.increaseAppetite();
                cat.info();
            }
        }
    }
}
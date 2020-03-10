package lesson5;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Employer[] emp = new Employer[5];
        emp[0] = new Employer("Иванов",null,null,null,null,null,1000,10);
        emp[1] = new Employer("Петров",null,null,null,null,null,1500,50);
        emp[2] = new Employer("Сидоров",null,null,null,null,null,1100,20);
        emp[3] = new Employer("Соколов",null,null,null,null,null,1200,60);
        emp[4] = new Employer("Орлов",null,null,null,null,null,900,70);

        Arrays.stream(emp)
        .filter( employer -> employer.getAge() > 40)
        .forEach( employer -> System.out.println(employer.toString()));
    }
}

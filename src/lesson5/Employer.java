package lesson5;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employer {

    private String name;
    private String lastname;
    private String patronymic;
    private String position;
    private String email;
    private String phone;
    private double salary;
    private int age;

    public Employer(String lastname, String name, String patronymic, String position, String email, String phone, double salary, int age) {
        if(lastname!=null && !lastname.isBlank()){
            this.lastname = lastname.substring(0,1).toUpperCase() + lastname.substring(1);
        }
        if(name!=null && !name.isBlank()){
            this.name = name.substring(0,1).toUpperCase() + name.substring(1);
        }
        if(patronymic!=null && !patronymic.isBlank()){
            this.patronymic = patronymic.substring(0,1).toUpperCase() + patronymic.substring(1);
        }
        this.position = position;
        if(email!=null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            this.email = email;
        }
        if(phone!=null && phone.matches(".*[^0-9].*")) {
            this.phone = phone;
        }
        this.salary = salary;
        this.age = age;
    }

    public Employer(String lastname, String name, String patronymic, String position, String email, String phone, double salary) {
        this(lastname, name, patronymic, position, email, phone, salary, 0);
    }

    public Employer(String lastname, String name, String patronymic, String position, String email, String phone) {
        this(lastname, name, patronymic, position, email, phone, 0);
    }

    public Employer(String lastname, String name, String patronymic, String position, String email) {
        this(lastname, name, patronymic, position, email, null);
    }

    public Employer(String lastname, String name, String patronymic, String position) {
        this(lastname, name, patronymic, position, null);
    }

    public Employer(String lastname, String name, String patronymic) {
        this(lastname, name, patronymic, null);
    }

    public Employer(String lastname, String name) {
        this(lastname, name, null);
    }

    public Employer(String lastname) {
        this(lastname, null);
    }

    public Employer() {
        this(null);
    }

    @Override
    public String toString() {
        return "Employer{" +
                "lastname='" + lastname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        if(lastname != null && !lastname.isBlank()) {
            this.lastname = lastname;
        } else{
            System.out.println("Недопустимая фамилия. Введите значение.");
        }
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            this.email = email;
        }else{
            System.out.println("Неверный формат почты");
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if(!phone.matches(".*[^0-9].*")) {
            this.phone = phone;
        } else {
            System.out.println("Номер не должен содержать символов");
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.out.println("Введена некорректная зарплата");
        }

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 150) {
            this.age = age;
        } else {
            System.out.println("Введен некорректный возраст");
        }
    }
}

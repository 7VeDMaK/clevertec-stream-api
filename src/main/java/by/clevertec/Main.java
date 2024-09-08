package by.clevertec;

import by.clevertec.model.*;
import by.clevertec.util.Util;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
//        task4();
//        task5();
//        task6();
//        task7();
//        task8();
//        task9();
//        task10();
        task11();
//        task12();
//        task13();
//        task14();
//        task15();
//        task16();
//        task17();
//        task18();
//        task19();
//        task20();
//        task21();
//        task22();
    }

    public static void task1() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip(7 * 2)
                .limit(7)
                .forEach(System.out::println);
    }

    public static void task2() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getOrigin().equals("Japanese"))
                .peek(animal -> animal.setBread(animal.getBread().toUpperCase()))
                .forEach(System.out::println);
    } // ??? Добавить наверно

    public static void task3() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(origin -> origin.startsWith("A"))
                .distinct()
                .forEach(System.out::println);
    }

    public static void task4() {
        List<Animal> animals = Util.getAnimals();
        long count = animals.stream()
                .filter(animal -> animal.getGender().equals("Female"))
                .count();
        System.out.println("count = " + count);
    }

    public static void task5() {
        List<Animal> animals = Util.getAnimals();
        Optional<Animal> isHungurian = animals.stream()
                .filter(animal -> animal.getAge() <= 30 && animal.getAge() >= 20)
                .filter(animal -> animal.getOrigin().startsWith("Hungarian"))
                .findAny();
        if (isHungurian.isPresent()) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public static void task6() {
        List<Animal> animals = Util.getAnimals();
        Optional<Animal> isUnusualGender = animals.stream()
                .filter(animal -> !animal.getGender().equals("Female") && !animal.getGender().equals("Male"))
                .findAny();
        if (isUnusualGender.isPresent()) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public static void task7() {
        List<Animal> animals = Util.getAnimals();
        Optional<Animal> isOceaniaAnimal = animals.stream()
                .filter(animal -> animal.getOrigin().equals("Oceania"))
                .findAny();
        if (isOceaniaAnimal.isPresent()) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public static void task8() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip(99)
                .map(Animal::getAge)
                .forEach(System.out::println);
    } // max

    public static void task9() {
        List<Animal> animals = Util.getAnimals();
        char[] smallestArray = animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .min(Comparator.comparingInt(arr -> arr.length))
                .orElse(new char[0]);
        System.out.println("smallestArray = " + smallestArray.length);
    }

    public static void task10() {
        List<Animal> animals = Util.getAnimals();
        Integer sum = animals.stream()
                .map(Animal::getAge)
                .reduce(Integer::sum).orElse(0);
        System.out.println("sum = " + sum);
    }

    public static void task11() {
        List<Animal> animals = Util.getAnimals();
        Long count = animals.stream()
                .filter(animal -> animal.getOrigin().equals("Indonesian"))
                .map(Animal::getAge)
                .count();
        Double sum = Double.valueOf(animals.stream()
                .filter(animal -> animal.getOrigin().equals("Indonesian"))
                .map(Animal::getAge)
                .reduce(Integer::sum).orElse(0));
        System.out.println("average = " + sum / count);
    }

    public static void task12() {
        List<Person> persons = Util.getPersons();
//        persons.stream() Продолжить ...
    }

    public static void task13() {
        List<House> houses = Util.getHouses();
//        houses.stream() Продолжить ...
    }

    public static void task14() {
        List<Car> cars = Util.getCars();
//        cars.stream() Продолжить ...
    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();
//        flowers.stream() Продолжить ...
    }

    public static void task16() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task17() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
//        students.stream() Продолжить ...
    }

    public static void task19() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task21() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }
}

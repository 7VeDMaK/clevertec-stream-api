package by.clevertec.util;

import by.clevertec.model.Animal;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskUtil {
    public static List<Animal> task1(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip(7 * 2)
                .limit(7)
                .collect(Collectors.toList());
    }

    public static List<Animal> task2(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> animal.getOrigin().equals("Japanese"))
                .peek(animal -> {
                    if ("Female".equals(animal.getGender())) {
                        animal.setBread(animal.getBread().toUpperCase());
                    }
                })
                .toList();
    }

    public static List<String> task3(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(origin -> origin.startsWith("A"))
                .distinct().toList();
    }

    public static Long task4(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> animal.getGender().equals("Female"))
                .count();
    }

    public static boolean task5(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                .anyMatch(animal -> animal.getOrigin().equals("Hungarian"));
    }

    public static boolean task6(List<Animal> animals) {
        return animals.stream()
                .anyMatch(animal -> !animal.getGender().equals("Female")
                        && !animal.getGender().equals("Male"));
    }

    public static boolean task7(List<Animal> animals) {
        return animals.stream()
                .noneMatch(animal -> animal.getOrigin().equals("Oceania"));
    }

    public static Integer task8(List<Animal> animals) {
        return animals.stream()
                .sorted(Comparator.comparing(Animal::getBread)
                        .thenComparingInt(Animal::getAge))
                .limit(100)
                .max(Comparator.comparingInt(Animal::getAge))
                .map(Animal::getAge).orElse(0);
    }

    public static Integer task9(List<Animal> animals) {
        return animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .mapToInt(arr -> arr.length)
                .min()
                .orElse(0);
    }

    public static Integer task10(List<Animal> animals) {
        return animals.stream()
                .map(Animal::getAge)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public static Double task11(List<Animal> animals) {
        long count = animals.stream()
                .filter(animal -> animal.getOrigin().equals("Indonesian"))
                .count();
        int sum = animals.stream()
                .filter(animal -> animal.getOrigin().equals("Indonesian"))
                .mapToInt(Animal::getAge)
                .sum();
        return (count > 0) ? (double) sum / count : 0.0;
    }
}

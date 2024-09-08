package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.TaskUtil;
import by.clevertec.util.Util;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
//        task11();
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
        TaskUtil.task1(animals)
                .forEach(System.out::println);
    }

    public static void task2() {
        List<Animal> animals = Util.getAnimals();
        TaskUtil.task2(animals)
                .forEach(System.out::println);
    }

    public static void task3() {
        List<Animal> animals = Util.getAnimals();
        TaskUtil.task3(animals)
                .forEach(System.out::println);
    }

    public static void task4() {
        List<Animal> animals = Util.getAnimals();
        System.out.println("count = " + TaskUtil.task4(animals));
    }

    public static void task5() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(TaskUtil.task5(animals) ? "Yes" : "No");
    }

    public static void task6() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(TaskUtil.task6(animals) ? "Yes" : "No");
    }

    public static void task7() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(TaskUtil.task7(animals) ? "No" : "Yes");
    }

    public static void task8() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(TaskUtil.task8(animals));
    }

    public static void task9() {
        List<Animal> animals = Util.getAnimals();
        System.out.println("Length of the smallest array = " + TaskUtil.task9(animals));
    }

    public static void task10() {
        List<Animal> animals = Util.getAnimals();
        System.out.println("Sum = " + TaskUtil.task10(animals));
    }

    public static void task11() {
        List<Animal> animals = Util.getAnimals();
        System.out.println("Average = " + TaskUtil.task11(animals));
    }

    public static void task12() {
        List<Person> persons = Util.getPersons();
        persons.stream()
                .filter(person -> person.getGender().equals("Male"))
                .filter(person -> {
                    LocalDate now = LocalDate.now();
                    int age = Period.between(person.getDateOfBirth(), now).getYears();
                    return age >= 18 && age <= 27;
                })
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    public static void task13() {
        final int MAX_EVACUATION_CAPACITY = 500;
        final int CHILD_AGE_THRESHOLD = 18;
        final int SENIOR_AGE_THRESHOLD = 65;
        List<House> houses = Util.getHouses();
        List<Person> peopleToEvacuate = houses.stream()
                .flatMap(house -> house.getPersonList().stream())
                .filter(person -> {
                    int age = Period.between(person.getDateOfBirth(), LocalDate.now()).getYears();
                    return person.getOccupation().equalsIgnoreCase("Hospital")
                            || age < CHILD_AGE_THRESHOLD
                            || age >= SENIOR_AGE_THRESHOLD;
                })
                .sorted((p1, p2) -> {
                    if (p1.getOccupation().equalsIgnoreCase("Hospital") && !p2.getOccupation().equalsIgnoreCase("Hospital")) {
                        return -1;
                    } else if (!p1.getOccupation().equalsIgnoreCase("Hospital") && p2.getOccupation().equalsIgnoreCase("Hospital")) {
                        return 1;
                    }
                    int age1 = Period.between(p1.getDateOfBirth(), LocalDate.now()).getYears();
                    int age2 = Period.between(p2.getDateOfBirth(), LocalDate.now()).getYears();

                    if (age1 < CHILD_AGE_THRESHOLD && age2 >= CHILD_AGE_THRESHOLD) {
                        return -1;
                    } else if (age1 >= CHILD_AGE_THRESHOLD && age2 < CHILD_AGE_THRESHOLD) {
                        return 1;
                    } else if (age1 >= SENIOR_AGE_THRESHOLD && age2 < SENIOR_AGE_THRESHOLD) {
                        return -1;
                    } else if (age1 < SENIOR_AGE_THRESHOLD && age2 >= SENIOR_AGE_THRESHOLD) {
                        return 1;
                    }
                    return 0;
                })
                .limit(MAX_EVACUATION_CAPACITY)
                .toList();
        peopleToEvacuate.forEach(System.out::println);
    }

    public static void task14() {
        final double COST_PER_TON = 7.14;
        final int MASS_THRESHOLD_ECHELON_2 = 1500;
        final int MASS_THRESHOLD_ECHELON_3 = 4000;
        final int PRICE_THRESHOLD_ECHELON_5 = 40000;
        List<Car> cars = Util.getCars();
        Map<String, List<Car>> echelonCars = new HashMap<>();
        List.of("Turkmenistan", "Uzbekistan", "Kazakhstan", "Kyrgyzstan", "Russia", "Mongolia")
                .forEach(country -> echelonCars.put(country, new ArrayList<>()));
        List<Car> remainingCars = cars.stream()
                .filter(car -> {
                    if (car.getCarMake().equalsIgnoreCase("Jaguar") || car.getColor().equalsIgnoreCase("White")) {
                        echelonCars.get("Turkmenistan").add(car);
                        return false;
                    }
                    return true;
                })
                .toList();
        remainingCars = remainingCars.stream()
                .filter(car -> {
                    if (car.getMass() < MASS_THRESHOLD_ECHELON_2 &&
                            List.of("BMW", "Lexus", "Chrysler", "Toyota").contains(car.getCarMake())) {
                        echelonCars.get("Uzbekistan").add(car);
                        return false;
                    }
                    return true;
                })
                .toList();
        remainingCars = remainingCars.stream()
                .filter(car -> {
                    if ((car.getColor().equalsIgnoreCase("Black") && car.getMass() > MASS_THRESHOLD_ECHELON_3) ||
                            List.of("GMC", "Dodge").contains(car.getCarMake())) {
                        echelonCars.get("Kazakhstan").add(car);
                        return false;
                    }
                    return true;
                })
                .toList();
        remainingCars = remainingCars.stream()
                .filter(car -> {
                    if (car.getReleaseYear() < 1982 || List.of("Civic", "Cherokee").contains(car.getCarModel())) {
                        echelonCars.get("Kyrgyzstan").add(car);
                        return false;
                    }
                    return true;
                })
                .toList();
        remainingCars = remainingCars.stream()
                .filter(car -> {
                    if (!List.of("Yellow", "Red", "Green", "Blue").contains(car.getColor()) || car.getPrice() > PRICE_THRESHOLD_ECHELON_5) {
                        echelonCars.get("Russia").add(car);
                        return false;
                    }
                    return true;
                })
                .toList();
        remainingCars.stream()
                .filter(car -> car.getVin().contains("59"))
                .forEach(car -> echelonCars.get("Mongolia").add(car));

        Map<String, Double> transportCosts = new HashMap<>();
        double totalRevenue = 0;

        for (String country : echelonCars.keySet()) {
            int totalMass = echelonCars.get(country).stream().mapToInt(Car::getMass).sum();
            double transportCost = (totalMass / 1000.0) * COST_PER_TON;
            transportCosts.put(country, transportCost);
            totalRevenue += transportCost;
        }

        transportCosts.forEach((country, cost) -> System.out.println(country + ": " + String.format("%.2f", cost) + " $"));
        System.out.println("Total Revenue: " + String.format("%.2f", totalRevenue) + " $");
    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();
        double totalCost = flowers.stream()
                .sorted(Comparator
                        .comparing(Flower::getOrigin).reversed()
                        .thenComparing(Flower::getPrice)
                        .thenComparing(Comparator.comparing(Flower::getWaterConsumptionPerDay).reversed())
                )
                .filter(flower -> flower.getCommonName().compareToIgnoreCase("C") >= 0 &&
                        flower.getCommonName().compareToIgnoreCase("S") <= 0)
                .filter(flower -> flower.isShadePreferred() &&
                        flower.getFlowerVaseMaterial().stream().anyMatch(
                                material -> List.of("Glass", "Aluminum", "Steel").contains(material)))
                .mapToDouble(flower -> {
                    final double WATER_COST_PER_CUBIC_METER = 1.39;
                    final int DAYS_IN_YEAR = 365;
                    final int YEARS = 5;
                    double waterConsumptionPerYear = flower.getWaterConsumptionPerDay() * DAYS_IN_YEAR;
                    double waterCostForFiveYears = (waterConsumptionPerYear * YEARS / 1000) * WATER_COST_PER_CUBIC_METER;
                    return flower.getPrice() + waterCostForFiveYears;
                })
                .sum();

        System.out.println("Total maintenance cost: " + String.format("%.2f", totalCost) + " $");
    }

    public static void task16() {
        List<Student> students = Util.getStudents();
        students.stream()
                .filter(student -> student.getAge() < 18)
                .sorted(Comparator.comparing(Student::getSurname)
                        .thenComparingInt(Student::getAge))
                .forEach(student -> System.out.println(
                        "Surname: " + student.getSurname() + ", Age: " + student.getAge()));

    }

    public static void task17() {
        List<Student> students = Util.getStudents();
        students.stream()
                .map(Student::getGroup)
                .distinct()
                .forEach(System.out::println);
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        Map<String, Double> facultyAverageAges = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.averagingInt(Student::getAge)
                ));

        facultyAverageAges.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry ->
                        System.out.println("Faculty: " + entry.getKey() + ", Average Age: " + String.format("%.2f", entry.getValue()))
                );
    }

    public static void task19() {
        final String GROUP = "M-2";
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();

        List<Integer> studentIdPassed = examinations.stream()
                .filter(examination -> examination.getExam3() > 4)
                .map(Examination::getStudentId)
                .toList();

        students.stream().
                filter(student -> studentIdPassed.contains(student.getId()))
                .filter(student -> student.getGroup().equals(GROUP))
                .forEach(System.out::println);
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();

        Map<String, List<Integer>> facultyExam1Scores = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.mapping(
                                student -> examinations.stream()
                                        .filter(exam -> exam.getStudentId() == student.getId())
                                        .findFirst()
                                        .map(Examination::getExam1)
                                        .orElse(0),
                                Collectors.toList()
                        )
                ));

        String facultyWithMaxAverage = facultyExam1Scores.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream().mapToInt(Integer::intValue).average().orElse(0.0)
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No faculty found");

        System.out.println("Faculty with the highest average score for Exam 1: " + facultyWithMaxAverage);
    }

    public static void task21() {
        List<Student> students = Util.getStudents();
        Map<String, Long> groupCount = students.stream()
                .collect(Collectors.groupingBy(
                                Student::getGroup,
                                Collectors.counting()
                        )
                );
        System.out.println(groupCount);
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
        Map<String, Integer> facultyMinAges = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.collectingAndThen(
                                Collectors.minBy(Comparator.comparingInt(Student::getAge)),
                                student -> student.map(Student::getAge).orElse(0)
                        )
                ));
        System.out.println(facultyMinAges);
    }
}

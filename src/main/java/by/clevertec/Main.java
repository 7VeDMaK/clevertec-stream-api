package by.clevertec;

import by.clevertec.model.*;
import by.clevertec.util.Util;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
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
        task19();
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
                .count();
        Double sum = Double.valueOf(animals.stream()
                .filter(animal -> animal.getOrigin().equals("Indonesian"))
                .map(Animal::getAge)
                .reduce(Integer::sum).orElse(0));
        System.out.println("average = " + sum / count);
    }

    public static void task12() {
        List<Person> persons = Util.getPersons();
        persons.stream()
                .filter(person -> person.getGender().equals("Male"))
                .filter(person -> person.getDateOfBirth().plusYears(18).isBefore(LocalDate.now()))
                .filter(person -> person.getDateOfBirth().plusYears(27).isAfter(LocalDate.now()))
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    private static int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public static void task13() {

        final int MAX_EVACUATION_CAPACITY = 500;
        final int CHILD_AGE_THRESHOLD = 18;
        final int SENIOR_AGE_THRESHOLD = 65;

        List<House> houses = Util.getHouses();
        List<Person> peopleToEvacuate = houses.stream()
                .flatMap(house -> house.getPersonList().stream())
                .filter(person -> {
                    int age = calculateAge(person.getDateOfBirth());
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

                    int age1 = calculateAge(p1.getDateOfBirth());
                    int age2 = calculateAge(p2.getDateOfBirth());

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
        echelonCars.put("Turkmenistan", new ArrayList<>());
        echelonCars.put("Uzbekistan", new ArrayList<>());
        echelonCars.put("Kazakhstan", new ArrayList<>());
        echelonCars.put("Kyrgyzstan", new ArrayList<>());
        echelonCars.put("Russia", new ArrayList<>());
        echelonCars.put("Mongolia", new ArrayList<>());

        cars.stream()
                .filter(car -> car.getCarMake().equalsIgnoreCase("Jaguar") || car.getColor().equalsIgnoreCase("White"))
                .forEach(car -> echelonCars.get("Turkmenistan").add(car));

        List<Car> remainingCars = cars.stream()
                .filter(car -> !(car.getCarMake().equalsIgnoreCase("Jaguar") || car.getColor().equalsIgnoreCase("White")))
                .collect(Collectors.toList());

        remainingCars.stream()
                .filter(car -> car.getMass() < MASS_THRESHOLD_ECHELON_2 &&
                        List.of("BMW", "Lexus", "Chrysler", "Toyota").contains(car.getCarMake()))
                .forEach(car -> echelonCars.get("Uzbekistan").add(car));

        remainingCars = remainingCars.stream()
                .filter(car -> !(car.getMass() < MASS_THRESHOLD_ECHELON_2 &&
                        List.of("BMW", "Lexus", "Chrysler", "Toyota").contains(car.getCarMake())))
                .collect(Collectors.toList());

        remainingCars.stream()
                .filter(car -> (car.getColor().equalsIgnoreCase("Black") && car.getMass() > MASS_THRESHOLD_ECHELON_3) ||
                        List.of("GMC", "Dodge").contains(car.getCarMake()))
                .forEach(car -> echelonCars.get("Kazakhstan").add(car));

        remainingCars = remainingCars.stream()
                .filter(car -> !((car.getColor().equalsIgnoreCase("Black") && car.getMass() > MASS_THRESHOLD_ECHELON_3) ||
                        List.of("GMC", "Dodge").contains(car.getCarMake())))
                .collect(Collectors.toList());

        remainingCars.stream()
                .filter(car -> car.getReleaseYear() < 1982 || List.of("Civic", "Cherokee").contains(car.getCarModel()))
                .forEach(car -> echelonCars.get("Kyrgyzstan").add(car));

        remainingCars = remainingCars.stream()
                .filter(car -> !(car.getReleaseYear() < 1982 || List.of("Civic", "Cherokee").contains(car.getCarModel())))
                .collect(Collectors.toList());

        remainingCars.stream()
                .filter(car -> (!List.of("Yellow", "Red", "Green", "Blue").contains(car.getColor())) || car.getPrice() > PRICE_THRESHOLD_ECHELON_5)
                .forEach(car -> echelonCars.get("Russia").add(car));

        remainingCars = remainingCars.stream()
                .filter(car -> !(car.getColor().equalsIgnoreCase("Yellow") || car.getColor().equalsIgnoreCase("Red") ||
                        car.getColor().equalsIgnoreCase("Green") || car.getColor().equalsIgnoreCase("Blue")) &&
                        car.getPrice() <= PRICE_THRESHOLD_ECHELON_5)
                .collect(Collectors.toList());

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

    private static double calculateTotalCost(Flower flower) {
        final double WATER_COST_PER_CUBIC_METER = 1.39;
        final int DAYS_IN_YEAR = 365;
        final int YEARS = 5;
        double waterConsumptionPerYear = flower.getWaterConsumptionPerDay() * DAYS_IN_YEAR;
        double waterCostForFiveYears = (waterConsumptionPerYear * YEARS / 1000) * WATER_COST_PER_CUBIC_METER;
        return flower.getPrice() + waterCostForFiveYears;
    }

    public static void task15() {

        List<Flower> flowers = Util.getFlowers();
        List<Flower> sortedFlowers = flowers.stream()
                .sorted(Comparator
                        .comparing(Flower::getOrigin).reversed()
                        .thenComparing(Flower::getPrice)
                        .thenComparing(Comparator.comparing(Flower::getWaterConsumptionPerDay).reversed())
                )
                .toList();

        List<Flower> selectedFlowers = sortedFlowers.stream()
                .filter(flower -> flower.getCommonName().compareToIgnoreCase("C") >= 0 &&
                        flower.getCommonName().compareToIgnoreCase("S") <= 0)
                .filter(flower -> flower.isShadePreferred() &&
                        flower.getFlowerVaseMaterial().stream().anyMatch(
                                material -> List.of("Glass", "Aluminum", "Steel").contains(material)))
                .toList();

        double totalCost = selectedFlowers.stream()
                .mapToDouble(Main::calculateTotalCost)
                .sum();

        System.out.println("Total maintenance cost: " + String.format("%.2f", totalCost) + " $");
    }

    public static void task16() {
        List<Student> students = Util.getStudents();
        students.stream()
                .filter(student -> student.getAge() < 18)
                .sorted(Comparator.comparing(Student::getSurname)
                        .thenComparingInt(Student::getAge))
                .forEach(System.out::println);
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

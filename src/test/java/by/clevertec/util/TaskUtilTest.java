package by.clevertec.util;

import by.clevertec.model.Animal;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskUtilTest {

    @Test
    void testTask1() {
        List<Animal> animals = Util.getAnimals();
        List<Animal> result = TaskUtil.task1(animals);

        assertNotNull(result);
        assertEquals(7, result.size());

        assertTrue(result.get(0).getAge() <= result.get(1).getAge());
        assertTrue(result.get(1).getAge() <= result.get(2).getAge());
        assertTrue(result.get(2).getAge() <= result.get(3).getAge());
        assertTrue(result.get(3).getAge() <= result.get(4).getAge());
        assertTrue(result.get(4).getAge() <= result.get(5).getAge());
        assertTrue(result.get(5).getAge() <= result.get(6).getAge());
    }

    @Test
    void testTask2() {
        List<Animal> animals = Util.getAnimals();
        List<Animal> result = TaskUtil.task2(animals);

        assertFalse(result.isEmpty(), "The result list should contain at least 1 Japanese animal.");

        assertTrue(result.stream().anyMatch(animal ->
                "Japanese".equals(animal.getOrigin()) && "Female".equals(animal.getGender())));

        assertTrue(result.stream().noneMatch(animal -> "dimsum12".equals(animal.getBread())),
                "Animals with non-Japanese origin should not be included in the result.");
    }

    @Test
    void testTask3() {
        List<Animal> animals = Util.getAnimals();
        List<String> result = TaskUtil.task3(animals);

        assertFalse(result.isEmpty(), "The result list should contain at least 1 animal.");

        assertTrue(result.contains("Afrikaans"), "The origin 'Afrikaans' should be included in the result.");
        assertTrue(result.contains("Arabic"), "The origin 'Arabic' should be included in the result.");
        assertTrue(result.contains("Assamese"), "The origin 'Assamese' should be included in the result.");
    }

    @Test
    void testTask4() {
        List<Animal> animals = Util.getAnimals();
        Long result = TaskUtil.task4(animals);

        assertTrue(result > 0, "The result should be greater than zero.");
    }

    @Test
    void testTask5() {
        List<Animal> animals = Util.getAnimals();
        boolean result = TaskUtil.task5(animals);
        assertTrue(result, "There should be at least one Hungarian animal between 20 and 30 years old.");
    }

    @Test
    void testTask6() {
        List<Animal> animals = Util.getAnimals();
        boolean result = TaskUtil.task6(animals);
        assertTrue(result, "There should be at least one animal with an unusual gender.");
    }

    @Test
    void testTask7() {
        List<Animal> animals = Util.getAnimals();
        boolean result = TaskUtil.task7(animals);
        assertTrue(result, "There should be no animals from Oceania.");
    }

    @Test
    void testTask8() {
        List<Animal> animals = Util.getAnimals();
        Integer result = TaskUtil.task8(animals);
        assertTrue(result > 0, "Age must be greater than zero.");
    }

    @Test
    void testTask9() {
        List<Animal> animals = Util.getAnimals();
        Integer result = TaskUtil.task9(animals);
        assertTrue(result > 0, "Length must be greater than zero.");
    }

    @Test
    void testTask10() {
        List<Animal> animals = Util.getAnimals();
        Integer result = TaskUtil.task10(animals);
        assertTrue(result > 0, "Sum must be greater than zero.");
    }

    @Test
    void testTask11() {
        List<Animal> animals = Util.getAnimals();
        Double result = TaskUtil.task11(animals);
        assertTrue(result > 0, "Sum must be greater than zero.");
    }
}

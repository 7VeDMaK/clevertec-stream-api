package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Student;
import by.clevertec.util.TaskUtil;
import by.clevertec.util.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

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
        // Similar setup and verification for task2
    }
}

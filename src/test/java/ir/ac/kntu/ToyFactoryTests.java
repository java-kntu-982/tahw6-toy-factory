package ir.ac.kntu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToyFactoryTests {
    private Toy toy;
    private Car car;

    @BeforeEach
    public void prepare() {
        toy = new Toy(20000, ToySize.SMALL);
        car = new Car(20000, ToySize.SMALL);
    }

    @Test
    public void testCarGetPrice() {
        assertEquals(40000, car.getPrice(), 1e-15);
        car.size = ToySize.MEDIUM;
        assertEquals(50000, car.getPrice(), 1e-15);
        car.size = ToySize.LARGE;
        assertEquals(60000, car.getPrice(), 1e-15);
        System.err.println("Get Price in Car => OK");
    }

    @Test
    public void testCarGetPriceWithDiscount() {
        double price = car.getPrice(20);
        assertEquals(20000, car.getBasePrice(), 1e-15);
        assertEquals(32000, price, 1e-15);
        System.err.println("Get Price with discount in Car => OK");
    }

    @Test
    public void testToyGetPrice() {
        assertEquals(20000, toy.getPrice(), 1e-15);
        toy.size = ToySize.MEDIUM;
        assertEquals(30000, toy.getPrice(), 1e-15);
        toy.size = ToySize.LARGE;
        assertEquals(40000, toy.getPrice(), 1e-15);
        System.err.println("Get Price in Toy => OK");
    }

    @Test
    public void testNumberOfToyConstructors() {
        Constructor<?>[] constructors = Toy.class.getConstructors();
        assertEquals(1, constructors.length);
        System.err.println("Toy Constructors => OK");
    }

    @Test
    public void testNumberOfCarConstructors() {
        Constructor<?>[] constructors = Car.class.getConstructors();
        assertEquals(1, constructors.length);
        System.err.println("Car Constructors => OK");
    }

    @Test
    public void testNumberOfToyMethods() {
        Method[] methods = Toy.class.getDeclaredMethods();
        int i = countDeclaredMethods(methods);
        assertEquals(3, i);
        System.err.println("Toy Methods => OK");
    }

    private int countDeclaredMethods(Method[] methods) {
        int i = 0;
        for (Method m : methods) {
            if (!m.getName().startsWith("$SWITCH_TABLE$")) {
                i++;
            }
        }
        return i;
    }

    @Test
    public void testNumberOfToyFields() {
        Field[] fields = Toy.class.getDeclaredFields();
        int i = countDeclaredFields(fields);
        assertEquals(2, i);
        System.err.println("Toy Fields => OK");
    }

    private int countDeclaredFields(Field[] fields) {
        int i = 0;
        for (Field f : fields) {
            if (!f.getName().startsWith("$SWITCH_TABLE$")) {
                i++;
            }
        }
        return i;
    }

    @Test
    public void testNumberOfCarFields() {
        Field[] fields = Car.class.getDeclaredFields();
        int i = countDeclaredFields(fields);
        assertEquals(0, i);
        System.err.println("Car Fields => OK");
    }

    @Test
    public void testNumberOfCarMethods() {
        Method[] methods = Car.class.getDeclaredMethods();
        int i = countDeclaredMethods(methods);
        assertEquals(1, i);
        System.err.println("Car Methods => OK");
    }

    @Test
    public void testFieldsModifier() {
        Field[] fields = Toy.class.getDeclaredFields();
        assertTrue(Modifier.isPrivate(fields[0].getModifiers()));
        assertTrue(Modifier.isProtected(fields[1].getModifiers()));
        System.err.println("Field Access Modifiers in Toy => OK");
    }

    @Test
    public void testToyMethodsModifier() {
        Method[] methods = Toy.class.getDeclaredMethods();
        for (Method m : methods) {
            if (!m.getName().startsWith("$SWITCH_TABLE$")) {
                assertTrue(Modifier.isPublic(m.getModifiers()));
            }
        }
        System.err.println("Method Access Modifiers in Toy => OK");
    }

    @Test
    public void testCarMethodsModifier() {
        Method[] methods = Car.class.getDeclaredMethods();
        for (Method method : methods) {
            if (!method.getName().startsWith("$SWITCH_TABLE$")) {
                assertTrue(Modifier.isPublic(method.getModifiers()));
            }
        }
        System.err.println("Method Access Modifiers in Car => OK");
    }
}

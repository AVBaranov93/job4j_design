package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void whenZeroVerticesThenSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEmpty()
                .isNotBlank()
                .contains("Sph");
    }

    @Test
    void whenOneVertexTheUnknownObject() {
        Box box = new Box(1, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .isNotEmpty()
                .isNotBlank()
                .contains("Unknown");
    }

    @Test
    void whenFourVertices() {
        Box box = new Box(4, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(4)
                .isLessThan(5)
                .isGreaterThan(1)
                .isPositive()
                .isEven();
    }

    @Test
    void whenEightVertices() {
        Box box = new Box(8, 10);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(8)
                .isLessThan(10)
                .isGreaterThan(1)
                .isPositive()
                .isEven();
    }

    @Test
    void whenBoxIsExist() {
        Box box = new Box(8, 10);
        boolean result = box.isExist();
        assertThat(result).isTrue()
                .isNotNull();
    }

    @Test
    void whenBoxDoesNotExist() {
        Box box = new Box(3, 10);
        boolean result = box.isExist();
        assertThat(result).isFalse()
                .isNotNull();
    }

    @Test
    void whenTetrahedronSquare() {
        Box box = new Box(4, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(173.205d, withPrecision(0.005d))
                .isCloseTo(173.205d, withPrecision(0.01d))
                .isCloseTo(173.205d, Percentage.withPercentage(1.0d))
                .isGreaterThan(171.205d)
                .isLessThan(175.205d);
    }

    @Test
    void whenCubeSquare() {
        Box box = new Box(8, 12);
        double result = box.getArea();
        assertThat(result).isEqualTo(864d, withPrecision(0.00001d))
                .isCloseTo(864d, withPrecision(0.01d))
                .isCloseTo(864d, Percentage.withPercentage(1.0d))
                .isGreaterThan(860)
                .isLessThan(865d);
    }

}
package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    public void whenConvertToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "third", "fourth", "fifth");
        assertThat(list).hasSize(5)
                .contains("fifth")
                .containsAnyOf("twelve", "third", "twenty")
                .containsExactly("first", "second", "third", "fourth", "fifth")
                .startsWith("first")
                .endsWith("fifth")
                .doesNotContain("zero");
    }

    @Test
    public void whenConvertToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> list = simpleConvert.toSet("first", "second", "third", "fourth", "fifth");
        assertThat(list).hasSize(5)
                .contains("fifth")
                .containsAnyOf("twelve", "third", "twenty")
                .doesNotContain("zero");
    }

    @Test
    public void whenConvertToMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "third", "fourth", "fifth");
        assertThat(map).containsKeys("first", "second", "third", "fourth", "fifth")
                .containsValues(0, 1, 2, 3, 4)
                .doesNotContainKeys("one", "two", "three")
                .doesNotContainValue(10);
    }
}
package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {
    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterThirdElement() {
        List<Integer> in = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListUtils.addAfter(in, 3, 100);
        assertThat(in).containsSequence(1, 2, 3, 4, 100, 5);
    }

    @Test
    void whenAddBeforeSecondElement() {
        List<Integer> in = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListUtils.addBefore(in, 2, 100);
        assertThat(in).containsSequence(1, 2, 100, 3, 4, 5);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.removeIf(input, e -> e < 2);
        assertThat(input).hasSize(1).containsSequence(3);
    }

    @Test
    void whenReplaceIf() {
        ListUtils.replaceIf(input, e -> e < 2, 100);
        assertThat(input).containsSequence(100, 3);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> in = new ArrayList<>(Arrays.asList(1, 2, 3, 10, 20, 100));
        ListUtils.removeAll(in, List.of(1, 3, 10));
        assertThat(in).containsSequence(2, 20, 100);
    }
}
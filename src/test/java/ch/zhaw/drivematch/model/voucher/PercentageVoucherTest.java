package ch.zhaw.drivematch.model.voucher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.zhaw.drivematch.model.Lesson;
import ch.zhaw.drivematch.model.LessonType;

// XXX Aufgabe 6b)
public class PercentageVoucherTest {

    @Test
    public void testVoucher_withoutLessons() {
        var voucher = new PercentageVoucher(7);
        assertEquals(0, voucher.getDiscount(new ArrayList<Lesson>()), 0.01);
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 5, 20, 49, 50 })
    public void testVoucher_singleLesson_multipleValues(int discount) {
        var voucher = new PercentageVoucher(discount);
        var lesson = new Lesson("desc1", "detail desc", LessonType.FUNDAMENTALS, 50.0);
        assertEquals(50*discount/100.0, voucher.getDiscount(Arrays.asList(lesson)), 0.01);
    }

    @Test
    public void testVoucher_withLessons() {
        var voucher = new PercentageVoucher(42);
        var lesson1 = new Lesson("desc1", "detail desc", LessonType.FUNDAMENTALS, 77.0);
        var lesson2 = new Lesson("desc2", "detail desc", LessonType.FUNDAMENTALS, 42.0);

        assertEquals(49.98, voucher.getDiscount(Arrays.asList(lesson1, lesson2)), 0.01);
    }

    // XXX Aufgabe 7c)
    @Test
    public void testVoucher_withLessons_Mock() {
        var voucher = new PercentageVoucher(42);

        var lesson1 = mock(Lesson.class);
        var lesson2 = mock(Lesson.class);
        when(lesson1.getPrice()).thenReturn(77.0);
        when(lesson2.getPrice()).thenReturn(42.0);

        assertEquals(49.98, voucher.getDiscount(Arrays.asList(lesson1, lesson2)), 0.01);
    }

    // XXX Aufgabe 7b)
    @Test
    public void testVoucher_belowOrEqualZero() {
        var exception1 = assertThrows(RuntimeException.class, () -> {
            new PercentageVoucher(0);
        });
        assertEquals(PercentageVoucher.errorMessageGreaterZero, exception1.getMessage());
        var exception2 = assertThrows(RuntimeException.class, () -> {
            new PercentageVoucher(-5);
        });
        assertEquals(PercentageVoucher.errorMessageGreaterZero, exception2.getMessage());
    }

    // XXX Aufgabe 7b)
    @Test
    public void testVoucher_greater50() {
        var exception1 = assertThrows(RuntimeException.class, () -> {
            new PercentageVoucher(51);
        });
        assertEquals(PercentageVoucher.errorMessage50, exception1.getMessage());

        var exception2 = assertThrows(RuntimeException.class, () -> {
            new PercentageVoucher(120);
        });
        assertEquals(PercentageVoucher.errorMessage50, exception2.getMessage());
    }

}

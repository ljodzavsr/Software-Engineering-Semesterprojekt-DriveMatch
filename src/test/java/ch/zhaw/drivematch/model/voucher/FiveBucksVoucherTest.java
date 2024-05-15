package ch.zhaw.drivematch.model.voucher;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ch.zhaw.drivematch.model.Lesson;
import ch.zhaw.drivematch.model.LessonType;

// Aufgabe 6a)
public class FiveBucksVoucherTest {

    @Test
    public void testEmpty() {
        var voucher = new FiveBucksVoucher();
        assertEquals(0, voucher.getDiscount(new ArrayList<Lesson>()), 0.01);
    }

    @Test
    public void testTwo() {
        var lessons = new ArrayList<Lesson>();
        var voucher = new FiveBucksVoucher();
        lessons.add(new Lesson("desc", "detail desc", LessonType.FUNDAMENTALS, 2.0));
        assertEquals(0, voucher.getDiscount(new ArrayList<Lesson>()), 0.01);
    }

    @Test
    public void testTen() {
        var voucher = new FiveBucksVoucher();
        var lessons = new ArrayList<Lesson>();
        lessons.add(new Lesson("description", "detail desc", LessonType.FUNDAMENTALS, 10.0));
        assertEquals(5, voucher.getDiscount(lessons), 0.01);
    }

}

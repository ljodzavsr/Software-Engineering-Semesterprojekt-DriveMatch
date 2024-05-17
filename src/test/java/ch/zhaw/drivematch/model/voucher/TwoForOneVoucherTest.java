package ch.zhaw.drivematch.model.voucher;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import ch.zhaw.drivematch.model.Lesson;
import ch.zhaw.drivematch.model.LessonType;

public class TwoForOneVoucherTest {

    @Test
    public void testOtherLesson() {
        var lesson1 = new Lesson("desc1", "detail desc", LessonType.FUNDAMENTALS, 77.0);
        var lesson2 = new Lesson("desc2", "detail desc", LessonType.URBAN, 77.0);

        var voucher = new TwoForOneVoucher(LessonType.FUNDAMENTALS);
        var lessons = new ArrayList<Lesson>();
        lessons.add(lesson1);
        lessons.add(lesson2);

        assertEquals(0, voucher.getDiscount(lessons), 0.01);
    }

    @Test
    public void testSameLesson() {
        var lesson1 = new Lesson("desc1", "detail desc", LessonType.HIGHWAY, 77.0);
        var lesson2 = new Lesson("desc2", "detail desc", LessonType.HIGHWAY, 33.0);

        var voucher = new TwoForOneVoucher(LessonType.HIGHWAY);
        var lessons = Arrays.asList(lesson1, lesson2);

        assertEquals(55, voucher.getDiscount(lessons), 0.01);
    }

    @Test
    public void testThreeLessons_sameType() {
        var lesson1 = new Lesson("desc1", "detail desc", LessonType.PARKING, 77.0);
        var lesson2 = new Lesson("desc2", "detail desc", LessonType.PARKING, 33.0);
        var lesson3 = new Lesson("desc3", "detail desc", LessonType.PARKING, 99.0);

        var voucher = new TwoForOneVoucher(LessonType.PARKING);
        var lessons = Arrays.asList(lesson1, lesson2, lesson3);

        assertEquals(104.5, voucher.getDiscount(lessons), 0.01);
    }

    @Test
    public void testThreeLessons_mixedType() {
        var lesson1 = new Lesson("desc1", "detail desc", LessonType.PARKING, 77.0);
        var lesson2 = new Lesson("desc2", "detail desc", LessonType.PARKING, 33.0);
        var lesson3 = new Lesson("desc3", "detail desc", LessonType.HIGHWAY, 99.0);

        var voucher = new TwoForOneVoucher(LessonType.PARKING);
        var lessons = Arrays.asList(lesson1, lesson2, lesson3);

        assertEquals(55.0, voucher.getDiscount(lessons), 0.01);
    }

    @ParameterizedTest
    @CsvSource({ "0,0", "1,0", "2,77", "3,115.5", "4,154" })
    public void testMultipleLessons(ArgumentsAccessor argumentsAccessor) {
        var voucher = new TwoForOneVoucher(LessonType.HIGHWAY);
        var lesson = new Lesson("desc", "detail desc", LessonType.HIGHWAY, 77.0);
        var lessons = new ArrayList<Lesson>();
        for (var i = 0; i < argumentsAccessor.getInteger(0); i++) {
            lessons.add(lesson);
        }
        var price = argumentsAccessor.getDouble(1);
        assertEquals(price, voucher.getDiscount(lessons), 0.01);
    }

}

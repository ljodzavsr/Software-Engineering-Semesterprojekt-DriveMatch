package ch.zhaw.drivematch.model.voucher;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.zhaw.drivematch.model.Lesson;
import ch.zhaw.drivematch.model.LessonType;

public class PercentageVoucherTest {

    @Test
    public void testVoucher_withoutLessons() {
        var voucher = new PercentageVoucher(7);
        assertEquals(0, voucher.getDiscount(Arrays.asList()), 0.01);
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 5, 20, 49, 50 })
    public void testVoucher_singleLesson_multipleValues(int discount) {
        var voucher = new PercentageVoucher(discount);
        var lesson = new Lesson("Autobahn", null, LessonType.PRACTICAL, 50.0);
        assertEquals(50 * discount / 100.0, voucher.getDiscount(Arrays.asList(lesson)), 0.01);
    }
}

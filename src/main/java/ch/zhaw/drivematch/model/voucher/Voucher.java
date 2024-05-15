package ch.zhaw.drivematch.model.voucher;

import java.util.List;

import ch.zhaw.drivematch.model.Lesson;

public interface Voucher {
    double getDiscount(List<Lesson> lessons);
}

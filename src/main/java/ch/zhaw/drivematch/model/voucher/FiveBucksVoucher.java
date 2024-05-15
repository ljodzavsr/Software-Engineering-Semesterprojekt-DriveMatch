package ch.zhaw.drivematch.model.voucher;

import java.util.List;

import ch.zhaw.drivematch.model.Lesson;

public class FiveBucksVoucher implements Voucher {

    @Override
    public double getDiscount(List<Lesson> lessons) {
        var sum = lessons.stream().mapToDouble(p -> p.getPrice()).sum();
        if (sum >= 10) {
            return 5;
        }
        return 0;
    }

}

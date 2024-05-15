package ch.zhaw.drivematch.model.voucher;

import java.util.List;

import ch.zhaw.drivematch.model.Lesson;

public class FiveBucksVoucher implements Voucher {
    
    @Override
    public double getDiscount(List<Lesson> lessons) {
        double total = lessons.stream().mapToDouble(Lesson::getPrice).sum();
        return total >= 10 ? 5.0 : 0;
    }
}

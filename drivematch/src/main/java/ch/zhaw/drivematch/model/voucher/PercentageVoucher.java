package ch.zhaw.drivematch.model.voucher;

import java.util.List;

import ch.zhaw.drivematch.model.Lesson;

public class PercentageVoucher implements Voucher {
    private final int percentage;

    public PercentageVoucher(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public double getDiscount(List<Lesson> lessons) {
        double total = lessons.stream().mapToDouble(Lesson::getPrice).sum();
        return (total * percentage) / 100.0;
    }
}

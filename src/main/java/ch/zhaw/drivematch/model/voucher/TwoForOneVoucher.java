package ch.zhaw.drivematch.model.voucher;

import java.util.List;

import ch.zhaw.drivematch.model.Lesson;
import ch.zhaw.drivematch.model.LessonType;

public class TwoForOneVoucher implements Voucher {

    LessonType lessonType;

    public TwoForOneVoucher(LessonType lessonType) {
        this.lessonType = lessonType;
    }

    @Override
    public double getDiscount(List<Lesson> lessons) {
        var filteredLessons = lessons.stream().filter(p -> this.lessonType.equals(p.getLessonType())).toList();
        var sum = filteredLessons.stream().mapToDouble(p -> p.getPrice()).sum();
        if (filteredLessons.size() <=1) {
            return 0;
        }
        return sum / 2;
    }

}

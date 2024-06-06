package ch.zhaw.drivematch.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zhaw.drivematch.model.Instructor;
import ch.zhaw.drivematch.model.Lesson;
import ch.zhaw.drivematch.model.LessonState;
import ch.zhaw.drivematch.repository.InstructorRepository;
import ch.zhaw.drivematch.repository.LessonRepository;

// Die LessonService-Klasse ermöglicht die Zuweisung und den Abschluss von Lektionen, indem sie den Zustand der Lektionen 
// aktualisiert und die entsprechenden Instruktoren anhand ihrer E-Mail-Adressen identifiziert. Die Methoden verwenden 
// Optional, um anzuzeigen, ob die Operation erfolgreich war oder nicht.

@Service
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    InstructorRepository instructorRepository;

    public Optional<Lesson> assignLesson(String lessonId, String instructorEmail) {
        Optional<Lesson> lessonToAssign = lessonRepository.findById(lessonId);
        if (lessonToAssign.isPresent()) {
            Lesson lesson = lessonToAssign.get();
            if (lesson.getLessonState() == LessonState.NEW) {
                Instructor f = instructorRepository.findFirstByEmail(instructorEmail);
                if (f != null) {
                    lesson.setLessonState(LessonState.ASSIGNED);
                    lesson.setInstructorId(f.getId());
                    lessonRepository.save(lesson);
                    return Optional.of(lesson);
                }
            }
        }
        return Optional.empty();
    }

    public Optional<Lesson> completeLesson(String lessonId, String instructorEmail) {
        Optional<Lesson> lessonToAssign = lessonRepository.findById(lessonId);
        if (lessonToAssign.isPresent()) {
            Lesson lesson = lessonToAssign.get();
            if (lesson.getLessonState() == LessonState.ASSIGNED) {
                Instructor f = instructorRepository.findFirstByEmail(instructorEmail);
                if (f != null) {
                    if (lesson.getInstructorId().equals(f.getId())) {
                        lesson.setLessonState(LessonState.DONE);
                        lessonRepository.save(lesson);
                        return Optional.of(lesson);
                    }
                }
            }
        }
        return Optional.empty();
    }

}

package com.ifpb.caelestiabackend.dto;

import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ModuleDto {
    private String name;
    private Integer qtyLessons;
    private Set<TheoricLesson> theoricLessons;

    public void addTheoricLesson(TheoricLesson theoricLesson) {
        List<TheoricLesson> theoricLessonsList;

        if (getTheoricLessons() == null) {
            theoricLessonsList = new ArrayList<>();
        } else {
            theoricLessonsList = new ArrayList<>(getTheoricLessons());
        }

        this.qtyLessons += 1;
        theoricLessonsList.add(theoricLesson);
        setTheoricLessons(new HashSet<>(theoricLessonsList));
    }

}

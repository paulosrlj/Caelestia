package com.ifpb.caelestiabackend.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class ModuleDto {

    @NotBlank(message = "O nome do módulo não pode estar em branco!")
    private String name;
    private Integer qtyLessons = 0;

    @Valid
    private Set<TheoricLessonDto> theoricLessons;

    public void addTheoricLesson(TheoricLessonDto theoricLessonDto) {
        List<TheoricLessonDto> theoricLessonsList;

        if (getTheoricLessons() == null) {
            theoricLessonsList = new ArrayList<>();
        } else {
            theoricLessonsList = new ArrayList<>(getTheoricLessons());
        }

        this.qtyLessons += 1;
        theoricLessonsList.add(theoricLessonDto);
        setTheoricLessons(new HashSet<>(theoricLessonsList));
    }

}

package com.ifpb.caelestiabackend;

import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.domain.entities.TheoricLesson;
import com.ifpb.caelestiabackend.infra.db.mysql.moduleRepository.ModuleRepository;
import com.ifpb.caelestiabackend.infra.db.mysql.moduleRepository.TheoricLessonRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class Teste {

    private final ModuleRepository moduleRepository;
    private final TheoricLessonRepository theoricLessonRepository;

    public Teste(ModuleRepository moduleRepository, TheoricLessonRepository theoricLessonRepository) {
        this.moduleRepository = moduleRepository;
        this.theoricLessonRepository = theoricLessonRepository;
    }

    private TheoricLesson makeTheoricLesson() {
        return TheoricLesson.builder()
                .lessonName("Introdução a astronomia")
                .description("Conteúdo da lição")
                .xpEarned(100L)
                .build();
    }

    private Module makeModule() {
        return Module.builder()
                .name("Astronomia antiga")
                .qtyLessons(0)
                .build();
    }

//    @PostConstruct
    public void testar() {
        TheoricLesson theoricLesson = new TheoricLesson();
        theoricLesson.setLessonName("Teste1");
        theoricLesson.setDescription("ahfiuafiuaf");
        theoricLesson.setXpEarned(100L);

        Module module = new Module();
        module.setName("Modulo teste");
        module.setQtyLessons(0);

        module.addTheoricLesson(theoricLesson);
        theoricLesson.setModule(module);

        moduleRepository.save(module);

        List<TheoricLesson> lessons = theoricLessonRepository.findAll();
        System.out.println(lessons);
    }
}

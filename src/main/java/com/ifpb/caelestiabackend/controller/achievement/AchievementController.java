package com.ifpb.caelestiabackend.controller.achievement;

import com.ifpb.caelestiabackend.domain.entities.Achievement.Achievement;
import com.ifpb.caelestiabackend.domain.entities.Module;
import com.ifpb.caelestiabackend.services.achievement.AchievementService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.AbstractMap;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/achievement")
@Log4j2
public class AchievementController implements IAchievementController {

    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<AbstractMap<String, Object>> add(@Valid @RequestBody Achievement achievement) {
        log.info(achievement);
        Achievement ac = achievementService.add(achievement);
        return ResponseEntity.ok(makeHttpResponseObject(ac));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AbstractMap<String, Object>> getById(@PathVariable("id") Long id) {
        Achievement ac = achievementService.getById(id);

        return ResponseEntity.ok(makeHttpResponseObject(ac));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<AbstractMap<String, Object>> update(@PathVariable Long id
            , @RequestBody Achievement achievement) {
        log.info("Achievement request");
        log.info(achievement);
        Achievement acUpdated = achievementService.update(id, achievement);

        return ResponseEntity.ok(makeHttpResponseObject(acUpdated));
    }

    private AbstractMap<String, Object> makeHttpResponseObject(Achievement ac) {
        AbstractMap<String, Object> obj = new LinkedHashMap<>();
        obj.put("id", ac.getId());
        obj.put("achievementName", ac.getAchievementName());
        obj.put("description", ac.getDescription());
        obj.put("baseBonusPercentage", ac.getBaseBonusPercentage());
        obj.put("bonusType", ac.getBonusType());
        obj.put("urlImage", ac.getUrlImage());

        AbstractMap<String, Object> moduleObj = new LinkedHashMap<>();

        moduleObj.put("id", ac.getModule().getId());
        moduleObj.put("name", ac.getModule().getName());

        obj.put("module", moduleObj);

        return obj;
    }
}

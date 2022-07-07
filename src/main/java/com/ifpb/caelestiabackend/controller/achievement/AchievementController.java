package com.ifpb.caelestiabackend.controller.achievement;

import com.ifpb.caelestiabackend.domain.entities.Achievement.Achievement;
import com.ifpb.caelestiabackend.services.achievement.AchievementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.AbstractMap;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/achievement")
public class AchievementController implements IAchievementController {

    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<AbstractMap<String, Object>> add(@Valid @RequestBody Achievement achievement) {
        System.out.println(achievement);
        Achievement ac = achievementService.add(achievement);
        return ResponseEntity.ok(makeHttpResponseObject(ac));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<AbstractMap<String, Object>> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<AbstractMap<String, Object>> update(Long id, Achievement achievement) {
        return null;
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

package com.olebokolo.distance.reading;

import com.olebokolo.distance.core.service.RuleService;
import com.olebokolo.distance.model.entity.Entry;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Log4j
@Service
public class CommandLineController {

    private final CommandLineHelper helper;
    private final RuleService ruleService;

    @Autowired
    public CommandLineController(CommandLineHelper helper, RuleService ruleService) {
        this.helper = helper;
        this.ruleService = ruleService;
    }

    public void resolveParams(String[] args) throws IOException {
        Map<String, List<String>> params = helper.getCommandLineParams(args);
        if (params.get("r") != null) {
            log.info("-------------------------------------");
            String studyFilePath = params.get("r").get(0);
            ruleService.learnRulesFromFile(studyFilePath);
            log.info("-------------------------------------");
        }
        if (params.get("c") != null) {
            log.info("-------------------------------------");
            String entriesFilePath = params.get("c").get(0);
            ruleService.checkEntriesFromFile(entriesFilePath);
            log.info("-------------------------------------");
        }
    }


}

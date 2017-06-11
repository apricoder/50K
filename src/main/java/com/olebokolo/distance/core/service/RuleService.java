package com.olebokolo.distance.core.service;

import com.olebokolo.distance.model.entity.Entry;
import com.olebokolo.distance.model.entity.Rule;
import com.olebokolo.distance.model.repository.RuleRepository;
import com.olebokolo.distance.reading.CSVParser;
import com.olebokolo.distance.reading.FileReader;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

@Log4j
@Service
public class RuleService {

    private final FileReader fileReader;
    private final CSVParser csvParser;
    private final RuleRepository ruleRepository;
    private final EntryComparator entryComparator;

    @Autowired
    public RuleService(
            FileReader fileReader,
            CSVParser csvParser,
            RuleRepository ruleRepository,
            EntryComparator entryComparator
    ) {
        this.fileReader = fileReader;
        this.csvParser = csvParser;
        this.ruleRepository = ruleRepository;
        this.entryComparator = entryComparator;
    }

    public void learnRulesFromFile(String studyFilePath) throws IOException {
        Stream<String> ruleLinesStream = fileReader.getLinesStream(studyFilePath);
        List<Rule> rules = csvParser.parseRules(ruleLinesStream);
        save(rules);
    }

    @Transactional(readOnly = true)
    public void checkEntriesFromFile(String checkFilePath) throws IOException {
        Stream<String> entryLinesStream = fileReader.getLinesStream(checkFilePath);
        List<Entry> entries = csvParser.parseEntries(entryLinesStream);
        Iterable<Rule> rules = ruleRepository.findAll();
        log.info("Loaded rules!");
        for (Entry entry : entries) {
            log.info(entry.getAge() + " -> " + get50KResultForEntryFromRules(entry, rules));
        }
    }

    private void save(Rule rule) {
        ruleRepository.save(rule);
        log.info("Saving rule [" + rule.getEntry() + "] -> " + rule.getResult());
    }

    private void save(Collection<Rule> rules) {
        rules.forEach(this::save);
    }

    @Transactional(readOnly = true)
    private String get50KResultForEntryFromRules(Entry entry, Iterable<Rule> rules) {
        Map<Integer, List<Rule>> similarityMap = new TreeMap<>();
        for (Rule rule : rules) {
            int commonValues = entryComparator.countSimilarity(entry, rule.getEntry());
            if (commonValues > 0) {
                similarityMap.putIfAbsent(commonValues, new ArrayList<>());
                similarityMap.get(commonValues).add(rule);
            }
        }
        int quotient = get50KQuotient(similarityMap);
        String textResult = (quotient <= 0) ? "<=50K" : ">50K";
        return textResult + " quotient: " + quotient;
    }

    private int get50KQuotient(Map<Integer, List<Rule>> similarityMap) {
        int result = 0;
        int bestKey = getBestKey(similarityMap);
        for (Rule rule : similarityMap.get(bestKey))
            result += (rule.getResult().equals("<=50K")) ? -1 : 1;
        return result;
    }

    private int getBestKey(Map<Integer, List<Rule>> similarityMap) {
        int bestKey = -1;
        for (Integer key : similarityMap.keySet()) {
            if (key > bestKey) {
                bestKey = key;
            }
        }
        return bestKey;
    }

}

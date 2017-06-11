package com.olebokolo.distance.reading;

import com.olebokolo.distance.model.entity.Entry;
import com.olebokolo.distance.model.entity.Rule;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Log4j
@Service
public class CSVParserImpl implements CSVParser {

    private static final int AGE = 0;
    private static final int WORK_CLASS = 1;
    private static final int FINAL_WEIGHT = 2;
    private static final int EDUCATION = 3;
    private static final int EDUCATION_INDEX = 4;
    private static final int MARITAL_STATUS = 5;
    private static final int OCCUPATION = 6;
    private static final int RELATIONSHIP = 7;
    private static final int RACE = 8;
    private static final int SEX = 9;
    private static final int CAPITAL_GAIN = 10;
    private static final int CAPITAL_LOSS = 11;
    private static final int WEEK_WORK_HOURS = 12;
    private static final int COUNTRY = 13;

    @Override
    public List<Rule> parseRules(Stream<String> linesStream) {
        List<Rule> rules = new ArrayList<>();
        linesStream.forEach(line -> {
            Rule rule = parseRule(line);
            if (rule != null) rules.add(rule);
        });
        return rules;
    }

    @Override
    public Rule parseRule(String line) {
        String[] rowValues = line.split(";");
        String[] entryValues = Arrays.copyOfRange(rowValues, 0, rowValues.length - 1);
        String result = rowValues[rowValues.length - 1];
        Entry entry = parseEntry(entryValues);
        return entry == null ? null : Rule.builder()
                .entry(entry)
                .result(result)
                .build();
    }

    @Override
    public List<Entry> parseEntries(Stream<String> linesStream) {
        List<Entry> entries = new ArrayList<>();
        linesStream.forEach(line -> {
            Entry entry = parseEntry(line.split(";"));
            if (entry != null) entries.add(entry);
        });
        return entries;
    }

    @Override
    public Entry parseEntry(String[] values) {
        Entry entry = null;
        try { entry = getEntry(values); }
        catch (Exception e) { log.error("Couldn't parse entry from "
                + Arrays.toString(values)); }
        return entry;
    }

    private Entry getEntry(String[] values) {
        return Entry.builder()
                .age(Integer.valueOf(nullIfQuestionMark(values[AGE])))
                .workClass(nullIfQuestionMark(values[WORK_CLASS]))
                .finalWeight(Long.valueOf(nullIfQuestionMark(values[FINAL_WEIGHT])))
                .education(nullIfQuestionMark(values[EDUCATION]))
                .educationIndex(Integer.valueOf(nullIfQuestionMark(values[EDUCATION_INDEX])))
                .maritalStatus(nullIfQuestionMark(values[MARITAL_STATUS]))
                .occupation(nullIfQuestionMark(values[OCCUPATION]))
                .relationship(nullIfQuestionMark(values[RELATIONSHIP]))
                .race(nullIfQuestionMark(values[RACE]))
                .sex(nullIfQuestionMark(values[SEX]))
                .capitalGain(Long.valueOf(nullIfQuestionMark(values[CAPITAL_GAIN])))
                .capitalLoss(Long.valueOf(nullIfQuestionMark(values[CAPITAL_LOSS])))
                .weekWorkHours(Integer.valueOf(nullIfQuestionMark(values[WEEK_WORK_HOURS])))
                .country(nullIfQuestionMark(values[COUNTRY]))
                .build();
    }

    private String nullIfQuestionMark(String value) {
        return value.equals("?") ? null : value;
    }

}

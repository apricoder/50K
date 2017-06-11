package com.olebokolo.distance.reading;

import com.olebokolo.distance.model.entity.Entry;
import com.olebokolo.distance.model.entity.Rule;

import java.util.List;
import java.util.stream.Stream;

public interface CSVParser {
    List<Rule> parseRules(Stream<String> linesStream);
    Rule parseRule(String line);

    List<Entry> parseEntries(Stream<String> linesStream);

    Entry parseEntry(String[] values);
}

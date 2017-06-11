package com.olebokolo.distance.reading;

import com.olebokolo.distance.model.entity.Entry;
import com.olebokolo.distance.model.entity.Rule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CSVParserImplTest {

    private CSVParserImpl parser;

    private String line;
    private String headerLine;
    private Entry expectedEntry;

    @Before
    public void setUp() throws Exception {
        parser = new CSVParserImpl();
        initLine();
        initExcpectedEntry();
    }

    private void initLine() {
        line = "17;Private;309504;"
                + "10th;6;Never-married;"
                + "Sales;Unmarried;White;"
                + "Female;0;0;24;United-States;<=50K";
        headerLine = "age;w-class;fnlwgt;"
                + "education;edu-num;marital-status;"
                + "occupation;relationship;race;"
                + "sex;cap-gain;cap-loss;hrs/week;n-country;res";
    }

    private void initExcpectedEntry() {
        expectedEntry = Entry.builder()
                .age(17)
                .workClass("Private")
                .finalWeight(309504L)
                .education("10th")
                .educationIndex(6)
                .maritalStatus("Never-married")
                .occupation("Sales")
                .relationship("Unmarried")
                .race("White")
                .sex("Female")
                .capitalGain(0L)
                .capitalLoss(0L)
                .weekWorkHours(24)
                .country("United-States")
                .build();
    }

    @Test
    public void shouldParseRules() throws Exception {
        //given
        Stream<String> stream = new ArrayList<String>() {{ add(line);}}.stream();
        //when
        List<Rule> rules = parser.parseRules(stream);
        //then
        assertEquals(rules.get(0), Rule.builder()
                .entry(expectedEntry)
                .result("<=50K")
                .build());
    }

    @Test
    public void shouldParseRule() throws Exception {
        //given
        //when
        Rule rule = parser.parseRule(line);
        //then
        assertEquals(expectedEntry, rule.getEntry());
        assertEquals("<=50K", rule.getResult());
    }

    @Test
    public void shouldParseEntry() throws Exception {
        //given
        //when
        Entry entry = parser.parseEntry(line.split(";"));
        //then
        assertEquals(expectedEntry, entry);
    }

    @Test
    public void shouldReturnNullWhenCouldntParse() throws Exception {
        //given
        //when
        Entry entry = parser.parseEntry(headerLine.split(";"));
        //then
        assertNull(entry);
    }

}
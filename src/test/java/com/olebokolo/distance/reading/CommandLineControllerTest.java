package com.olebokolo.distance.reading;

import com.olebokolo.distance.core.service.RuleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CommandLineControllerTest {

    @Spy
    @InjectMocks
    private CommandLineController controller;

    @Mock
    private RuleService ruleService;

    @Test
    public void shouldCallLearnRulesFromFile() throws Exception {
        //given
        String[] params = "-r ~/path/to/file.csv".split("\\s");
        //when
        controller.resolveParams(params);
        //then
        verify(ruleService).learnRulesFromFile("~/path/to/file.csv");
    }

}
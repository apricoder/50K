package com.olebokolo.distance.core.service;

import com.olebokolo.distance.model.entity.Entry;
import org.springframework.stereotype.Service;

@Service
class EntryComparator {

    private static final int MIN_AGE = 17;
    private static final int MAX_AGE = 90;
    private static final int MIN_WEIGHT = 12285;
    private static final int MAX_WEIGHT = 1484705;
    private static final int MIN_EDU_INDEX = 1;
    private static final int MAX_EDU_INDEX = 16;
    private static final int MIN_CAP_GAIN = 0;
    private static final int MAX_CAP_GAIN = 99999;
    private static final int MIN_CAP_LOSS = 0;
    private static final int MAX_CAP_LOSS = 4356;
    private static final int MIN_WEEK_HOURS = 1;
    private static final int MAX_WEEK_HOURS = 99;

    int countSimilarity(Entry entry, Entry compared) {
        double s = 0;
        s += powerGradation(entry.getAge(), compared.getAge(), MIN_AGE, MAX_AGE);
        s += equal(entry.getWorkClass(), compared.getWorkClass());
        s += gradation(entry.getFinalWeight(), compared.getFinalWeight(), MIN_WEIGHT, MAX_WEIGHT);
        s += equal(entry.getEducation(), compared.getEducation());
        s += powerGradation(entry.getEducationIndex(), compared.getEducationIndex(), MIN_EDU_INDEX, MAX_EDU_INDEX);
        s += equal(entry.getMaritalStatus(), compared.getMaritalStatus());
        s += equal(entry.getOccupation(), compared.getOccupation());
        s += equal(entry.getRelationship(), compared.getRelationship());
        s += equal(entry.getRace(), compared.getRace());
        s += equal(entry.getSex(), compared.getSex());
        s += gradation(entry.getCapitalGain(), compared.getCapitalGain(), MIN_CAP_GAIN, MAX_CAP_GAIN);
        s += gradation(entry.getCapitalLoss(), compared.getCapitalLoss(), MIN_CAP_LOSS, MAX_CAP_LOSS);
        s += gradation(entry.getWeekWorkHours().longValue(), compared.getWeekWorkHours().longValue(), MIN_WEEK_HOURS, MAX_WEEK_HOURS);
        s += equal(entry.getCountry(), compared.getCountry());
        return Math.toIntExact(Math.round(s));
    }

    private double powerGradation(Integer value, Integer compared, int min, int max) {
        try {
            int range = max - min;
            return (range - Math.pow(Math.abs(value - compared), 3)) / range;
        } catch (Exception ignored) {}
        return 0;
    }

    private double gradation(Long value, Long compared, long min, long max) {
        try {
            long range = max - min;
            return (range - Math.abs(value - compared)) / range;
        } catch (Exception ignored) { }
        return 0;
    }

    private int equal(String value, String compared) {
        try { return value.equals(compared) ? 1 : 0; }
        catch (Exception ignored) {}
        return 0;
    }
}

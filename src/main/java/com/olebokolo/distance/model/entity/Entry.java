package com.olebokolo.distance.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer age;
    private String workClass;
    private Long finalWeight;
    private String education;
    private Integer educationIndex;
    private String maritalStatus;
    private String occupation;
    private String relationship;
    private String race;
    private String sex;
    private Long capitalGain;
    private Long capitalLoss;
    private Integer weekWorkHours;
    private String country;

    @Override
    public String toString() {
        return "{age:" + age + ","
                + "workClass:" + workClass + ","
                + "finalWeight:" + finalWeight + ","
                + "education:" + education + ","
                + "educationIndex:" + educationIndex + ","
                + "maritalStatus:" + maritalStatus + ","
                + "occupation:" + occupation + ","
                + "relationship:" + relationship + ","
                + "race:" + race + ","
                + "sex:" + sex + ","
                + "capitalGain:" + capitalGain + ","
                + "capitalLoss:" + capitalLoss + ","
                + "weekWorkHours:" + weekWorkHours + ","
                + "country:" + country + "}";
    }
}

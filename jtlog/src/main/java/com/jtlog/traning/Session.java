package com.jtlog.traning;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 *
 */
@Data
@Accessors(fluent = true)
public class Session {

    private String id;
    private String start;
    private String end;
    private String note;
    private List<Exercise> exercises;

    /**
     *
     */
    @Data
    @Accessors(fluent = true)
    public static class Exercise {
        private String id;
        private String note;
        private List<Set> sets;
    }

    /**
     *
     */
    @Data
    @Accessors(fluent = true)
    public static class Set {
        private int nr;
        private String note;
        private Double weight;
        private Integer reps;
        private Integer rir;


    }
}

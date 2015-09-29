package com.cg.jgen.cmd;

import com.cg.jgen.cmd.evaluators.ArgumentEvaluator;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 *
 */
class Argument {
    private String name;
    private String description;
    private String value;
    private boolean mandatory;

    private boolean present;
    private ArgumentEvaluator evaluator;

    public Argument(String name, String description, boolean mandatory, ArgumentEvaluator evaluator) {
        this(name, description, mandatory);
        Preconditions.checkNotNull(evaluator, "ArgumentEvaluator must be not null");

        this.evaluator = evaluator;
    }

    public Argument(String name, String description, boolean mandatory) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "Argument name is empty or Null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(description), "Argument description is empty or Null");

        setValues(name, description, mandatory);
    }

    private void setValues(String name, String description, boolean mandatory) {
        this.name = name;
        this.description = description;
        this.value = "";
        this.mandatory = mandatory;

        this.present = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public boolean haveParameter() {
        return evaluator != null;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public boolean evaluate(String arg) {
        return evaluator != null && evaluator.evaluate(arg);
    }
}

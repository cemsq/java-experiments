package com.cg.jgen.core;

import com.cgm.storm.utils.common.StringUtils;
import com.google.common.base.Preconditions;

/**
 * EnumValue contains one row of the entries of a value table
 */
public class EnumValue {

    private String id;
    private String code;
    private String displayText;
    private Integer sortSequence;
    private String resourceId;

    public EnumValue(String id, String code, String displayText, Integer sortSequence) {
        StringUtils.validateString(code, "Code in EnumValue must be not null or not empty");

        Preconditions.checkArgument(sortSequence > 0);
        this.id = id;
        this.code = code;
        this.displayText = displayText;
        this.sortSequence = sortSequence;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public Integer getSortSequence() {
        return sortSequence;
    }

    public void setSortSequence(Integer sortSequence) {
        this.sortSequence = sortSequence;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

}

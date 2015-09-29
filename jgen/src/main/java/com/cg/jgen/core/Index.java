package com.cg.jgen.core;

import com.cg.jgen.utils.KeyWordChecker;
import com.cg.jgen.utils.Utility;
import com.cgm.storm.utils.common.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A Table index
 */
public class Index {
    private String name;
    private String unique;
    private String primary;

    private List<String> columns = new ArrayList<>();

    public Index(String name, String primary, String unique) {
        StringUtils.validateString(name, "Index name must be not null or not empty");
        KeyWordChecker.checkKeyWord(name, "Index", "");

        this.name = name;
        this.primary = StringUtils.getValidString(primary, "false");
        if (this.primary.equals("true")) {
            this.unique = "true";
        } else {
            this.unique = StringUtils.getValidString(unique, "true");
        }
    }

    // <editor-fold desc = "--- Getter and Setter ---">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        StringUtils.validateString(name, "Index name must be not null or not empty");
        KeyWordChecker.checkKeyWord(name, "Index", "");
        this.name = name;
    }

    public boolean isUnique() {
        return unique.equals("true");
    }

    public void setUnique(String unique) {
        this.unique = StringUtils.getValidString(unique, "false");
    }

    public boolean isPrimary() {
        return primary.equals("true");
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }
    // </editor-fold>

    public boolean hasColumn(String name) {
        for (String col : columns) {
            if (col.equals(name)) {
                return true;
            }
        }

        return false;
    }

    public Index addColumn(String name) {
        KeyWordChecker.checkKeyWord(name, "Index", "");
        if (StringUtils.isValid(name)) {
            columns.add(Utility.camelCase(name));
        }

        return this;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns.clear();
        for (String col : columns) {
            addColumn(col);
        }
    }

    @Override
    public String toString() {
        return StringUtils.stringFormat(0, "@TableIndex(name = \"{}\", unique = {}, elementNames = {{}})", name, unique, columnsAsString());
    }

    public String columnsAsString() {
        int i = 0;
        StringBuilder sb = new StringBuilder("");
        for (String col : columns) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append("\"");
            sb.append(col);
            sb.append("\"");
            i++;
        }

        return sb.toString();
    }
}

package com.cg.jgen.core;

import com.cg.helix.persistence.metadata.annotation.CardinalityType;
import com.cg.jgen.core.exception.JGenException;
import com.cg.jgen.utils.KeyWordChecker;
import com.cg.jgen.utils.Utility;
import com.cgm.storm.utils.common.StringUtils;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class ForeignKey {

    private static List<ForeignKey> fkCreated = new ArrayList<>();
    private static String begin = "\t\t\t";
    private String childTable;
    private String parentTable;
    private String name;
    private CardinalityType cardinality;
    private List<Map<String, String>> columns;

    public ForeignKey() {
        fkCreated.add(this);
    }

    public static List<ForeignKey> getForeignKeyByFrom(String childTable) {
        return getForeignKeyByFrom(childTable, fkCreated);
    }

    public static List<ForeignKey> getForeignKeyByFrom(String childTable, List<ForeignKey> fkList) {
        List<ForeignKey> list = new ArrayList<>();
        for (ForeignKey fk : fkList) {
            if (fk.childTable.equals(childTable)) {
                list.add(fk);
            }
        }

        return list;
    }

    // unused method
//    public static List<ForeignKey> getForeignKeysCreated() {
//        return fkCreated;
//    }

    // unused method
//    public String getChildTable() {
//        return childTable;
//    }

    public void setChildTable(String childTable) {
        StringUtils.validateString(childTable, "'childTable' must be not null or not empty");
        this.childTable = childTable;
    }


    public String getParentTable() {
        return parentTable;
    }

    public void setParentTable(String parentTable) {
        StringUtils.validateString(parentTable, "'parentTable' must be not null or not empty");
        this.parentTable = parentTable;
    }

    public String getName() {
        return name;
    }

    /**
     * set ForeignKey's name
     *
     * @param name new ForeignKey name
     * @throws JGenException if name is empty or null
     */
    public void setName(String name) {
        StringUtils.validateString(name, "'name' must be not null or not empty");
        KeyWordChecker.checkKeyWord(name, "Foreign key", "");
        this.name = name;
    }

    // unused method
//    public CardinalityType getCardinality() {
//        return cardinality;
//    }

    public void setCardinality(String cardinality) {
        StringUtils.validateString(cardinality, "'cardinality' must be not null or not empty");
        String aux = cardinality.toUpperCase();
        switch (aux) {
            case "MANY_TO_ONE":
                this.cardinality = CardinalityType.MANY_TO_ONE;
                break;

            case "ONE_TO_MANY":
                this.cardinality = CardinalityType.ONE_TO_MANY;
                break;

            case "ONE_TO_ONE":
                this.cardinality = CardinalityType.ONE_TO_ONE;
                break;

            default:
                throw new JGenException("Unknown cardinality: " + cardinality);
        }
    }

    public String generateType() {
        Preconditions.checkNotNull(cardinality, "ForeignKey without CardinalityType");

        if (cardinality.equals(CardinalityType.ONE_TO_MANY)) {
            return String.format("List<%s>", parentTable);
        }

        return String.format("%s", parentTable);
    }

    public String generateName() {
        Preconditions.checkNotNull(cardinality, "ForeignKey without CardinalityType");

        String ret = Utility.camelCase(parentTable);

        if (cardinality.equals(CardinalityType.ONE_TO_MANY)) {
            ret += "List";
        }

        return ret;
    }

    public List<Map<String, String>> getColumns() {
        return columns;
    }

    public void setColumns(List<Map<String, String>> columns) {
        this.columns = columns;
    }

    public String buildAnnotation() {
        String relation = "@Relation(";
        relation += String.format("%n%s" + "cardinality = CardinalityType.%s,", begin, cardinality);
        relation += String.format("%n%s" + "constraintName = \"%s\",", begin, name);
        relation += String.format("%n%s" + "constrained = true,", begin);
        relation += String.format("%n%s" + "join = %s", begin, getRelationJoins());
        relation += ")";

        return relation;
    }

    public String getRelationJoins() {
        String open = "";
        String close = "";

        String space = begin;

        if (columns.size() > 1) {
            space = begin + "\t";
            open = "{\n" + space;
            close = "}";
        }

        int i = 0;
        StringBuilder sbRelations = new StringBuilder("");
        for (Map<String, String> col : columns) {
            // more than 1 element
            if (i > 0) {
                sbRelations.append(String.format("%n%s, ", space));
            }
            sbRelations.append(String.format("@RelationJoin(srcElement = \"%s\", targetElement = \"%s\")", col.get("src"), col.get("target")));
            i++;
        }

        return open + sbRelations.toString() + close;
    }

    // unused code
//    public String getMember() {
//        return String.format("private %s %s;", parentTable, Utility.camelCase(parentTable));
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("name = %s, from = %s, to = %s, cardinality = %s",
                childTable, parentTable, name, cardinality));
        for (Map<String, String> col : columns) {
            sb.append(String.format("%n    src = %s, target = %s", col.get("src"), col.get("target")));
        }
        return sb.toString();
    }


}

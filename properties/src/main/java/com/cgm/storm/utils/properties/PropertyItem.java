package com.cgm.storm.utils.properties;

import com.cg.helix.schemadictionary.annotation.ComplexType;
import com.cg.helix.util.StringUtils;

import java.util.List;

/**
 * PropertyItem
 */
@ComplexType
public class PropertyItem {

    private Boolean editable;
    private Boolean mandatory;
    private String fieldName;       // internal name (name of database field)
    private String name;            // name displayed
    private String description;
    private String value;
    private String type;
    private String editstyle;
    private String dataSource;

    private static String defaultEditStyle = EditStyle.TextEdit.name();

    public PropertyItem() {

    }

    /**
     * A PropertiyItem describes a single Property
     *
     * @param type - Typename of the Property
     * @param fieldName - Name of the field of the object to which the property belongs
     * @param name - human readable Name (in UI lang.) of the property
     * @param value - String representation of the initial value of the property
     * @param mandatory - Specifies if the item is mandatory
     * @param editable - Specifies if item can be edited on frontend
     */
    public PropertyItem(String type, String fieldName, String name, Object value, Boolean mandatory, Boolean editable) {
        this.type = type;
        this.fieldName = fieldName;
        this.name = name;
        this.description = "";

        if( value != null ) {
            this.value = value.toString();
        }
        this.mandatory = mandatory;
        this.editable = editable;

        switch (type) {
            case "Integer":
                this.editstyle = EditStyle.NumberEdit.name();

                if (StringUtils.isNullOrEmpty( this.value)) {
                    this.value = "0";
                }

                break;
            case "Boolean":
                this.editstyle = EditStyle.CheckBox.name();

                if (StringUtils.isNullOrEmpty( this.value)) {
                    this.value = "false";
                }

                break;
            case "LookupDropdown":
                this.editstyle = EditStyle.LookupDropdown.name();
                break;
            case "Date":
                this.editstyle = EditStyle.DateEdit.name();
                break;
            case "DateTime":
                this.editstyle = EditStyle.DateTimeEdit.name();
                break;
            default:
                this.editstyle = defaultEditStyle;
        }
    }

    // <editor-fold desc = "Getter/Setter" >
    public String getFieldName() {
        return fieldName;
    }

    public PropertyItem setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public String getName() {
        return name;
    }

    public PropertyItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PropertyItem setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getType() {
        return type;
    }

    public PropertyItem setType(String type) {
        this.type = type;
        return this;
    }

    public String getValue() {
        return value;
    }

    public PropertyItem setValue(String val) {
        this.value = val;
        return this;
    }

    public String getEditstyle() {
        return editstyle;
    }

    public PropertyItem setEditstyle(String newEditStyle) {
        this.editstyle = newEditStyle;
        return this;
    }

    public PropertyItem setEditstyle(EditStyle editStyle) {
        this.setEditstyle(editStyle.name());
        return this;
    }

    public PropertyItem setDataSource(String dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public String getDataSource() {
        return dataSource;
    }

    public Boolean getMandatory() { return mandatory; }

    public PropertyItem setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
        return this;
    }

    public Boolean getEditable() {
        return editable;
    }

    public PropertyItem setEditable(Boolean editable) {
        this.editable = editable;
        return this;
    }

    // </editor-fold>


    @Override
    public String toString() {
        return "      -PropertyItem{\n" +
                "          field='" + fieldName + '\'' + "\n" +
                "          name='" + name + '\'' + "\n" +
                "          description='" + description + '\'' + "\n" +
                "          value='" + value + '\'' + "\n" +
                "          type='" + type + '\'' + "\n" +
                "          lookupDataSource='" + dataSource + '\'' + "\n" +
                "      }\n";
    }

}




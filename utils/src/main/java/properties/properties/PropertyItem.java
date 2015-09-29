package properties.properties;

/**
 *
 */
public class PropertyItem {
    private String fieldName;
    private String name;
    private String value;
    private Object actualValue;

    private Boolean mandatory;
    private Boolean editable;
    private String editStyle;
    private String dataSource;

    public PropertyItem(String fieldName, String name, Object value, Boolean mandatory, Boolean editable) {
        this.fieldName = fieldName;
        this.name = name;
        setValue(value);
        this.mandatory = mandatory;
        this.editable = editable;
    }

    public void setValue(Object value) {
        this.actualValue = value;
        this.value = String.valueOf(value);
    }

    public String getValue() {
        return value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getActualValue() {
        return actualValue;
    }
}

package fieldhandler.accessor;

/**
 *
 */
public class TestClass {
    private String onlyGetter;

    private String onlySetter;

    private double noGetterNoSetter;

    private Integer withGetterSetter;


    public TestClass() {
    }

    public TestClass(String onlyGetter, String onlySetter, double noGetterNoSetter, Integer withGetterSetter) {
        this.noGetterNoSetter = noGetterNoSetter;
        this.onlyGetter = onlyGetter;
        this.onlySetter = onlySetter;
        this.withGetterSetter = withGetterSetter;
    }

    public String getOnlyGetter() {
        return onlyGetter;
    }

    public void setOnlySetter(String onlySetter) {
        this.onlySetter = onlySetter;
    }

    public Integer getWithGetterSetter() {
        return withGetterSetter;
    }

    public void setWithGetterSetter(Integer withGetterSetter) {
        this.withGetterSetter = withGetterSetter;
    }
}

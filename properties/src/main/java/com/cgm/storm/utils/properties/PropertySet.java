package com.cgm.storm.utils.properties;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class PropertySet {
    private String info;

    private List<PropertyCategory> categoryList = new ArrayList<>();

    public void addCategory(PropertyCategory cat) {
        categoryList.add(cat);
    }

    public PropertyCategory getCategory(String name) {
        for (PropertyCategory cat : categoryList) {
            if (cat.getName().equals(name)) {
                return cat;
            }
        }
        return null;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<PropertyCategory> getCategoryList() {
        return categoryList;
    }

    public static PropertySet createDemo() {

        PropertySet ps = new PropertySet();

        ps.info = "Demo Properties";

        PropertyCategory cat = new PropertyCategory("/com/cgm/storm/PsDemo.personDataCategory.label"); // Persönliche Angaben
        ps.addCategory(cat);

        cat.addItem(new PropertyItem("String", "FirstName", "/com/cgm/storm/PsDemo.firstName.label", "Josef", true, true));
        cat.addItem(new PropertyItem("String", "LastName", "/com/cgm/storm/PsDemo.lastName.label", "Haslinger", true, false));
        cat.addItem(
                new PropertyItem("Integer", "Age", "/com/cgm/storm/PsDemo.ageInYears.label", "46", false, true)
                        .setEditstyle(EditStyle.NumberEdit)
                        .setDescription("/com/cgm/storm/PsDemo.ageInYears.label")
        );
        cat.addItem(new PropertyItem("Date", "CurrentDate", "/com/cgm/storm/PsDemo.currentDate.label",
                    "", false, true));
        cat.addItem(new PropertyItem("DateTime", "CurrentDateTime", "/com/cgm/storm/PsDemo.currentDateTime.label",
                "", false, true));

        cat = new PropertyCategory("/com/cgm/storm/PsDemo.programmingLanguagesCategory.label");
        ps.addCategory(cat);

        cat.addItem(new PropertyItem("String", "Cpp", "/com/cgm/storm/Common.Cpp.label", "", false, true));
        cat.addItem(new PropertyItem("String", "Net", "/com/cgm/storm/Common.Net.label", "", false, true));
        cat.addItem(new PropertyItem("Integer", "Java", "/com/cgm/storm/Common.Java.label", "", false, false));
        cat.addItem(new PropertyItem("Integer", "JavaScript", "/com/cgm/storm/Common.JavaScript.label", "", false, true));
        cat.addItem(new PropertyItem("Integer", "Angular", "/com/cgm/storm/Common.AngularJS_Framework.label", "", false, true));
        cat.addItem(new PropertyItem("Integer", "SQL1", "/com/cgm/storm/Common.MS_SQL.label", "", false, true));
        cat.addItem(new PropertyItem("Integer", "SQL2", "/com/cgm/storm/Common.ORACLE_SQL.label", "", false, true));

        cat = new PropertyCategory("Betriebssysteme");
        ps.addCategory(cat);

        cat.addItem(new PropertyItem("Boolean", "Dos", "/com/cgm/storm/Common.MS_DOS.label", "true", false, false));
        cat.addItem(new PropertyItem("Boolean", "Unix", "/com/cgm/storm/Common.UNIX.label", "true", false, true));
        cat.addItem(new PropertyItem("Boolean", "Linux", "/com/cgm/storm/Common.Linux.label", "true", false, true));
        cat.addItem(new PropertyItem("Boolean", "Mac", "/com/cgm/storm/Common.Mac_OS_X.label", "true", false, true));
        cat.addItem(new PropertyItem("Boolean", "Win", "/com/cgm/storm/Common.Windows.label", "true", false, true));

        cat = new PropertyCategory("LookupTests");
        ps.addCategory(cat);

        cat.addItem(new PropertyItem("LookupDropdown", "FavIDE", "/com/cgm/storm/Common.FavoriteIDE.label", "002",
                false, true)).setEditstyle(EditStyle.LookupDropdown)
                .setDataSource("services/com/cgm/storm/properties/PropertyLookupService/getData?param=IdeTypes");

        cat.addItem(new PropertyItem("LookupDropdown", "FavLang", "/com/cgm/storm/Common.FavoriteLanguage2.label",
                "008", false, false)).setEditstyle(EditStyle.LookupDropdown)
                .setDataSource("services/com/cgm/storm/properties/PropertyLookupService/getData?param=ProgLanguages");

        return ps;
    }

    @Override
    public String toString() {
        return "PropertySet{" + "\n" +
                "   info = '" + info + "\'\n" +
                "   categoryList = {\n" +
                getCategoryListAsString() +
                "   }\n" +
                "}\n";
    }

    private String getCategoryListAsString() {
        StringBuilder sb = new StringBuilder("");
        for (PropertyCategory cat : categoryList) {
            sb.append(cat);
        }

        return sb.toString();
    }
}

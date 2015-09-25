package com.cgm.storm.utils.properties;

/**
 * Created by josefhaslinger on 13.11.2014.
 */
public enum DataType {
    Boolean,
    Integer,
    String,
    Number,
    Date,
    Datetime;

//    public static DataType mapHelixDataType(com.cg.helix.datatypes.DataType helixDataType) {
//        switch (helixDataType.getName()) {
//            case "SHORT":
//            case "INTEGER":
//            case "LONG":
//                return DataType.Integer;
//            default:
//                return DataType.String;
//
//        }
//
//    }

    public static EditStyle defaultEditStyle(DataType dataType) {
        return EditStyle.TextEdit;
    }
}

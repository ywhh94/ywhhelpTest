package ywh.wh.test.database;

/**
 * Created by Administrator on 2018-06-27.
 */

public class BeanDataInfo {
    private String type;
    private String fieldName;

    public BeanDataInfo(String type, String fieldName) {
        this.type = type;
        this.fieldName = fieldName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}

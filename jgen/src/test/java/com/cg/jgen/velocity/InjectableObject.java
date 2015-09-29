package com.cg.jgen.velocity;

/**
 *
 */
public class InjectableObject {
    private String name;
    private String desc;

    public InjectableObject(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

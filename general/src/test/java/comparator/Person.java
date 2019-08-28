package comparator;

class Person {
    private Integer id;
    private String name;
    private String desc;

    public Person() {
    }

    public Person setId(Integer id) {
        this.id = id;
        return this;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Person setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}

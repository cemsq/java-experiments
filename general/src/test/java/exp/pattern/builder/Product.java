package exp.pattern.builder;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 *
 */
public class Product {
    private String id;
    private String name;
    private String desc;
    private Integer num;

    public static class Builder {
        private String id;
        private String name;
        private String desc;
        private Integer num;

        public Builder() {
            id = "";
            name = "";
            desc = "";
            num = 0;
        }

        public Builder id(String id) {this.id = id; return this;}
        public Builder name(String name) {this.name = name; return this;}
        public Builder desc(String desc) {this.desc = desc; return this;}
        public Builder num(Integer num) {this.num = num; return this;}

        public Product build() {
            Product p = Product.create(this);

            checkString(p.getId(), "Id not defined");
            checkString(p.getName(), "Name not defined");

            return p;
        }

        public final void checkString(String str, String msg) {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(str), msg);
        }
    }

    private Product(Builder builder) {
        id = builder.id;
        name = builder.name;
        desc = builder.desc;
        num = builder.num;
    }

    public static Product create(Builder builder) {
        return new Product(builder);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

package reflection;

public interface ReflectedClass {

    FieldAccessor<?> field(String fieldName);
}

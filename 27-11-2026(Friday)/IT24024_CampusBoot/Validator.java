import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {
    public static List<String> validate(Object obj) {
        List<String> violations = new ArrayList<>();
        for (Field f : obj.getClass().getDeclaredFields()) {
            Range r = f.getAnnotation(Range.class);
            if (r == null || (f.getType() != int.class && f.getType() != Integer.class)) continue;
            try {
                f.setAccessible(true);
                int value = f.getInt(obj);
                if (value < r.min() || value > r.max()) {
                    violations.add(f.getName() + " - " + r.message() + " (actual: " + value + ")");
                }
            } catch (IllegalAccessException e) {
                violations.add(f.getName() + " - cannot be read");
            }
        }
        return violations;
    }
}

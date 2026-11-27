import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class Container {
    private final Map<String, Object> beans = new LinkedHashMap<>();

    public void scan(Class<?>... classes) {
        for (Class<?> cls : classes) {
            if (!cls.isAnnotationPresent(Component.class)) {
                System.out.println("[Container] Skipped: " + cls.getSimpleName() + " (no @Component)");
                continue;
            }
            Component comp = cls.getAnnotation(Component.class);
            String name = comp.value();
            if (name.isEmpty()) {
                String simple = cls.getSimpleName();
                name = Character.toLowerCase(simple.charAt(0)) + simple.substring(1);
            }
            try {
                Constructor<?> ctor = cls.getDeclaredConstructor();
                ctor.setAccessible(true);
                beans.put(name, ctor.newInstance());
                System.out.println("[Container] Registered: " + name + " (" + cls.getSimpleName() + ")");
            } catch (ReflectiveOperationException e) {
                System.out.println("[Container] Skipped: " + cls.getSimpleName()
                        + " (cannot instantiate: " + e.getClass().getSimpleName() + ")");
            }
        }
    }

    public void injectValues(Map<String, String> config) {
        for (Object bean : beans.values()) {
            for (Field f : bean.getClass().getDeclaredFields()) {
                Value v = f.getAnnotation(Value.class);
                if (v == null) continue;
                String key = v.value();
                String raw = config.get(key);
                if (raw == null) {
                    System.out.println("[Container] Skipped: " + key + " (no config value)");
                    continue;
                }
                try {
                    f.setAccessible(true);
                    Class<?> t = f.getType();
                    if (t == int.class || t == Integer.class) {
                        f.set(bean, Integer.parseInt(raw.trim()));
                    } else if (t == String.class) {
                        f.set(bean, raw);
                    } else {
                        System.out.println("[Container] Skipped: " + key + " (unsupported type "
                                + t.getSimpleName() + ")");
                        continue;
                    }
                    System.out.println("[Container] Injected: " + key + " -> "
                            + bean.getClass().getSimpleName() + "." + f.getName());
                } catch (NumberFormatException e) {
                    System.out.println("[Container] Skipped: " + key + " (not a valid int: " + raw + ")");
                } catch (IllegalAccessException e) {
                    System.out.println("[Container] Skipped: " + key + " (access denied)");
                }
            }
        }
    }

    public Object getBean(String name) {
        return beans.get(name);
    }

    public Iterable<Object> getBeans() {
        return beans.values();
    }
}

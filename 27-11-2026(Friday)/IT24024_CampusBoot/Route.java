import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Route {
    /** URL path as named in the specification table: @Route(path = "/hello"). */
    String path() default "";

    /** Shorthand so the starter code's @Route("/hello") compiles. */
    String value() default "";
}

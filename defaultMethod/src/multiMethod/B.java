package multiMethod;

/**
 */
public interface B extends A{
    @Override
    default void hello() {
        System.out.println("hello from B.");
    }
}

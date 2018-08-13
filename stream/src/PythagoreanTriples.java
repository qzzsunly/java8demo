import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <pre>
 毕达哥拉斯三元数(勾股定理）
 * </pre>
 */
public class PythagoreanTriples {
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100).boxed().flatMap(a -> {
            //b 从a 开始生成避免重复
            return IntStream.rangeClosed(a, 100).mapToObj(b -> {
                return new double[]{a, b, Math.sqrt(a * a + b * b)};
            }).filter(t -> {
                return t[2] % 1 == 0;
            });
        }).forEach(t -> System.out.println(t[0] +","+ t[1] + ","+ t[2]));
    }
}

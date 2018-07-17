import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 并行流
 *
 * @author qzz
 */
public class ParallelStreamTest {
    public static void main(String[] args) {

        int max = 10000000;
        List<String> list = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            list.add(uuid.toString());
        }

        //顺序排序
        long t0 = System.nanoTime();
        System.out.println(String.format("开始：%s", t0));
        long count = list.stream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();
        System.out.println(String.format("结束：%s", t1));

        long time = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("花费时间：%d", time));

        //并行排序


        long t2 = System.nanoTime();
        System.out.println(String.format("开始：%s", t2));
        long count1 = list.parallelStream().sorted().count();
        System.out.println(count1);

        long t3 = System.nanoTime();
        System.out.println(String.format("并行结束：%s", t3));

        long time1 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
        System.out.println(String.format("并行排序花费时间：%d", time1));
    }
}

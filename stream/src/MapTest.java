import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 描述：
 * 构建组：大道金服科技部
 * 作者:秦在振
 * 邮箱:qinzaizhen@ddjf.com.cn
 * 日期:18-7-16
 * 版权：大道金服
 * </pre>
 */
public class MapTest {
    public static void main(String[] args) {
        String[] words = new String[]{"hello","word"};
        //获取字符串数组中每个字符串的长度
        List<Integer> lengths = Arrays.stream(words).map(String::length).collect(Collectors.toList());
        System.out.println(lengths);

        //将字符数组转换成单个字符
        List<String[]> strings = Arrays.stream(words).map(s -> s.split("")).collect(Collectors.toList());
        //转换出来的是字符串数组[[h,e,l,l,o],[w,o,r,d]]
        //需要将两个数组进行合并，通过flatMap来进行
        System.out.println(strings);

        List<String> strings1 = Arrays.stream(words).map(s -> s.split("")).flatMap(Arrays::stream).collect(Collectors.toList());
        System.out.println(strings1);
    }
}

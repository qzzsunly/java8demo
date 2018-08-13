import java.util.stream.Stream;

/**
 * 斐波纳契数组
 */
public class Fibonacoci {
	public static void main(String[] args) {
		// iterate 方法生成了一个所有正偶数的流:流的第一个元素是初始值 0 。然后加
		// 上 2 来生成新的值 2 ,再加上 2 来得到新的值 4 ,以此类推。这种 iterate 操作基本上是顺序的,
		// 因为结果取决于前一次应用。请注意,此操作将生成一个无限流——这个流没有结尾,因为值是
		// 按需计算的,可以永远计算下去。
		Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

		Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[1], t[0] + t[1] }).limit(20)
				.forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));
		// 取数组的第一个数即是斐波纳契数列

		Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[1], t[0] + t[1] }).limit(20)
				.map(t -> t[0]).forEach(System.out::println);

		// 与 iterate 方法类似, generate 方法也可让你按需生成一个无限流。但 generate 不是依次
		// 对每个新生成的值应用函数的。它接受一个 Supplier<T> 类型的Lambda提供新的值。我们先来
		// 看一个简单的用法:
		Stream.generate(Math::random).limit(5).forEach(System.out::println);
	}
}

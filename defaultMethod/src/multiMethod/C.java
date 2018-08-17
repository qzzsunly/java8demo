package multiMethod;

/**
 * 继承多个有相同默认方法的接口
 */
public class C implements A, B {
	public static void main(String[] args) {
		new C().hello();
	}

}

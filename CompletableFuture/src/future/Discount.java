package future;

import java.util.Random;

/**
 * 折扣
 */
public class Discount {
    private Random random = new Random();
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;
        Code(int percentage) {
            this.percentage = percentage;
        }
    }

	public static String applyDiscount(Quote quote) {
        String s = quote.getShopName() + " price is "
                + Discount.apply(quote.getPrice(), quote.getDiscountCode());
        System.out.println(s);
        return s;
	}

	private static double apply(double price, Code code) {
		delay();
		return format(price * (100 - code.percentage) / 100);
	}

    private static double format(Double v) {
        return v;
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
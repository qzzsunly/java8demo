package future;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class Shop {
	private Random random = new Random();

	/*
	 * public double getPrice(String product) { return calculatePrice(product); }
	 */

	public String getPrice(String product) {
		double price = calculatePrice(product);
		Discount.Code code = Discount.Code.values()[random
				.nextInt(Discount.Code.values().length)];
		return String.format("%s:%.2f:%s", product, price, code);
	}

	private double calculatePrice(String product) {
		//模拟随机延迟
		randomDelay();
		return random.nextDouble() * product.charAt(0) + product.charAt(1);
	}

	public Future<Double> getPriceAsync(String product) {
		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
		new Thread(() -> {
			try {
				double price = calculatePrice(product);
				futurePrice.complete(price);
			}
			catch (Exception e) {
				futurePrice.completeExceptionally(e);
			}
		}).start();
		return futurePrice;
	}

	public Future<Double> getPriceAsync2(String product) {
		return CompletableFuture.supplyAsync(() -> calculatePrice(product));
	}

	/*
	 * private double calculatePrice(String product) { if (1 == 1) { throw new
	 * RuntimeException("计算出现异常"); } delay(); return random.nextDouble() *
	 * product.charAt(0) + product.charAt(1); }
	 */

	public Stream<CompletableFuture<String>> findPricesStream(String product) {
		List<Shop> shops = Arrays.asList(new Shop(), new Shop(), new Shop());
		return shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product)))
				.map(future -> future.thenApply(Quote::parse))
				.map(future -> future.thenCompose(quote -> CompletableFuture
						.supplyAsync(() -> Discount.applyDiscount(quote))));
	}

	public static void delay() {
		try {
			Thread.sleep(1000L);
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public  void randomDelay() {
		int delay = 500 + random.nextInt(2000);
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
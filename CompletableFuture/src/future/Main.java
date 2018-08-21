package future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 */
public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop();
        long start = System.nanoTime();
        CompletableFuture[] futures = shop.findPricesStream("myPhone")
                .map(f -> f.thenAccept(s -> System.out.println(s + " (done in " +
                        ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
                .toArray(CompletableFuture[]::new);
        //等待所有任务返回
        CompletableFuture.allOf(futures).join();
        //有一个返回即可
//        CompletableFuture.anyOf(futures).join();
        System.out.println("All shops have now responded in "
                + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }

    public static void main2(String[] args) {
        Shop shop = new Shop();
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync2("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime
                + " msecs");
        try {
            // 执行更多任务,比如查询其他商店
            doSomethingElse();
            // 在计算商品价格的同时
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    private static void doSomethingElse() throws InterruptedException {
        Thread.sleep(1000);
    }
}

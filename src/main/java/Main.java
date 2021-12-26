import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    final static int SHOP_COUNT = 3;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<int[]>[] shops = new FutureTask[SHOP_COUNT];
        for (int i = 0; i < SHOP_COUNT; i++) {
            createShop(shops, i);
        }

        LongAdder report = new LongAdder();
        for (int i = 0; i < SHOP_COUNT; i++) {
            getRevenue(shops[i], report);
        }
        System.out.println("\nОбщая выручка = " + report.sum() + " рублей");
    }

    private static void createShop(FutureTask<int[]>[] shops, int i) {
        final Shop shop = new Shop(Integer.toString(i + 1));
        shops[i] = new FutureTask<>(shop);
        new Thread(shops[i]).start();
    }

    private static void getRevenue(FutureTask<int[]> futureTask, LongAdder report) throws InterruptedException, ExecutionException {
        int[] shopSales = futureTask.get();
        Arrays.stream(shopSales).forEach(report::add);
    }
}

import java.util.Random;
import java.util.concurrent.Callable;

public class Shop implements Callable {

    final int MAX_RANGE = 50;
    final int MIN_RANGE = 1;
    final int MAX_COUNT = 4;

    private String shopName;

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    @Override
    public int[] call() {
        int count = MAX_COUNT;
        int[] revenue = new int[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            revenue[i] = random.nextInt(MAX_RANGE - MIN_RANGE);
            System.out.println("Продажа в магазине \"" + shopName + "\" на сумму " + revenue[i]);
        }
        return revenue;
    }
}

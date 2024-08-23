import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        System.out.println("\n1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).");

        List<Transaction> transactions1 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .toList();
        System.out.println(transactions1);

        System.out.println("\n2. Вывести список неповторяющихся городов, в которых работают трейдеры.");

        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .forEach(System.out::println);

        System.out.println("\n3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.");

        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .distinct()
                .toList();
        System.out.println(traders);

        System.out.println("\n4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.");

        String names = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted()
                .distinct()
                .reduce("", (str, name) -> str + name + " ");
        System.out.println(names);

        System.out.println("\n5. Выяснить, существует ли хоть один трейдер из Милана.");

        System.out.println(
                transactions.stream()
                        .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"))
        );

        System.out.println("\n6. Вывести суммы всех транзакций трейдеров из Кембриджа.");
        
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        System.out.println("\n7. Какова максимальная сумма среди всех транзакций?");

        System.out.println(
                transactions.stream()
                        .map(Transaction::getValue)
                        .max(Comparator.comparingInt(Integer::intValue))
                        .get()
        );

        System.out.println("\n8. Найти транзакцию с минимальной суммой.");

        System.out.println(
                transactions.stream()
                        .min(Comparator.comparingInt(Transaction::getValue))
                        .get()
        );
    }
}
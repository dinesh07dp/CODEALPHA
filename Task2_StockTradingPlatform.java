import java.util.*;

class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class Portfolio {
    private Map<String, Integer> holdings;
    private double balance;

    public Portfolio(double balance) {
        this.holdings = new HashMap<>();
        this.balance = balance;
    }

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;
        if (balance >= cost) {
            holdings.put(stock.getSymbol(), holdings.getOrDefault(stock.getSymbol(), 0) + quantity);
            balance -= cost;
            System.out.println("Bought " + quantity + " shares of " + stock.getSymbol());
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void sellStock(Stock stock, int quantity) {
        if (holdings.getOrDefault(stock.getSymbol(), 0) >= quantity) {
            holdings.put(stock.getSymbol(), holdings.get(stock.getSymbol()) - quantity);
            balance += stock.getPrice() * quantity;
            System.out.println("Sold " + quantity + " shares of " + stock.getSymbol());
        } else {
            System.out.println("Not enough shares to sell.");
        }
    }

    public void displayPortfolio() {
        System.out.println("Portfolio Holdings:");
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " shares");
        }
        System.out.println("Balance: $" + balance);
    }
}

public class Task2_StockTradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Portfolio portfolio = new Portfolio(10000); // Starting balance
        Stock apple = new Stock("AAPL", 150);
        Stock google = new Stock("GOOGL", 2800);
        Stock amazon = new Stock("AMZN", 3400);

        Map<String, Stock> market = new HashMap<>();
        market.put("AAPL", apple);
        market.put("GOOGL", google);
        market.put("AMZN", amazon);

        while (true) {
            System.out.println("1. View Portfolio");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    portfolio.displayPortfolio();
                    break;
                case 2:
                    System.out.print("Enter stock symbol: ");
                    String buySymbol = scanner.next();
                    if (market.containsKey(buySymbol)) {
                        System.out.print("Enter quantity: ");
                        int buyQuantity = scanner.nextInt();
                        portfolio.buyStock(market.get(buySymbol), buyQuantity);
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter stock symbol: ");
                    String sellSymbol = scanner.next();
                    if (market.containsKey(sellSymbol)) {
                        System.out.print("Enter quantity: ");
                        int sellQuantity = scanner.nextInt();
                        portfolio.sellStock(market.get(sellSymbol), sellQuantity);
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}


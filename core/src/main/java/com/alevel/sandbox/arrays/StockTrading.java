package com.alevel.sandbox.arrays;

public class StockTrading {

    public static void main(String[] args) {
        double[] prices = {15, 9.4, 9.8, 14, 12.3, 10, 8};

        System.out.println("Prices for the week are: ");
        for (int i = 0; i < prices.length; i++) {
            System.out.println("Day " + i + ": " + prices[i]);
        }

        int purchaseDay = 0;
        int saleDay = 0;
        double profit = 0;

        for (int day = 0, minPriceDay = 0; day < prices.length; day++) {
            double price = prices[day];
            double minPrice = prices[minPriceDay];
            if (price < minPrice) {
                minPriceDay = day;
            } else {
                double difference = price - minPrice;
                if (difference > profit) {
                    purchaseDay = minPriceDay;
                    saleDay = day;
                    profit = difference;
                }
            }
        }

        System.out.println("Max profit is " + profit +
                " with purchase on day " + (purchaseDay + 1) +
                " and sale on day " + (saleDay + 1));
    }
}

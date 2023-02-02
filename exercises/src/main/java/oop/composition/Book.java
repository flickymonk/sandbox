package oop.composition;

public class Book {
    private String isbn;

    private String name;
    private Author authors;
    private double price;
    private int qty;

    public Book(String isbn, String name, Author authors, double price) {
        this.isbn = isbn;
        this.name = name;
        this.authors = authors;
        this.price = price;
    }

    public Book(String isbn, String name, Author authors, double price, int qty) {
        this.isbn = isbn;
        this.name = name;
        this.authors = authors;
        this.price = price;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return authors;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author=" + authors +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }

    public String getAuthorName() {
        return getAuthor().getName();
    }

    public String getAuthorEmail() {
        return getAuthor().getEmail();
    }
}

package com.company9.entity;

public class BookInfo {
    String bookno;
    String bookname;
    String author;
    String sort;
    int amount;
    public BookInfo() {
    }
    public BookInfo(String bookno,String bookname,String author) {
        this.bookno=bookno;
        this.bookname=bookname;
        this.author=author;
    }
    public String getBookno() {
        return bookno;
    }
    public void setBookno(String bookno) {
        this.bookno = bookno;
    }
    public String getBookname() {
        return bookname;
    }
    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return bookno+","+bookname+","+author+","+","+sort+","+amount+"\n";
    }
}

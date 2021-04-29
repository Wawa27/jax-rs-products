package fr.wawa.products.exceptions;

public class NoEnoughProductException extends Exception {

    public NoEnoughProductException() {
        super("No enough product left");
    }
}

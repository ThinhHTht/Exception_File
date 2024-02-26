package ra;

import ra.imp.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface IBook {
    public abstract void inputData(Scanner scanner, List<Book> listBooks);
    public abstract void displayData();
}

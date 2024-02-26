package ra.imp;

import ra.IBook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Book implements IBook {
    private int bookId;
    private String bookName;
    private float importPrice;
    private float exportPrice;
    private String author;
    private Date created;
    private String descriptions;

    public Book() {
    }

    public Book(int bookId, String bookName, float importPrice, float exportPrice, String author, Date created, String descriptions) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.author = author;
        this.created = created;
        this.descriptions = descriptions;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public void inputData(Scanner scanner, List<Book> listBooks ) {
        this.bookName = inputBookName(scanner, listBooks);
        this.importPrice = inputImportPrice(scanner);
        this.exportPrice = inputExportPrice(scanner);
        this.author = inputAuthor(scanner);
        this.created = inputCreatedDate(scanner);
        this.descriptions = inputDescriptions(scanner);

    }

    @Override
    public void displayData() {
        System.out.println("List sách là:");
        System.out.printf("ID: %d - Tên sách: %s - Giá nhập: %.2f - Giá xuất: %.2f\n", this.bookId, this.bookName, this.importPrice, this.exportPrice);
        System.out.printf("Tác giả: %s - Ngày tạo: %s - Mô tả: %s\n", this.author, this.created.toString(), this.descriptions);

    }
    public String inputBookName(Scanner scanner, List<Book> listBooks){
        System.out.println("Hãy nhập tên sách:");
        boolean isExisting = true;
        do {
            String inputName = scanner.nextLine();
            for (int i = 0; i < listBooks.size(); i++) {
                if(listBooks.get(i).getBookName().equals(inputName)){
                    isExisting = false;
                    System.err.println("Tên sách nhập đã tồn tại, vui lòng nhập lại!");
                }

            }
            if(isExisting){
                if(Pattern.matches("[B]...", inputName)){
                    return inputName;
                } else {
                    System.err.println("Tên phải gồm 4 ký tự và bắt đầu bằng B");
                }
            }

        }while(true);
    }

    public float inputImportPrice(Scanner scanner){
        System.out.println("Hãy nhập giá nhập sách:");
        do {
            try{
                float inputImport = Float.parseFloat(scanner.nextLine());
                if(inputImport > 0){
                    return inputImport;
                } else {
                    System.err.println("Giá nhập phải > 0, vui lòng nhập lại!");
                }

            }catch (NumberFormatException ex){
                System.err.println("Vui lòng nhập số thực!");
            }

        }while(true);
    }

    public float inputExportPrice(Scanner scanner){
        System.out.println("Hãy nhập giá xuất:");
        do {
            try{
                float inputExport = Float.parseFloat(scanner.nextLine());
                if(inputExport > this.importPrice){
                    return inputExport;
                } else {
                    System.err.println("Giá xuất phải lớn hơn giá nhập, vui lòng nhập lại!");
                }

            }catch (NumberFormatException ex){
                System.err.println("Vui lòng nhập một số thực!");
            }


        }while(true);
    }

    public String inputAuthor(Scanner scanner){
        System.out.println("Hãy nhập tên tác giả:");
        do {
            String inputAuthor = scanner.nextLine();
            if(inputAuthor.length() >=6 && inputAuthor.length()<=50){
                return inputAuthor;
            } else {
                System.err.println("Tên tác giả phải có từ 6-50 ký tự, vui lòng nhập lại!");
            }

        }while(true);
    }

    public Date inputCreatedDate(Scanner scanner){
        System.out.println("Hãy nhập ngày tạo:");
        do {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try{
                Date inputDate = sdf.parse(scanner.nextLine());
                return inputDate;

            }catch (Exception ex){
                System.err.println("Vui lòng nhập theo định dạng: dd/MM/yyyy");
            }

        }while(true);
    }
    public String inputDescriptions(Scanner scanner){
        System.out.println("Hãy nhập mô tả cho sản phẩm:");
        do {
            String inputDes = scanner.nextLine();
            if(inputDes.length()<= 500){
                return inputDes;
            } else {
                System.err.println("Vui lòng nhập tối đa 500 ký tự!");
            }
        }while (true);
    }
}

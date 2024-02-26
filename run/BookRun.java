package ra.run;

import com.sun.security.jgss.GSSUtil;
import ra.imp.Book;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class BookRun {
    static List<Book> listBooks = new ArrayList<>();
    public static void main(String[] args) {
        writeDataToFile();
        readDataFromFile();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("******************************MENU******************************");
            System.out.println("1. Nhập thông tin sách");
            System.out.println("2. Hiển thị thông tin sách");
            System.out.println("3. Cập nhật thông tin theo mã sách");
            System.out.println("4. Xóa sách theo mã sách");
            System.out.println("5. Sắp xếp sách theo giá bán tăng dần");
            System.out.println("6. Thống kê sách theo khoảng giá a-b nhập từ bàn phím");
            System.out.println("7. Tìm kiếm sách theo tên tác giả");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn là: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    inputBooks(scanner, listBooks);
                    break;
                case 2:
                    displayListBooks();
                    break;
                case 3:
                    updateBook(scanner);
                    break;
                case 4:
                    deleteBookById(scanner);
                    break;
                case 5:
                    sortByExportPriceESC();
                    break;
                case 6:
                    searchByImportPriceRange(scanner);
                    break;
                case 7:
                    searchByAuthor(scanner);
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập từ 1-8");
            }

        }while(true);
    }
    public static void inputBooks(Scanner scanner, List<Book> listBooks){
        System.out.println("Nhập số lượng sách muốn thêm:");
        int numberInput = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberInput; i++) {
            Book newBook = new Book();
            newBook.setBookId(listBooks.size() + 1);
            newBook.inputData(scanner, listBooks);
            listBooks.add(newBook);
        }
    }

    public static void displayListBooks(){
        listBooks.forEach(book -> book.displayData());
    }

    public static void updateBook(Scanner scanner){
        System.out.println("Hãy nhập ID sản phẩm muốn update");
        boolean isExit = true;
        boolean isExisting = true;
        do {
            int inputId = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < listBooks.size(); i++) {
                if(listBooks.get(i).getBookId() == inputId){
                    isExisting = false;
                    do {
                        System.out.println("********Menu********");
                        System.out.println("1. Cập nhật tên sản phẩm:");
                        System.out.println("2. Cập nhật giá nhập sản phẩm:");
                        System.out.println("3. Cập nhật giá xuất sản phẩm:");
                        System.out.println("4. Cập nhật tên tác giả:");
                        System.out.println("5. Cập nhật ngày tạo:");
                        System.out.println("6. Cập nhật mô tả:");
                        System.out.println("7. Thoát khỏi cập nhật");
                        System.out.print("Lựa chọn của bạn là:");
                        int choice = Integer.parseInt(scanner.nextLine());
                        switch (choice){
                            case 1:
                                System.out.println("Hãy nhập tên sản phẩm mới:");
                                listBooks.get(i).setBookName(scanner.nextLine());
                                break;
                            case 2:
                                System.out.println("Hãy nhập giá nhập mới:");
                                listBooks.get(i).setImportPrice(Float.parseFloat(scanner.nextLine()));
                                break;
                            case 3:
                                System.out.println("Hãy nhập giá xuất mới:");
                                listBooks.get(i).setExportPrice(Float.parseFloat(scanner.nextLine()));
                                break;
                            case 4:
                                System.out.println("Hãy nhập tên tác giả mới:");
                                listBooks.get(i).setAuthor(scanner.nextLine());
                                break;
                            case 5:
//                                System.out.println("Hãy nhập ngày tạo mới:");
//                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                                listBooks.get(i).setCreated(sdf.parse(scanner.nextLine()));
                                break;
                            case 6:
                                System.out.println("Nhập mô tả mới:");
                                listBooks.get(i).setDescriptions(scanner.nextLine());
                                break;
                            case 7:
                                isExit = false;
                            default:
                                System.err.println("Vui lòng nhập từ 1-7");
                        }

                    }while(isExit);
                    break;
                }

            }if(isExisting){
                System.err.println("ID đã nhập không tồn tại, vui lòng nhập lại!");
            }

        }while(isExit);
    }

    public static int findIndexById(int id){
        for (int i = 0; i < listBooks.size(); i++) {
            if(listBooks.get(i).getBookId() == id){
                return i;
            }

        }
        return -1;
    }

    public static void deleteBookById(Scanner scanner){
        System.out.println("Hãy nhập Id sản phẩm muốn xóa:");
        int idInput = Integer.parseInt(scanner.nextLine());
        int indexDelete = findIndexById(idInput);
        if(indexDelete >= 0){
            listBooks.remove(indexDelete);
            System.out.println("Đã xóa xong sản phẩm!");
        } else {
            System.err.println("Id đã nhập không tồn tại!");
        }

    }
    public static void sortByExportPriceESC(){
        Collections.sort(listBooks, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return (int) Math.ceil(o1.getExportPrice() - o2.getExportPrice());
            }
        });
    }
    public static void searchByImportPriceRange(Scanner scanner){
        int count = 0;

            System.out.println("Hãy nhập mức giá from:");
            float fromPrice = Float.parseFloat(scanner.nextLine());
            System.out.println("Hãy nhập mức giá to:");
            float toPrice = Float.parseFloat(scanner.nextLine());
            System.out.println("Kết quả là:");
            for (int i = 0; i < listBooks.size(); i++) {
                if(listBooks.get(i).getImportPrice() >= fromPrice && listBooks.get(i).getImportPrice() <= toPrice ){
                    listBooks.get(i).displayData();
                    count ++;
                }

            }
            if(count == 0){
                System.out.println("Không có kết quả nào phù hợp!");
            }
            System.out.println("Số sản phẩm tìm được là: "+ count);

    }
    public static void searchByAuthor(Scanner scanner){
        int count = 0;
           System.out.println("Hãy nhập tên tác giả:");
           String authorInput = scanner.nextLine();
           for (int i = 0; i < listBooks.size(); i++) {
               if(listBooks.get(i).getAuthor().toLowerCase().contains(authorInput.toLowerCase())){
                   count++;
                   listBooks.get(i).displayData();
               }

           }
           if(count == 0){
               System.err.println("Không có kết quả nào phù hợp!");
           }
           System.out.println("Số kết quả tìm được là: " + count);
    }
    public static void writeDataToFile(){
        File file = new File("book.txt");
        try{
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream obs = new ObjectOutputStream(fos);
            obs.writeObject(listBooks);
            obs.flush();
            obs.close();
            fos.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }
    public static void readDataFromFile(){
        File file = new File("book.txt");
        try{
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Book> listReadBooks = (List<Book>) ois.readObject();
            listReadBooks.forEach(book -> book.displayData());
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    // Q: nhập sai Tên sách 1 lần thì ko tạo tiếp được (n>=2)
}

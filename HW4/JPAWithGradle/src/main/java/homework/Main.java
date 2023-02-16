package homework;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BookHelper bookHelper = new BookHelper();
        Book book = new Book();
        book.setAuthor("Boba");
        book.setName("Big Boba");
        bookHelper.addBook(book);

        Book book2 = new Book();
        book2.setAuthor("Boba 2");
        book2.setName("Big Boba 2");
        bookHelper.addBook(book2);

        List<Book> books = bookHelper.getBooksList();
        for(Book tmp:books){
            System.out.println(tmp.getId()+" "+tmp.getName()+" "+tmp.getAuthor());
        }
    }
}

package service.serviceImpl;

import db.Database;
import models.Book;
import models.Library;
import service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public Book saveBook(Long libraryId, Book book) {
        for (Library library : Database.librariesList){
            if(library.getId().equals(libraryId)){
                library.getBooks().add(book);
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> getAllBooks(Long libraryId) {
        for (Library library : Database.librariesList){
            if(library.getId().equals(libraryId)){
                return library.getBooks();
            }
        }
        return null;
    }

    @Override
    public Book getBookById(Long libraryId, Long bookId) {
        for(Library library: Database.librariesList){
            if (library.getId().equals(libraryId)) {
              for (Book book:library.getBooks()){
                  if (book.getId().equals(bookId)) {
                      return book;
                  }
              }
            }
        }
        return null ;
    }

    @Override
    public String deleteBook(Long libraryId, Long bookId) {
        for(Library library : Database.librariesList) {
            if (library.getId().equals(libraryId)) {
                List<Book> books = library.getBooks();
                for (Book book : books) {
                    if (book.getId().equals(bookId)) {
                       library.getBooks().remove(book);
                        return "Book менен id " + bookId + " ID менен китепканадан өчүрүлдү " + libraryId;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void clearBookByLibraryId(Long libraryId) {
        for (Library library : Database.librariesList){
            if (library.getId().equals(libraryId)) {
                library.getBooks().clear();
            }
        }
    }
}

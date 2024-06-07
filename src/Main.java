import enam.Gender;
import enam.Genre;
import models.Book;
import models.Library;
import models.Reader;
import service.BookService;
import service.LibraryService;
import service.ReaderService;
import service.serviceImpl.BookServiceImpl;
import service.serviceImpl.LibraryServiceImpl;
import service.serviceImpl.ReaderServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryServiceImpl();
        BookService bookService = new BookServiceImpl();
        ReaderService readerService = new ReaderServiceImpl();

        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("""
                    Опцияны тандоо
                    1. Китепкананы сактоо
                    2. Бардык китепканаларды алуу
                    3. Китепкананы id боюнча алуу
                    4. Китепкананы жаңыртуу
                    5. Китепкананы жок кылуу
                    6. Китепти сактоо
                    7. Китепканадагы бардык китептерди алуу
                    8. id боюнча китеп алуу
                    9. Китепти өчүрүү
                    10. Китепкана идентификатору боюнча
                        китептерди тазалоо
                    11. Окурманды сактоо
                    12. Бардык окурмандарды алуу
                    13. Окурманды id боюнча алуу
                    14. Окурманды жаныртуу
                    15. Окурманды китепканага кошуу
                    """);
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:{
                    System.out.println("Китепкананын маалыматын киргизиңиз (id, аты, дареги)");
                    Long libId = sc.nextLong();
                    sc.nextLine();
                    String libName = sc.nextLine();
                    String libAddress = sc.nextLine();
                    List<Book> books = new ArrayList<>();
                    List<Reader> readers = new ArrayList<>();
                    Library library = new Library(libId, libName, libAddress, books, readers);
                    List<Library> libraries = new ArrayList<>();
                    libraries.add(library);
                    libraryService.saveLibrary(libraries);
                    break;
                } case 2:{
                    System.out.println("Бардык китепканалар: ");
                    List<Library> libraries = libraryService.getAllLibraries();
                    for (Library library : libraries){
                        System.out.println(library);
                    }
                    break;
                } case 3:{
                    System.out.println("Китеп кананын id бериниз");
                    Long searchLibId = sc.nextLong();
                    Library foundLibrary = libraryService.getLibraryById(searchLibId);
                    if(foundLibrary != null){
                        System.out.println(foundLibrary);
                    }else {
                        System.out.println("Китеп кана табылган жок");
                    }break;
                } case 4:{
                    System.out.println("Жаңыртуу үчүн китепкана идентификаторун киргизиңиз: ");
                    Long updateLibId = sc.nextLong();
                    sc.nextLine();
                    System.out.println("Жаны ат киргиз: ");
                    String newName= sc.nextLine();
                    System.out.println("Жаңы даректи киргизиңиз: ");
                    String newAddress = sc.nextLine();
                    Library updateLibrary = new Library(updateLibId, newName, newAddress, new ArrayList<>(), new ArrayList<>());
                    Library updated = libraryService.updateLibrary(updateLibId, updateLibrary);
                    if(updated != null){
                        System.out.println("Жаныртылган китепкана: "+updated);
                    }else {
                        System.out.println("Китепкана табылган жок: ");
                    }
                    break;
                } case 5:{
                    System.out.println("Жок кылуу үчүн китепкана идентификаторун киргизиңиз: ");
                    Long deleteLibId = sc.nextLong();
                    String result = libraryService.deleteLibrary(deleteLibId);
                    System.out.println(result);
                    break;
                } case 6:{
                    System.out.println("Китепти кошуу үчүн китепкана идентификаторун киргизиңиз: ");
                    long libIdForBook = sc.nextLong();
                    sc.nextLine();
                    System.out.println("Китептин маалыматтарын киргизиниз(id, аты, автору, жанр: )");
                    Long bookId = sc.nextLong();
                    sc.nextLine();
                    String bookName = sc.nextLine();
                    String bookAuthor = sc.nextLine();
                    System.out.println("FANTASY, LITERARY, ARTISTIC, ANGEME;");
                    Genre bookGenre = Genre.valueOf(sc.nextLine().toUpperCase());
                    Book book = new Book(bookId, bookName, bookAuthor, bookGenre);
                    Book savedBook = bookService.saveBook(libIdForBook, book);
                    if(savedBook != null){
                        System.out.println("Китеп кошулду: "+savedBook);
                    }else {
                        System.out.println("Китеп кошулган жок");
                    }break;
                } case 7:{
                    System.out.println("Бардык китептерди алуу үчүн китепкана идентификаторун киргизиңиз: ");
                    Long libIdForBooks = sc.nextLong();
                    List<Book> allBooks = bookService.getAllBooks(libIdForBooks);
                    if(allBooks != null){
                        for (Book b : allBooks){
                            System.out.println(b);
                        }
                    }else {
                        System.out.println("Китепкана табылган жок");
                    }break;
                } case 8:{
                    System.out.println("Китепти алуу үчүн китепкана идентификаторун киргизиңиз: ");
                    Long libIdForBookSearch = sc.nextLong();
                    System.out.println("Китептин id киргизиниз: ");
                    Long bookIdSearch = sc.nextLong();
                    Book foundBook = bookService.getBookById(libIdForBookSearch, bookIdSearch);
                    if(foundBook != null){
                        System.out.println(foundBook);
                    }else {
                        System.out.println("Китепкана табылган жок");
                    }break;
                } case 9:{
                    System.out.println("Китепти жок кылуу үчүн китепкана идентификаторун киргизиңиз: ");
                    Long libIdForBookDelete = sc.nextLong();
                    System.out.println("Китептин id киргизиниз: ");
                    Long bookIdDelete = sc.nextLong();
                    String deleteResult = bookService.deleteBook(libIdForBookDelete, bookIdDelete);
                    System.out.println(deleteResult);
                    break;
                } case 10:{
                    System.out.println("Китептерди тазалоо үчүн китепкана идентификаторун киргизиңиз: ");
                    Long libIdForCear = sc.nextLong();
                    bookService.clearBookByLibraryId(libIdForCear);
                    System.out.println("Китептер китепканадан id-си менен тазаланды"+libIdForCear);
                    break;
                } case 11:{
//                    Scanner scc = new Scanner(System.in);
//                    Scanner scanner1 = new Scanner(System.in);
//                    Scanner scanner2 = new Scanner(System.in);
//                    Scanner scanner3 = new Scanner(System.in);
//                    Reader reader = new Reader();
//                    System.out.println("Окурмандын киргизиңиз (id, толук аты-жөнү, электрондук почтасы, телефон номери, жынысы): ");
//                    System.out.println("id");
//                      reader.setId(new Scanner(System.in).nextLong());
//                    System.out.println("fullName");
//                      reader.setFullName(scc.nextLine());
//                    System.out.println("email");
//                      reader.setEmail(scanner2.nextLine());
//                    System.out.println("phone");
//                      reader.setPhoneNumber(scanner3.nextLine());
//                    System.out.println("FEMALE and MALE");
//                    if (sc.nextLine().equalsIgnoreCase(String.valueOf(Gender.MALE))){
//                        reader.setGender(Gender.MALE);
//                    }else if (sc.nextLine().equalsIgnoreCase(String.valueOf(Gender.FEMALE))){
//                        reader.setGender(Gender.FEMALE);
//                    }else {
//                        System.out.println("Мындай жендер жок ! ");
//                    }
//                    readerService.saveReader(reader);
                    System.out.println("Id");
                    Long readerId = sc.nextLong();
                    System.out.println("FullName");
                    String readerFullName = sc.nextLine();
                    sc.nextLine();
                    System.out.println("Email");
                    String readerEmail = sc.nextLine();
                    System.out.println("Phone");
                    String readerPhoneNumber = sc.nextLine();
                    System.out.println("Enter gender (MALE / FEMALE):");
                    Gender readerGender = Gender.valueOf(sc.nextLine().toUpperCase());
                    Reader reader = new Reader(readerId, readerFullName, readerEmail, readerPhoneNumber, readerGender);
                    readerService.saveReader(reader);
                    System.out.println("Окурман сакталды: "+reader);
                    break;
                } case 12:{
                    System.out.println("Бардык окурманды чыгаруу");
                    List<Reader> allReaders = readerService.getAllReaders();
                    for (Reader r : allReaders){
                        System.out.println(r);
                    }break;
                } case 13:{
                    System.out.println("Окурман id киргиз: ");
                    Long readerIdSearch =sc.nextLong();
                    Reader foundReader = readerService.getReaderById(readerIdSearch);
                    if (foundReader != null){
                        System.out.println(foundReader);
                    }else {
                        System.out.println("Окурман табылган жок");
                    }break;
                } case 14:{
                    System.out.println("Жаңыртуу үчүн окурмандын идентификаторун киргизиңиз: ");
                    Long updateReaderId = sc.nextLong();
                    sc.nextLine();
                    System.out.println("Жаны толук атын киргизиниз");
                    String newFullName = sc.nextLine();
                    System.out.println("Жаңы электрондук почтаны киргизиңиз");
                    String newEmail = sc.nextLine();
                    System.out.println("Жаңы телефон номер");
                    String newPhoneNumber = sc.nextLine();
                    System.out.println("Жаңы жынысты киргизиңиз");
                    Gender newGender = Gender.valueOf(sc.nextLine().toUpperCase());
                    Reader updatedReader = new Reader(updateReaderId, newFullName, newEmail, newPhoneNumber, newGender);
                    Reader updated = readerService.updateReader(updateReaderId, updatedReader);
                    if(updated != null){
                        System.out.println("Жаңыртылган окурман:"+updated);
                    }else {
                        System.out.println("Окурман табылган жок");
                    }break;
                } case 15:{
                    System.out.println("Дайындоо үчүн окурмандын идентификаторун киргизиңиз");
                    Long assignReaderId = sc.nextLong();
                    sc.nextLine();
                    System.out.println("Окурманды дайындоо үчүн китепкана идентификаторун киргизиңиз");
                    Long assignLibraryId = sc.nextLong();
                    sc.nextLine();
                    readerService.assignReaderToLibrary(assignReaderId,assignLibraryId);
                    System.out.println("ID менен дайындалган окурман "+assignReaderId+" Идентификатор менен китепканага "+assignLibraryId);
                    break;
                } case 16:{
                    System.out.println("Чыгууда...");
                    sc.close();
                    break;
                } default:
                    System.out.println("Жараксыз опция. Сураныч, кайра аракет кылыңыз.");
                    break;
            }
        }
    }
}
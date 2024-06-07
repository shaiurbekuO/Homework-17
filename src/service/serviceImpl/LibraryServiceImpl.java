package service.serviceImpl;

import db.Database;
import models.Library;
import service.LibraryService;
import java.util.List;

public class LibraryServiceImpl implements LibraryService {




    @Override
    public List<Library> saveLibrary(List<Library> libraries) {
        Database.librariesList.addAll(libraries);
        return libraries;
    }

    @Override
    public List<Library> getAllLibraries() {

        return Database.librariesList;
    }

    @Override
    public Library getLibraryById(Long id) {
        for (Library library : Database.librariesList){
            if (library.getId().equals(id)){
                return library;
            }
        }
        return null;
    }

    @Override
    public Library updateLibrary(Long id, Library updateLibrary) {
        for (Library library : Database.librariesList){
            if (library.getId().equals(id)){
                library.setName(updateLibrary.getName());
                library.setBooks(updateLibrary.getBooks());
                library.setReaders(updateLibrary.getReaders());
                return  library;
            }
        }
        return null;
    }

    @Override
    public String deleteLibrary(Long id) {
        for (Library library : Database.librariesList){
            if(library.getId().equals(id)){
                Database.librariesList.remove(library);
                return "Идентификаторлуу китепкана " + id + " жок кылынды.";
            }
        }
        return "";
    }


}

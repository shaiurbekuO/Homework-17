package service.serviceImpl;

import db.Database;
import models.Book;
import models.Library;
import models.Reader;
import service.ReaderService;

import java.util.List;



public class ReaderServiceImpl implements ReaderService {
    @Override
    public void saveReader(Reader reader) {
        Database.readersList.add(reader);
    }

    @Override
    public List<Reader> getAllReaders() {
        return Database.readersList;
    }

    @Override
    public Reader getReaderById(Long id) {
        for(Reader reader : Database.readersList){
           if(reader.getId().equals(id)){
               return reader;
           }
        }
        return null;
    }

    @Override
    public Reader updateReader(Long id, Reader updateReader) {
        for ( Reader reader : Database.readersList){
            if(reader.getId().equals(id)){
                reader.setFullName(updateReader.getFullName());
                reader.setEmail(updateReader.getEmail());
                reader.setPhoneNumber(updateReader.getPhoneNumber());
                reader.setGender(updateReader.getGender());
                return reader;
            }
        }
        return null;
    }

    @Override
    public void assignReaderToLibrary(Long readerId, Long libraryId) {
        Reader reader = getReaderById(readerId);
        if(reader != null){
            for (Library library : Database.librariesList){
                if(library.getId().equals(libraryId)){
                    library.getReaders().add(reader);
                    return;
                }
            }
        }
    }
}

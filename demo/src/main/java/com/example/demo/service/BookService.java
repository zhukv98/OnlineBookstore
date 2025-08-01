package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.interfaces.IBookDAO;
import com.example.demo.interfaces.IBookService;

@Service
public class BookService implements IBookService {

    @Autowired
    IBookDAO bookDAO;

    public BookService(IBookDAO bookDAO){
        this.bookDAO = bookDAO;       
    }
    @Override
    public void createBook(Book book) {
        // TODO: Implement this method
        bookDAO.createBook(book);
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        return bookDAO.searchBooks(keyword);
    }

    @Override
    public void updateBook(Book book) {
        // TODO: Implement this method
        bookDAO.updateBook(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookDAO.deleteBook(id);
    }
    
    @Override
    public Book findBookById(Long id) {
        return bookDAO.findBookById(id);
    }
    
    @Override
    public List<Book> findAllBooks(){
        // TODO: Implement this method
        return bookDAO.findAllBooks();
    }

}
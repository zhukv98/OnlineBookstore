package com.example.demo.service;
import java.util.List;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    
    @Override
    public Page<Book> findPaginated(Pageable pageable) {
        long startTime = System.currentTimeMillis(); // 开始计时

        List<Book> allBooks;
        try {
            allBooks = findAllBooks();
        } catch (Exception e) {
            e.printStackTrace();
            allBooks = Collections.emptyList();
        }
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Book> list;

        if (allBooks.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, allBooks.size());
            list = allBooks.subList(startItem, toIndex);
        }

        var bookPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), allBooks.size());

        long endTime = System.currentTimeMillis(); // 结束计时
        System.out.println("Optimized method execution time: " + (endTime - startTime) + "ms");

        return bookPage;
    }
}
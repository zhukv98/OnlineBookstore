package com.example.demo.interfaces;
import java.util.List;

import com.example.demo.entity.Book;

public interface IBookService {

	public List<Book> findAllBooks() throws Exception;

	public List<Book> searchBooks(String keyword);

	public Book findBookById(Long id);

	public void createBook(Book book);

	public void updateBook(Book book);

	public void deleteBook(Long id);
}
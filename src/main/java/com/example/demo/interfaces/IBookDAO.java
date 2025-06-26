package com.example.demo.interfaces;
import java.util.List;

import com.example.demo.entity.Book;

public interface IBookDAO {
    /**
     * Retrieves all books from the database.
     *
     * @return a list of all books
     */
    List<Book> findAllBooks();

    /**
     * Searches for books based on a keyword.
     *
     * @param keyword the keyword to search for
     * @return a list of books that match the keyword
     */
    List<Book> searchBooks(String keyword);

    /**
     * Finds a book by its ID.
     *
     * @param id the ID of the book
     * @return the book with the specified ID, or null if not found
     */
    Book findBookById(Long id);

    /**
     * Creates a new book in the database.
     *
     * @param book the book to create
     */
    void createBook(Book book);

    /**
     * Updates an existing book in the database.
     *
     * @param book the book to update
     */
    void updateBook(Book book);

    /**
     * Deletes a book from the database by its ID.
     *
     * @param id the ID of the book to delete
     */
    void deleteBook(Long id);

    // Additional methods can be added as needed
}
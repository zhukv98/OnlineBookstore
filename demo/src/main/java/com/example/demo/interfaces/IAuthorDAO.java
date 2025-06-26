package com.example.demo.interfaces;
import java.util.List;

import com.example.demo.entity.Author;

public interface IAuthorDAO {
    /**
     * Retrieves all authors from the database.
     *
     * @return a list of all authors
     */
    List<Author> findAllAuthors();
    /**
     * Finds an author by their ID.
     *
     * @param id the ID of the author
     * @return the author with the specified ID, or null if not found
     */
    Author findAuthorById(Long id);
    /**
     * Creates a new author in the database.
     *
     * @param author the author to create
     */
    void createAuthor(Author author);
    /**
     * Updates an existing author in the database.
     *
     * @param author the author to update
     */
    void updateAuthor(Author author);
    /**
     * Deletes an author from the database by their ID.
     *
     * @param id the ID of the author to delete
     */
    void deleteAuthor(Long id);

}
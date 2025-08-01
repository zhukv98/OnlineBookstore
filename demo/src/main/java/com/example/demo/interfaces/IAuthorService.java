package com.example.demo.interfaces;
import java.util.List;

import com.example.demo.entity.Author;

public interface IAuthorService {

	public List<Author> findAllAuthors();

	public Author findAuthorById(Long id);

	public void createAuthor(Author author);

	public void updateAuthor(Author author);

	public void deleteAuthor(Long id);
}
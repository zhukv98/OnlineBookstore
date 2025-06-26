package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Author;
import com.example.demo.interfaces.IAuthorDAO;
import com.example.demo.repository.AuthorRepository;

@Repository("authorDAO")
@Profile({ "dev", "default" })
public class AuthorDAO implements IAuthorDAO {

	@Autowired
	AuthorRepository authorRepository;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Author findAuthorById(Long id) {
		return authorRepository.findById(id).get();
	}

	@Override
	public void createAuthor(Author author) {
		authorRepository.save(author);
	}

	@Override
	public void updateAuthor(Author author) {
		authorRepository.save(author);
	}

	@Override
	public void deleteAuthor(Long id) {
		var author = authorRepository.findById(id).get();
		authorRepository.deleteById(author.getId());
	}
}

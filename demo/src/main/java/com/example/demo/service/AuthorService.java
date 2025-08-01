package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Author;
import com.example.demo.interfaces.IAuthorDAO;
import com.example.demo.interfaces.IAuthorService;

@Service
public class AuthorService implements IAuthorService {

    @Autowired
    IAuthorDAO authorDAO;

    public AuthorService(IAuthorDAO authorDAO){
        this.authorDAO = authorDAO;
    }
    @Override
    public void createAuthor(Author author) {
        authorDAO.createAuthor(author);
    }
    @Override
    public void updateAuthor(Author author) {
        authorDAO.updateAuthor(author);
    }
    @Override
    public void deleteAuthor(Long id) {
        authorDAO.deleteAuthor(id);
    }
    @Override
    public Author findAuthorById(Long id) {
        return authorDAO.findAuthorById(id);
    }
    @Override
    public List<Author> findAllAuthors() {
        return authorDAO.findAllAuthors();
    }
    
}
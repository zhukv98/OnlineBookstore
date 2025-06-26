package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.entity.Publisher; // Add this import, adjust the package if needed

public interface IPublisherService {
    public List<Publisher> findAllPublishers();

    public Publisher findPublisherById(Long id);

    public void createPublisher(Publisher publisher);

    public void updatePublisher(Publisher publisher);

    public void deletePublisher(Long id);
}

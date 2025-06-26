package com.example.demo.interfaces;
import com.example.demo.entity.Publisher;

import java.util.List;

public interface IPublisherDAO {
    List<Publisher> findAllPublishers();

    Publisher findPublisherById(Long id);

    void createPublisher(Publisher publisher);

    void updatePublisher(Publisher publisher);

    void deletePublisher(Long id);
}
package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Publisher;
import com.example.demo.interfaces.IPublisherDAO;
import com.example.demo.interfaces.IPublisherService;

@Service
public class PublisherService implements IPublisherService {
    @Autowired
    IPublisherDAO publisherDAO;

    public PublisherService(IPublisherDAO publisherDAO){
        this.publisherDAO = publisherDAO;
    }
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Publisher> findAllPublishers() {
		return publisherDAO.findAllPublishers();
	}

	@Override
	public Publisher findPublisherById(Long id) {
		return publisherDAO.findPublisherById(id);
	}

	@Override
	public void createPublisher(Publisher publisher) {
		publisherDAO.createPublisher(publisher);
	}

	@Override
	public void updatePublisher(Publisher publisher) {
		publisherDAO.updatePublisher(publisher);
	}

	@Override
	public void deletePublisher(Long id) {
		publisherDAO.deletePublisher(id);
	}
}

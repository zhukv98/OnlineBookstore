package com.example.demo.controller;

import com.example.demo.entity.Publisher; 
import com.example.demo.service.PublisherService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

public class PublisherController {
    @Autowired
	private final PublisherService publisherService;

	public PublisherController(PublisherService publisherService) {
		this.publisherService = publisherService;

	}

	@RequestMapping("/publishers")
	public String findAllPublishers(Model model) {
		model.addAttribute("publishers", publisherService.findAllPublishers());
		return "list-publishers";
	}

	@RequestMapping("/publisher/{id}")
	public String findPublisherById(@PathVariable Long id, Model model) {

		model.addAttribute("publisher", publisherService.findPublisherById(id));
		return "list-publisher";
	}

	@GetMapping("/addPublisher")
	public String showCreateForm(Publisher publisher) {
		return "add-publisher";
	}

	@RequestMapping("/add-publisher")
	public String createPublisher(Publisher publisher, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-publisher";
		}

		publisherService.createPublisher(publisher);
		model.addAttribute("publisher", publisherService.findAllPublishers());
		return "redirect:/publishers";
	}

	@GetMapping("/updatePublisher/{id}")
	public ResponseEntity<String> showUpdateForm(@PathVariable Long id, Model model) {
		  Publisher publisher = publisherService.findPublisherById(id);
 		if (publisher == null) {
   			return ResponseEntity.notFound().build();
  	    }
  		model.addAttribute("publisher", publisher);
  		return ResponseEntity.ok("update-publisher");
	}

	@RequestMapping("/update-publisher/{id}")
	public ResponseEntity<String> updatePublisher(@PathVariable Long id, Publisher publisher, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Invalid publisher data");
		}

		publisherService.updatePublisher(publisher);
		return ResponseEntity.ok("Publisher updated successfully");
	}

	@RequestMapping("/remove-publisher/{id}")
	public ResponseEntity<String> deletePublisher(@PathVariable("id") Long id, Model model) {
		publisherService.deletePublisher(id);
		return ResponseEntity.ok("Publisher deleted successfully");
	}
}

package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.PublisherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BookController {
    @Autowired
    private final BookService bookService;
    @Autowired
    private final AuthorService authorService;
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final PublisherService publisherService;

    public BookController(PublisherService publisherService, CategoryService categoryService, BookService bookService,
            AuthorService authorService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.publisherService = publisherService;
    }

    @RequestMapping({ "/books", "/" })
    public String findAllBooks(Model model, @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {

        var currentPage = page.orElse(1);
        var pageSize = size.orElse(5);

        org.springframework.data.domain.Page<Book> bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("books", bookPage);

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "list-books";
    }

    @RequestMapping("/searchBook")
    public String searchBook(@Param("keyword") String keyword, Model model) {

        model.addAttribute("books", bookService.searchBooks(keyword));
        model.addAttribute("keyword", keyword);
        return "list-books";
    }

    @RequestMapping("/book/{id}")
    public String findBookById(@PathVariable("id") Long id, Model model) {

        model.addAttribute("book", bookService.findBookById(id));
        return "list-book";
    }

    @GetMapping("/add")
    public String showCreateForm(Book book, Model model) {

        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "add-book";
    }

    @RequestMapping("/add-book")
    public String createBook(Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-book";
        }

        bookService.createBook(book);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {

        model.addAttribute("book", bookService.findBookById(id));
        return "update-book";
    }

    @RequestMapping("/update-book/{id}")
    public String updateBook(@PathVariable("id") Long id, Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return "update-book";
        }

        bookService.updateBook(book);
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }

    @RequestMapping("/remove-book/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        bookService.deleteBook(id);

        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/books";
    }

}
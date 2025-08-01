package com.example.demo.service;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.example.demo.constant.Item;
import com.example.demo.interfaces.IFileService;
import com.example.demo.util.Mapper;
import com.example.demo.vo.AuthorRecord;
import com.example.demo.vo.BookRecord;
import com.example.demo.vo.CategoryRecord;
import com.example.demo.vo.PublisherRecord;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class FileService implements IFileService {
    private final BookService bookService;

	private final AuthorService authorService;

	private final PublisherService publisherService;

	private final CategoryService categoryService;

	public FileService(BookService bookService, AuthorService authorService, PublisherService publisherService,
			CategoryService categoryService) {
		this.authorService = authorService;
		this.categoryService = categoryService;
		this.publisherService = publisherService;
		this.bookService = bookService;
	}
	@Override
	public void exportCSV(String fileName, HttpServletResponse response)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		var item = Item.getItemByValue(fileName);
		response.setContentType("text/csv");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + item.get().getFileName() + "\"");

		switch (item.get()) {
		case BOOK -> {
			StatefulBeanToCsv<BookRecord> writer1 = getWriter(response.getWriter());
			writer1.write(Mapper.bookModelToVo(bookService.findAllBooks()));
		}
		case AUTHOR -> {
			StatefulBeanToCsv<AuthorRecord> writer2 = getWriter(response.getWriter());
			writer2.write(Mapper.authorModelToVo(authorService.findAllAuthors()));
		}
		case CATEGORY -> {
			StatefulBeanToCsv<CategoryRecord> writer3 = getWriter(response.getWriter());
			writer3.write(Mapper.categoryModelToVo(categoryService.findAllCategories()));
		}
		case PUBLISHER -> {
			StatefulBeanToCsv<PublisherRecord> writer4 = getWriter(response.getWriter());
			writer4.write(Mapper.publisherModelToVo(publisherService.findAllPublishers()));
		}
		}

	}

	private static <T> StatefulBeanToCsv<T> getWriter(PrintWriter printWriter) {
		return new StatefulBeanToCsvBuilder<T>(printWriter).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
				.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(false).build();
	}
}


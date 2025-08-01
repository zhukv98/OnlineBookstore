package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.service.FileService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FileExportController {
	@Autowired
	private final FileService fileService;

	public FileExportController(FileService fileService) {
		this.fileService = fileService;
	}

	@GetMapping("/export/{fileName}")
	public void exportCSV(@PathVariable(value = "fileName") String fileName, HttpServletResponse response)
			throws Exception {
		fileService.exportCSV(fileName, response);
	}

}

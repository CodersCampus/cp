package com.coderscampus.springwise.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DataFileService {
	public String[] getData(String filePath) {
		List<String> lines;
		

		try {

			lines = Files.readAllLines(Paths.get(filePath));

			String[] linesArray = lines.toArray(new String[0]);

			for (String line : linesArray) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

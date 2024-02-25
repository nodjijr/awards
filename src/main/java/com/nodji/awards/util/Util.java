package com.nodji.awards.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public interface Util {
	
	public static Iterable<CSVRecord> toCSVRecord(Reader reader) {
		try {
			return CSVFormat.RFC4180.withDelimiter(';')
					.withHeader("yearm", "title", "studios", "producers", "winner").parse(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}

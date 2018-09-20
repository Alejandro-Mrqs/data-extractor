package extractor.book.reader;

import java.io.File;
import java.io.FileReader;

import com.opencsv.CSVReader;

import extractor.book.transformer.CSVToObject;

public class CSVLineReader<T> {

	private CSVReader reader;
	private CSVToObject<T> transformer;
	
	public CSVLineReader(String file, CSVToObject<T> transformer) throws Exception {
		reader = new CSVReader(new FileReader(new File(getClass().getClassLoader().getResource(file).getFile())));
			reader.skip(1);
		this.transformer = transformer;
	}
		

	public T readLine () throws Exception {
		String[] lineFields = reader.readNext();
		
		if (null == lineFields) {
			return null;
		}
		
		return transformer.transform(lineFields);
	}
}

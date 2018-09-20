package extractor.book.writer;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWriter {
	
	private BufferedWriter writer;
	
	public FileWriter (String name) throws Exception {
		writer = Files.newBufferedWriter(Paths.get(name));
	}
	
	public void writeLine (String line) throws Exception {
		writer.write(line + "\n");
	}
	
	public void finish () throws Exception {
		writer.close();
	}
}
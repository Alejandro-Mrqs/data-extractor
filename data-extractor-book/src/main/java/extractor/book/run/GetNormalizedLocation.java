package extractor.book.run;

import extractor.book.writer.FileWriter;

public class GetNormalizedLocation {
	
	private static String[] locations = new String[] {
			"[[Hartford, Connecticut|Hartford]], [[Connecticut]], U.S.",
			"[[Yate]], Gloucestershire, England",
			"[[Hartford, Connecticut]], U.S.",
			"[[Monroeville, Alabama]], U.S.",
			"[[St. Paul, Minnesota]], U.S.",
			"[[Bloemfontein]], [[Orange Free State]] (modern-day South Africa)",
			"[[Manhattan, New York]], U.S.",
			"[[Exeter, New Hampshire]], U.S.",
			"[[Steventon, Hampshire|Steventon]] Rectory, [[Hampshire]], England"
	};
	
	private static FileWriter locationsFile;

	public static void main(String[] args) throws Exception {
		
		locationsFile = new FileWriter("locations.csv");
		
		for (String location : locations) {
			System.out.println(location);
			locationsFile.writeLine("\"" + location + "\"");
		}
		
		locationsFile.finish();
		
		System.out.println("END!");

	}

}

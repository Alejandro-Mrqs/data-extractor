package extractor.book.transformer;

public abstract class CSVToObject<T> {
		
		public abstract T transform (String[] data);

}

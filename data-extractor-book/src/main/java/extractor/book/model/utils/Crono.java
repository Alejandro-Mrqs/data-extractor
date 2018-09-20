package extractor.book.model.utils;

public class Crono {
	
	private long millis;
	
	public Crono() {
		set();
	}

	public void set() {
		millis = System.currentTimeMillis();
	}
	
	public long get() {
		long ellapsed = System.currentTimeMillis() - millis;
		set();
		return ellapsed;
	}
}

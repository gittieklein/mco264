package idiotsDelight;

public class EmptyDeckException extends RuntimeException{

	public EmptyDeckException(){
		super("Deck is empty");
	}
	private static final long serialVersionUID = 1L;

}

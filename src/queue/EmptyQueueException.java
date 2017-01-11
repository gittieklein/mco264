package queue;

public class EmptyQueueException extends RuntimeException{

	public EmptyQueueException(){
		super("empty queue");
	}
	private static final long serialVersionUID = 1L;

}

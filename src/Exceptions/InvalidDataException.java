package Exceptions;

public class InvalidDataException extends RuntimeException
{
	public InvalidDataException()
	{
		super("Invalid data");
	}
}

package idiotsDelight;

public class Card 
{
	private Rank rank;
	private Suit suit;
	private Color color;
	
	public Card(Rank rank, Suit suit, Color color)
	{
		this.rank = rank;
		this.suit = suit;
		this.color = color;
	}
	
	public Rank getRank()
	{
		return rank;
	}
	
	public Suit getSuit()
	{
		return suit;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public String toString()
	{
		StringBuilder csb = new StringBuilder();
		csb.append(rank + " " + suit.getSymbol());
		return csb.toString();		
	}
}

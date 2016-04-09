package Code;

import java.util.Random;

public class Bot {

	private int chosenI;
	private int chosenJ;

	public void choose(int i, int j)
	{
		Random rand = new Random();
		chosenI = rand.nextInt(3)+i;
		chosenJ = rand.nextInt(3)+j;
	}

	public void chooseFromAll()
	{
		Random rand = new Random();
		chosenI = rand.nextInt(9);
		chosenJ = rand.nextInt(9);
	}

	public int getI()
	{
		return chosenI;
	}

	public int getJ()
	{
		return chosenJ;
	}
}

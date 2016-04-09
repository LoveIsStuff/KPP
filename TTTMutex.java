package Code;

import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TTTMutex extends Rectangle{

	private int player;

	private int indexI;
	private int indexJ;

	public TTTMutex(int i, int j)
	{
		indexI = i;
		indexJ = j;

		player = 0;
    	setEffect(new GaussianBlur(5));
    	setHeight(150);
    	setWidth(150);
	}

	public void setPlayer(int newPlayer)
	{
		player = newPlayer;
		setOpacity(0.5);

		switch(newPlayer)
		{
		case 0:
			setFill(null);
			setOpacity(0);
			break;
		case 1:
			setFill(Color.BLUE);
			break;
		case 2:
			setFill(Color.RED);
			break;
		case 3:
			setFill(Color.BLACK);
			break;
		}
	}

	public int getPlayer()
	{
		return player;
	}

	public int getI()
	{
		return indexI;
	}

	public int getJ()
	{
		return indexJ;
	}
}

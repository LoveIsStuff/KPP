package Code;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TTTButton extends StackPane
{
	private Image X; // Image of X
	private Image O; // Image of O
	private ImageView view;
	private Rectangle bg;
	private int player;
	private int indexI;
	private int indexJ;


	public TTTButton(int i, int j) throws Exception
	{
		indexI = i;
		indexJ = j;

		InputStream is = Files.newInputStream(Paths.get("Images/X.png"));
		X = new Image(is);
		is.close();
		is = Files.newInputStream(Paths.get("Images/O.png"));
		O = new Image(is);
		is.close();

		player = 0;

		view = new ImageView();
		bg = new Rectangle(40, 40);
        bg.setOpacity(0);
        bg.setFill(Color.BLUE);
        bg.setEffect(new GaussianBlur(5));
        this.getChildren().addAll(bg, view);

        enable();
	}

	public int getI()
	{
		return indexI;
	}

	public int getJ()
	{
		return indexJ;
	}

	public int getPlayer()
	{
		return player;
	}

	public void setPicture(int number)
	{
		switch(number)
		{
		case 0:
			view.setImage(null);
			player = 0;
			break;
		case 1:
			view.setImage(X); break;
		case 2:
			view.setImage(O); break;
		}
	}

	public void disable()
	{
		bg.setOpacity(0);

		this.setOnMouseEntered(event ->{
        });

		this.setOnMouseExited(event ->{
        });

        this.setOnMouseClicked(event->{
        });
	}

	public void enable()
	{
		this.setOnMouseEntered(event ->{
        	bg.setOpacity(0.3);
        });

        this.setOnMouseExited(event ->{
        	bg.setOpacity(0);
        });

        this.setOnMouseClicked(event->{
        	if(player == 0)
        	{
	        	if(Field.checkEvaliable(indexI, indexJ))
	       		{
		        	player = Logic.getTurn();
		        	setPicture(Logic.getTurn());
		        	if(Logic.test(this) != 0)
						try {
							Field.mute(indexI, indexJ);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        	Field.ShowRightPlace(indexI, indexJ);
			       	Logic.changeTurn();

			       	if(Field.getGameMode() == 1)
						try {
							Field.botChoose();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	        	}
        	}
        });
	}

	public void botClicked() throws Exception
	{
		player = Logic.getTurn();
    	setPicture(Logic.getTurn());
    	if(Logic.test(this) != 0)
    		Field.mute(indexI, indexJ);
    	Field.ShowRightPlace(indexI, indexJ);
    	Logic.changeTurn();
	}
}

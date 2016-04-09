package Code;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Field{

	private static Group root = new Group();
	private static TTTButton[][] buttons = new TTTButton[9][9];
	private static int START_PIXEL_X;
	private static int START_PIXEL_Y;
	private static TTTMutex[][] mutexes;
	private static ImageView rightPlace;
	private static Menu winMenu;
	private static int whereToPlace;
	private static int[] muted;
	private static int gameMode;
	private static Bot bot;
	private static boolean draw;
	private static Timeline oneSecondDelay;

	public Field(int gameMode)
	{
		bot = new Bot();
		Field.gameMode = gameMode;
	}

	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(root, 600, 600, false);

		InputStream is = Files.newInputStream(Paths.get("Images/GameBackground.png"));
		Image image = new Image(is);
		is.close();
        ImageView img = new ImageView(image);
        img.setFitHeight(600);
        img.setFitWidth(600);

        is = Files.newInputStream(Paths.get("Images/RightPlace.png"));
        image = new Image(is);
        rightPlace = new ImageView(image);
        is.close();
        rightPlace.setVisible(false);
        draw = false;

        muted = new int[9];
        for(int i=0; i<9; i++)
        	muted[i] = 0;

        root.getChildren().addAll(img, rightPlace);

        mutexes = new TTTMutex[3][3];
        for(int i=0; i<3; i++)
        {
        	for(int j=0; j<3; j++)
	        {
	        	mutexes[i][j] = new TTTMutex(i, j);
	        	mutexes[i][j].setOpacity(0);
	        	mutexes[i][j].setEffect(new GaussianBlur(5));
	        	mutexes[i][j].setPlayer(0);
	        	mutexes[i][j].setTranslateX(65+j*160);
            	mutexes[i][j].setTranslateY(65+i*160);
	        	root.getChildren().add(mutexes[i][j]);
	        }
        }

        START_PIXEL_Y = 70;
        for(int i=0; i<9; i++)
        {
        	if(i%3==0 && i!=0)
        		START_PIXEL_Y += 10;
        	START_PIXEL_X = 70;
        	for(int j = 0; j < 9; j++)
        	{
        		buttons[i][j] = new TTTButton(i,j);
        		if(j % 3 == 0 && j != 0)
        			START_PIXEL_X += 10;
        		buttons[i][j].setTranslateX(START_PIXEL_X+50*j);
                buttons[i][j].setTranslateY(START_PIXEL_Y+50*i);
                buttons[i][j].setPicture(0);
                root.getChildren().add(buttons[i][j]);
        	}
        }

        Logic.createLogic(buttons, mutexes);

        winMenu = new Menu(600,600);

		primaryStage.setScene(scene);
		primaryStage.show();

		if(gameMode == 2)
		{
			for(int i=0; i<9; i++)
				for(int j=0; j<9; j++)
					buttons[i][j].disable();

			oneSecondDelay = new Timeline(new KeyFrame(Duration.seconds(0.01), new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					try {
						botChoose();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}));
			oneSecondDelay.setCycleCount(Timeline.INDEFINITE);
			oneSecondDelay.play();
		}
	}

	public static void mute(int i, int j) throws Exception
	{
		int mutexNumberI = 0;
		int mutexNumberJ = 0;
		if(i<3)
		{
			if(j<3)
			{
				for(int l=0; l<3; l++)
					for(int k=0; k<3; k++)
						buttons[l][k].disable();
				mutexNumberI = 0;
				mutexNumberJ = 0;
			}
			else if(j<6)
			{
				for(int l=0; l<3; l++)
					for(int k=3; k<6; k++)
						buttons[l][k].disable();
				mutexNumberI = 0;
				mutexNumberJ = 1;
			}
			else if(j<9)
			{
				for(int l=0; l<3; l++)
					for(int k=6; k<9; k++)
						buttons[l][k].disable();
				mutexNumberI = 0;
				mutexNumberJ = 2;
			}
		}
		else if(i<6)
		{
			if(j<3)
			{
				for(int l=3; l<6; l++)
					for(int k=0; k<3; k++)
						buttons[l][k].disable();
				mutexNumberI = 1;
				mutexNumberJ = 0;
			}
			else if(j<6)
			{
				for(int l=3; l<6; l++)
					for(int k=3; k<6; k++)
						buttons[l][k].disable();
				mutexNumberI = 1;
				mutexNumberJ = 1;
			}
			else if(j<9)
			{
				for(int l=3; l<6; l++)
					for(int k=6; k<9; k++)
						buttons[l][k].disable();
				mutexNumberI = 1;
				mutexNumberJ = 2;
			}
		}
		else if(i<9)
		{
			if(j<3)
			{
				for(int l=6; l<9; l++)
					for(int k=0; k<3; k++)
						buttons[l][k].disable();
				mutexNumberI = 2;
				mutexNumberJ = 0;
			}
			else if(j<6)
			{
				for(int l=6; l<9; l++)
					for(int k=3; k<6; k++)
						buttons[l][k].disable();
				mutexNumberI = 2;
				mutexNumberJ = 1;
			}
			else if(j<9)
			{
				for(int l=6; l<9; l++)
					for(int k=6; k<9; k++)
						buttons[l][k].disable();
				mutexNumberI = 2;
				mutexNumberJ = 2;
			}
		}

		mutexes[mutexNumberI][mutexNumberJ].setPlayer(Logic.getTurn());
		muted[mutexNumberI*3+mutexNumberJ] = 1;

		if(Logic.mutexesTest(mutexes[mutexNumberI][mutexNumberJ]))
		{
			oneSecondDelay.stop();
			if(gameMode!=2)
			{
		        winMenu.initializeWinMenu();
				root.getChildren().add(winMenu);
			}
			else
			{
				StartPlay();
			}
		}
	}

	public static void StartPlay() throws Exception
	{
		root.getChildren().removeAll(winMenu);

		draw = false;

		for(int i=0; i<9; i++)
		{
			for(int j=0; j<9; j++)
			{
				buttons[i][j].setPicture(0);
				if(gameMode == 2)
					buttons[i][j].disable();
				else
					buttons[i][j].enable();
			}
		}

		for(int i=0; i<3; i++)
		{
			for(int j=0; j<3; j++)
			{
				mutexes[i][j].setPlayer(0);
			}
		}

		for(int i=0; i<9; i++)
			muted[i] = 0;

		whereToPlace = 0;
		rightPlace.setVisible(false);

		oneSecondDelay.play();

		Logic.resetTurn();
	}

	public static void ShowRightPlace(int i, int j)
	{
		int rightPositionX = 0;
		int rightPositionY = 0;


		if(j%3==0 && i%3==0)
		{
			rightPositionX=65;
			rightPositionY=65;
			if(muted[0]==0)
				whereToPlace = 1;
			else
				whereToPlace = 0;
		}
		else if(j%3==1 && i%3==0)
		{
			rightPositionX=225;
			rightPositionY=65;
			if(muted[1]==0)
				whereToPlace = 2;
			else
				whereToPlace = 0;
		}
		else if(j%3==2 && i%3==0)
		{
			rightPositionX=385;
			rightPositionY=65;
			if(muted[2]==0)
				whereToPlace = 3;
			else
				whereToPlace = 0;
		}
		else if(j%3==0 && i%3==1)
		{
			rightPositionX=65;
			rightPositionY=225;
			if(muted[3]==0)
				whereToPlace = 4;
			else
				whereToPlace = 0;
		}
		else if(j%3==1 && i%3==1)
		{
			rightPositionX=225;
			rightPositionY=225;
			if(muted[4]==0)
				whereToPlace = 5;
			else
				whereToPlace = 0;
		}
		else if(j%3==2 && i%3==1)
		{
			rightPositionX=385;
			rightPositionY=225;
			if(muted[5]==0)
				whereToPlace = 6;
			else
				whereToPlace = 0;
		}
		else if(j%3==0 && i%3==2)
		{
			rightPositionX=65;
			rightPositionY=385;
			if(muted[6]==0)
				whereToPlace = 7;
			else
				whereToPlace = 0;
		}
		else if(j%3==1 && i%3==2)
		{
			rightPositionX=225;
			rightPositionY=385;
			if(muted[7]==0)
				whereToPlace = 8;
			else
				whereToPlace = 0;
		}
		else if(j%3==2 && i%3==2)
		{
			rightPositionX=385;
			rightPositionY=385;
			if(muted[8]==0)
				whereToPlace = 9;
			else
				whereToPlace = 0;
		}

		if(whereToPlace != 0)
		{
		rightPlace.setTranslateX(rightPositionX);
		rightPlace.setTranslateY(rightPositionY);
		rightPlace.setVisible(true);
		}
		else
		{
			rightPlace.setVisible(false);
		}

		if(draw)
		{
			winMenu.initializeWinMenu();
			root.getChildren().add(winMenu);
		}
	}

	public static boolean checkEvaliable(int i, int j)
	{
		boolean flag = false;

		for(int k=0; k<9; k++)
		{
			if(muted[k] == 0)
				flag = true;
		}

		if(!flag && gameMode==2)
		{
			oneSecondDelay.stop();
			draw = true;
			return false;
		}

		flag = false;

		for(int k=0; k<3; k++)
			for(int l=0; l<3; l++)
			{
				if(buttons[k][l].getPlayer()==0)
					flag = true;
			}
		if(!flag)
		{
			muted[0] = 1;
		}

		flag = false;

		for(int k=0; k<3; k++)
			for(int l=3; l<6; l++)
			{
				if(buttons[k][l].getPlayer()==0)
					flag = true;
			}
		if(!flag)
		{
			muted[1] = 1;
		}

		flag = false;
		for(int k=0; k<3; k++)
			for(int l=6; l<9; l++)
			{
				if(buttons[k][l].getPlayer()==0)
					flag = true;
			}
		if(!flag)
		{
			muted[2] = 1;
		}

		flag = false;
		for(int k=3; k<6; k++)
			for(int l=0; l<3; l++)
			{
				if(buttons[k][l].getPlayer()==0)
					flag = true;
			}
		if(!flag)
		{
			muted[3] = 1;
		}

		flag = false;
		for(int k=3; k<6; k++)
			for(int l=3; l<6; l++)
			{
				if(buttons[k][l].getPlayer()==0)
					flag = true;
			}
		if(!flag)
		{
			muted[4] = 1;
		}

		flag = false;
		for(int k=3; k<6; k++)
			for(int l=6; l<9; l++)
			{
				if(buttons[k][l].getPlayer()==0)
					flag = true;
			}
		if(!flag)
		{
			muted[5] = 1;
		}

		flag = false;
		for(int k=6; k<9; k++)
			for(int l=0; l<3; l++)
			{
				if(buttons[k][l].getPlayer()==0)
					flag = true;
			}
		if(!flag)
		{
			muted[6] = 1;
		}

		flag = false;
		for(int k=6; k<9; k++)
			for(int l=3; l<6; l++)
			{
				if(buttons[k][l].getPlayer()==0)
					flag = true;
			}
		if(!flag)
		{
			muted[7] = 1;
		}

		flag = false;
		for(int k=6; k<9; k++)
			for(int l=6; l<9; l++)
			{
				if(buttons[k][l].getPlayer()==0)
					flag = true;
			}
		if(!flag)
		{
			muted[8] = 1;
		}

		flag = false;

		if(i<3)
		{
			if(j<3)
			{
				if(muted[0]!=0)
					return false;
				else if(whereToPlace == 1 || whereToPlace == 0)
					return true;
			}
			else if(j<6)
			{
				if(muted[1]!=0)
					return false;
				else if(whereToPlace == 2 || whereToPlace == 0)
					return true;
			}
			else if(j<9)
			{
				if(muted[2]!=0)
					return false;
				else if(whereToPlace == 3 || whereToPlace == 0)
					return true;
			}
		}
		else if(i<6)
		{
			if(j<3)
			{
				if(muted[3]!=0)
					return false;
				else if(whereToPlace == 4 || whereToPlace == 0)
					return true;
			}
			else if(j<6)
			{
				if(muted[4]!=0)
					return false;
				else if(whereToPlace == 5 || whereToPlace == 0)
					return true;
			}
			else if(j<9)
			{
				if(muted[5]!=0)
					return false;
				else if(whereToPlace == 6 || whereToPlace == 0)
					return true;
			}
		}
		else if(i<9)
		{
			if(j<3)
			{
				if(muted[6]!=0)
					return false;
				else if(whereToPlace == 7 || whereToPlace == 0)
					return true;
			}
			else if(j<6)
			{
				if(muted[7]!=0)
					return false;
				else if(whereToPlace == 8 || whereToPlace == 0)
					return true;
			}
			else if(j<9)
			{
				if(muted[8]!=0)
					return false;
				else if(whereToPlace == 9 || whereToPlace == 0)
					return true;
			}
		}

		return false;
	}

	public static int getGameMode()
	{
		return gameMode;
	}

	public static void botChoose() throws Exception
	{
		int indexI, indexJ;
    	while(true)
    	{
    		if(whereToPlace!=0 && muted[whereToPlace-1]!=0)
    			whereToPlace = 0;
    		switch(whereToPlace)
    		{
    		case 0:
    			bot.chooseFromAll(); break;
    		case 1:
    			bot.choose(0, 0); break;
    		case 2:
    			bot.choose(0, 3); break;
    		case 3:
    			bot.choose(0, 6); break;
    		case 4:
    			bot.choose(3, 0); break;
    		case 5:
    			bot.choose(3, 3); break;
    		case 6:
    			bot.choose(3, 6); break;
    		case 7:
    			bot.choose(6, 0); break;
    		case 8:
    			bot.choose(6, 3); break;
    		case 9:
    			bot.choose(6, 6); break;
    		}
    		indexI = bot.getI();
    		indexJ = bot.getJ();

    		if(checkEvaliable(indexI, indexJ) && buttons[indexI][indexJ].getPlayer()==0)
    			break;
    		if(draw)
    			break;
    	}
    	if(!draw)
    		buttons[indexI][indexJ].botClicked();
    	else
    	{
    		StartPlay();
    	}
	}

	public static boolean checkDraw()
	{
		return draw;
	}
}

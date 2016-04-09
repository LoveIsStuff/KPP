package Code;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.Parent;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu extends Parent{

	private int height;
	private int width;

	public Menu(int height, int width)
	{
		this.height=height;
		this.width=width;
	}

	public void initializeWinMenu()
	{
		Font font = new Font("Impact", 60);
		Rectangle bg = new Rectangle(height,width);
		Text winText = new Text();
		winText.setFont(font);
		winText.setTranslateY(100);
		winText.setEffect(new GaussianBlur(2));

		if(Field.checkDraw())
		{
			winText.setTranslateX(220);
			winText.setText("DRAW!");
			winText.setFill(Color.WHITE);
			bg.setFill(Color.BLACK);
		}
		else if(Logic.getTurn()==1)
		{
			winText.setTranslateX(100);
			winText.setText("First player wins!");
			winText.setFill(Color.AQUA);
			bg.setFill(Color.BLUE);
		}
		else
		{
			winText.setTranslateX(70);
			winText.setText("Second player wins!");
			winText.setFill(Color.AQUA);
			bg.setFill(Color.RED);
		}

		bg.setOpacity(0.7);
		bg.setEffect(new GaussianBlur(100));

		MenuButton replayButton = new MenuButton("Replay");
		replayButton.setTranslateX(200);
		replayButton.setTranslateY(250);
		replayButton.setOnMouseClicked(event ->{
			try {
				Field.StartPlay();
			} catch (Exception e) {
				e.printStackTrace();
			}
			bg.setOpacity(0);
			this.getChildren().remove(winText);
		});

		MenuButton goToMenu = new MenuButton("Menu");
		goToMenu.setTranslateX(200);
		goToMenu.setTranslateY(320);
		goToMenu.setOnMouseClicked(event ->{
			//some code
		});

		this.getChildren().addAll(bg, winText, replayButton);
	}

	public void initializeMainMenu(Stage stage) throws Exception
	{
		InputStream is = Files.newInputStream(Paths.get("Images/MainMenuBackground.png"));
		Image image = new Image(is);
		is.close();
        ImageView img = new ImageView(image);
        img.setFitHeight(height);
        img.setFitWidth(width);

		MenuButton pvpButton = new MenuButton("PVP");
		pvpButton.setTranslateX(200);
		pvpButton.setTranslateY(250);
		pvpButton.setOnMouseClicked(event ->{
			Field field = new Field(0);
			try {
				field.start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		MenuButton soloButton = new MenuButton("Solo");
		soloButton.setTranslateX(200);
		soloButton.setTranslateY(320);
		soloButton.setOnMouseClicked(event ->{
			Field field = new Field(1);
			try {
				field.start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		MenuButton bvb = new MenuButton("Bot vs Bot");
		bvb.setTranslateX(200);
		bvb.setTranslateY(390);
		bvb.setOnMouseClicked(event ->{
			Field field = new Field(2);
			try {
				field.start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		this.getChildren().addAll(img, pvpButton,soloButton,bvb);
	}

	private static class MenuButton extends StackPane
	{
		public MenuButton(String buttonName)
		{
			Rectangle bg = new Rectangle(200,50);
			Text text = new Text(buttonName);
			text.setFill(Color.WHITE);
			text.setFont(new Font(30));
			bg.setFill(Color.BLACK);
			bg.setOpacity(0.6);

			setOnMouseEntered(event->{
				bg.setFill(Color.RED);
			});
			setOnMouseExited(event->{
				bg.setFill(Color.BLACK);
			});

			this.getChildren().addAll(bg, text);
		}
	}
}

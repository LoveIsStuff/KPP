package Code;

public class Logic {

	private static int numberOfTurns;
	private static int turn;
	private static TTTButton[][] buttons;
	private static TTTMutex[][] mutexes;

	public static void createLogic(TTTButton[][] buttons, TTTMutex[][] mutexes)
	{
		Logic.mutexes = mutexes;
		Logic.buttons = buttons;
		turn = 1;
		numberOfTurns = 0;
	}

	public static void changeTurn()
	{
		numberOfTurns++;

		switch(turn)
		{
		case 1:
			turn = 2; break;
		case 2:
			turn = 1; break;
		}
	}

	public static void resetTurn()
	{
		turn = 1;
	}

	public static int getTurn()
	{
		return turn;
	}

	public int getNumberOfTurns()
	{
		return numberOfTurns;
	}

	public static int test(TTTButton pressedButton)
	{
		int pressedI=pressedButton.getI();
		int pressedJ=pressedButton.getJ();
		int count[] = new int[4];
		for(int i=0; i<4; i++)
		{
			count[i]=1;
		}

//		0   1   2
// 		3	x	3
//		2	1	0

		for(int j=1;j<3;j++)
		{
			for(int i=0; i<4; i++)
			{
				if(pressedI < 3) // 1st raw
				{
					if(pressedJ < 3)
					{
						switch(i)
						{
						case 0:
							if(pressedI-j >= 0 && pressedJ-j >=0 && buttons[pressedI-j][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 2 && pressedJ+j <=2 && buttons[pressedI+j][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 1:
							if(pressedI-j >= 0 && buttons[pressedI-j][pressedJ].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 2 && buttons[pressedI+j][pressedJ].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 2:
							if(pressedI-j >= 0 && pressedJ+j <=2 && buttons[pressedI-j][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 2 && pressedJ-j >=0 && buttons[pressedI+j][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 3:
							if(pressedJ+j <= 2 && buttons[pressedI][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedJ-j >=0 && buttons[pressedI][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						}
					}
					else if(pressedJ < 6)
					{
						switch(i)
						{
						case 0:
							if(pressedI-j >= 0 && pressedJ-j >=3 && buttons[pressedI-j][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 2 && pressedJ+j <=5 && buttons[pressedI+j][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 1:
							if(pressedI-j >= 0 && buttons[pressedI-j][pressedJ].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 2 && buttons[pressedI+j][pressedJ].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 2:
							if(pressedI-j >= 0 && pressedJ+j <=5 && buttons[pressedI-j][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 2 && pressedJ-j >=3 && buttons[pressedI+j][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 3:
							if(pressedJ+j <= 5 && buttons[pressedI][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedJ-j >=3 && buttons[pressedI][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						}
					}
					else
					{
						switch(i)
						{
						case 0:
							if(pressedI-j >= 0 && pressedJ-j >=6 && buttons[pressedI-j][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 2 && pressedJ+j <=8 && buttons[pressedI+j][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 1:
							if(pressedI-j >= 0 && buttons[pressedI-j][pressedJ].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 2 && buttons[pressedI+j][pressedJ].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 2:
							if(pressedI-j >= 0 && pressedJ+j <=8 && buttons[pressedI-j][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 2 && pressedJ-j >=6 && buttons[pressedI+j][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 3:
							if(pressedJ+j <= 8 && buttons[pressedI][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedJ-j >=6 && buttons[pressedI][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						}
					}
				}
				else if(pressedI < 6) // 2nd raw
				{
					if(pressedJ < 3)
					{
						switch(i)
						{
						case 0:
							if(pressedI-j >= 3 && pressedJ-j >=0 && buttons[pressedI-j][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 5 && pressedJ+j <=2 && buttons[pressedI+j][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 1:
							if(pressedI-j >= 3 && buttons[pressedI-j][pressedJ].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 5 && buttons[pressedI+j][pressedJ].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 2:
							if(pressedI-j >= 3 && pressedJ+j <=2 && buttons[pressedI-j][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 5 && pressedJ-j >=0 && buttons[pressedI+j][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 3:
							if(pressedJ+j <= 2 && buttons[pressedI][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedJ-j >=0 && buttons[pressedI][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						}
					}
					else if(pressedJ < 6)
					{
						switch(i)
						{
						case 0:
							if(pressedI-j >= 3 && pressedJ-j >=3 && buttons[pressedI-j][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 5 && pressedJ+j <= 5 && buttons[pressedI+j][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 1:
							if(pressedI-j >= 3 && buttons[pressedI-j][pressedJ].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 5 && buttons[pressedI+j][pressedJ].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 2:
							if(pressedI-j >= 3 && pressedJ+j <=5 && buttons[pressedI-j][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 5 && pressedJ-j >=3 && buttons[pressedI+j][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 3:
							if(pressedJ+j <= 5 && buttons[pressedI][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedJ-j >=3 && buttons[pressedI][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						}
					}
					else
					{
						switch(i)
						{
						case 0:
							if(pressedI-j >= 3 && pressedJ-j >=6 && buttons[pressedI-j][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 5 && pressedJ+j <=8 && buttons[pressedI+j][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 1:
							if(pressedI-j >= 3 && buttons[pressedI-j][pressedJ].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 5 && buttons[pressedI+j][pressedJ].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 2:
							if(pressedI-j >= 3 && pressedJ+j <=8 && buttons[pressedI-j][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 5 && pressedJ-j >=6 && buttons[pressedI+j][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 3:
							if(pressedJ+j <= 8 && buttons[pressedI][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedJ-j >=6 && buttons[pressedI][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						}
					}
				}
				else // 3rd raw
				{
					if(pressedJ < 3)
					{
						switch(i)
						{
						case 0:
							if(pressedI-j >= 6 && pressedJ-j >=0 && buttons[pressedI-j][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 8 && pressedJ+j <=2 && buttons[pressedI+j][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 1:
							if(pressedI-j >= 6 && buttons[pressedI-j][pressedJ].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 8 && buttons[pressedI+j][pressedJ].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 2:
							if(pressedI-j >= 6 && pressedJ+j <=2 && buttons[pressedI-j][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 8 && pressedJ-j >=0 && buttons[pressedI+j][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 3:
							if(pressedJ+j <= 2 && buttons[pressedI][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedJ-j >=0 && buttons[pressedI][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						}
					}
					else if(pressedJ < 6)
					{
						switch(i)
						{
						case 0:
							if(pressedI-j >= 6 && pressedJ-j >=3 && buttons[pressedI-j][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 8 && pressedJ+j <=5 && buttons[pressedI+j][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 1:
							if(pressedI-j >= 6 && buttons[pressedI-j][pressedJ].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 8 && buttons[pressedI+j][pressedJ].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 2:
							if(pressedI-j >= 6 && pressedJ+j <=5 && buttons[pressedI-j][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 8 && pressedJ-j >=3 && buttons[pressedI+j][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 3:
							if(pressedJ+j <= 5 && buttons[pressedI][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedJ-j >=3 && buttons[pressedI][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						}
					}
					else
					{
						switch(i)
						{
						case 0:
							if(pressedI-j >= 6 && pressedJ-j >=6 && buttons[pressedI-j][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 8 && pressedJ+j <=8 && buttons[pressedI+j][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 1:
							if(pressedI-j >= 6 && buttons[pressedI-j][pressedJ].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 8 && buttons[pressedI+j][pressedJ].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 2:
							if(pressedI-j >= 6 && pressedJ+j <=8 && buttons[pressedI-j][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedI+j <= 8 && pressedJ-j >=6 && buttons[pressedI+j][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						case 3:
							if(pressedJ+j <= 8 && buttons[pressedI][pressedJ+j].getPlayer()==Logic.getTurn())
								count[i]++;
							if(pressedJ-j >=6 && buttons[pressedI][pressedJ-j].getPlayer()==Logic.getTurn())
								count[i]++;
							break;
						}
					}
				}
			}
		}
		for(int k=0; k<4; k++)
			if(count[k]==3)
				return 1;

		return 0;
	}

	public static boolean mutexesTest(TTTMutex mutex)
	{
		int mutexI = mutex.getI();
		int mutexJ = mutex.getJ();

		int count=1;

		for(int i=0; i<8; i++)
		{
			count = 1;
			for(int j=1; j<3; j++)
			{
				switch(i)
				{
				case 0:
					if(mutexI-j >= 0 && mutexJ-j >= 0 && mutexes[mutexI-j][mutexJ-j].getPlayer()==Logic.getTurn())
						count++;
					if(mutexI+j <= 2 && mutexJ+j <= 2 && mutexes[mutexI+j][mutexJ+j].getPlayer()==Logic.getTurn())
						count++;
					break;
				case 1:
					if(mutexI-j >= 0 && mutexes[mutexI-j][mutexJ].getPlayer()==Logic.getTurn())
						count++;
					if(mutexI+j <= 2 && mutexes[mutexI+j][mutexJ].getPlayer()==Logic.getTurn())
						count++;
					break;
				case 2:
					if(mutexI-j >= 0 && mutexJ+j <= 2 && mutexes[mutexI-j][mutexJ+j].getPlayer()==Logic.getTurn())
						count++;
					if(mutexI+j <= 2 && mutexJ-j >= 0 && mutexes[mutexI+j][mutexJ-j].getPlayer()==Logic.getTurn())
						count++;
					break;
				case 3:
					if(mutexJ+j <= 2 && mutexes[mutexI][mutexJ+j].getPlayer()==Logic.getTurn())
						count++;
					if(mutexJ-j >= 0 && mutexes[mutexI][mutexJ-j].getPlayer()==Logic.getTurn())
						count++;
					break;
				}
			}
			if(count == 3)
			{
				return true;
			}
		}

		return false;
	}
}

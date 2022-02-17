/*
Chase Balmer
10/15/19 | period 5
This is my own work, CJB
Project Assignemnt2/Assig2
This is the main program that uses all of the other clases to create the roulette game
*/

import java.io.*;
import java.util.*;

public class Assig2
{
	public static void main(String[]args) throws IOException
	{
		//Variables
		String playerName = "";
		Scanner reader = new Scanner(System.in);
		double initialBalance = 0;
		double balance = 0;
		double bet;
		int betType;
		String betChoice;
		int valueBet;
		RouletteWheel wheel = new RouletteWheel();
		int winFactor = 0;
		int cont;
		boolean quit = true;
		int count = 0;

		//Open || Create a file that will be used for the player
		System.out.println("Hello user, what is your name?");
		playerName = reader.nextLine();
		String fileName = playerName.concat(".txt");

		File f = new File(fileName);	//Declare a file, but doesnt make a new file

		//If statement will read file, but if it doesnt exist it will create a new one
		if(f.exists())
		{
			//Output users name and balance(reading input from file)
			System.out.println("Wecome " + playerName + "! Welcome to roulette!\n\n");

			Scanner inputFile = new Scanner(f);
			initialBalance = inputFile.nextDouble();
			if(initialBalance < 1)
			{
				System.out.println("You have no money. Unfortunately, you are not allowed to play. Good day.");
				System.exit(1);
			}
			else
			{

			}
			System.out.println("Your current balance is " + initialBalance + "\n\n");
			inputFile.close();

		}
		else
		{
			//Creates a new file
			try
			{
					 f.createNewFile();
				  } catch(Exception e) {
					 e.printStackTrace();
			}
			//Prompt User
			System.out.println("Wecome " + playerName + "! Welcome to roulette!\n\n");

			//Open writable file, write in users name, then ask for the amount of money they would like to use and write that in
			PrintWriter outputFile = new PrintWriter(fileName);
			System.out.println("How much money do you have to play with?");
			initialBalance = reader.nextDouble();
			outputFile.println(initialBalance);
			if(initialBalance < 1)
			{
				System.out.println("You have no money. Unfortunately, you are not allowed to play. Good day.");
				System.exit(1);
			}
			outputFile.close();
		}

		//Must now create a player (store initial balance into balance so later we can calculate the amount changed)
		balance = initialBalance;
		RoulettePlayer p = new RoulettePlayer(playerName, balance);

		//Loop will run until game ends
		while(quit)
		{
				RouletteResult RR = wheel.spinWheel();

				//User has now been prompted and data has been written into the file...
				//We can now start the game
				System.out.println("How much would you like to place for this bet(This may not exceed your overall cash)?");
				bet = reader.nextDouble();
				//If user exceeds his overall he will be re-prompted until he is under his overall
				while(bet > initialBalance || bet < 0)
				{
					System.out.println("Invalid - please try again");
					System.out.println("How much would you like to place for this bet(This may not exceed your overall cash)?");
					bet = reader.nextDouble();
				}
				System.out.println("\nInteresting...What would you like to bet on?");
				System.out.println("1)Value\n2)Color\n3)Parity\n4)Range");
				betType = reader.nextInt();
				//While loop will run to make sure user enters something valid
				while(betType > 4 || betType < 1)
				{
					System.out.println("Invalid, please re-enter your bet type");
					System.out.println("1)Value\n2)Color\n3)Parity\n4)Range");
					betType = reader.nextInt();
				}

				//If statement handles the choice of bet depending on type of bet
				if(betType == 1)
				{
					//Get what value user wants to bet on, and prevent them from enterying something false
					System.out.println("Bet Type: Value");
					System.out.print("Please select a number between 1 and 36(inlusive)");
					valueBet = reader.nextInt();

					//loop runs if users value is out of range
					while(valueBet < 1 || valueBet > 36)
					{
						System.out.print("Not in range, re-enter:");
						System.out.print("Please select a number between 1 and 36(inlusive)");
						valueBet = reader.nextInt();
					}

					//Store int inside string
					betChoice = String.valueOf(valueBet);
					//Create Roulette Bet
					RouletteBet bet1 = new RouletteBet(RBets.Value, betChoice);
					//Check if user won and output spin
					System.out.println("\nResults of the spin:\n" + RR);
					winFactor = testBet(wheel, bet1);
				}
				else if(betType == 2)
				{
					//Get what color user wants to bet on, and prevent them from enterying something false
					System.out.println("Bet Type: Color");
					System.out.print("Please select a color to bet on, Black or Red(First letter must be capitalized)");
					reader.nextLine();
					betChoice = reader.nextLine();

					if(betChoice.equals("Red") || betChoice.equals("Black"))
						System.out.println();
					else
					{
						while(!(betChoice.equals("Red") || betChoice.equals("Black")))
						{
								System.out.print("Not an option, re-enter:");
								System.out.print("Please select a color to bet on, Black or Red(First letter must be capitalized)");
								betChoice = reader.nextLine();
						}
					}
					//Create Roulette Bet
					RouletteBet bet1 = new RouletteBet(RBets.Color, betChoice);
					//Check if user won and output spin
					System.out.println("\nResults of the spin:\n" + RR);
					winFactor = testBet(wheel, bet1);

				}
				else if(betType == 3)
				{
					//Get what parity user wants to bet on, and prevent them from enterying something false
					System.out.println("Bet Type: Parity");
					System.out.print("Please select a parity to bet on, Even or Odd(First letter must be capitalized)");
					reader.nextLine();
					betChoice = reader.nextLine();

					if(betChoice.equals("Even") || betChoice.equals("Odd"))
						System.out.println();
					else
					{
						while(!(betChoice.equals("Even")|| (betChoice.equals("Odd"))))
						{
							System.out.print("Not an option, re-enter:");
							System.out.print("Please select a parity to bet on, Even or Odd(First letter must be capitalized)");
							betChoice = reader.nextLine();
						}
					}

					//Create Roulette Bet
					RouletteBet bet1 = new RouletteBet(RBets.Parity, betChoice);
					//Check if user won and output spin
					System.out.println("\nResults of the spin:\n" + RR);
					winFactor = testBet(wheel, bet1);
				}
				else if(betType == 4)
				{
					//Get what range user wants to bet on, and prevent them from enterying something false
					System.out.println("Bet Type: Range");
					System.out.print("Please select a range to bet on, Low(1-18) or High(19-36)(inclusive)(First letter must be capitalized)");
					reader.nextLine();
					betChoice = reader.nextLine();

					if(betChoice.equals("Low") || betChoice.equals("High"))
						System.out.println();
					else
					{
						while(!(betChoice.equals("Low")|| betChoice.equals("High")))
						{
							System.out.print("Not an option, re-enter:");
							System.out.print("Please select a range to bet on, Low(1-18) or High(19-36)(inclusive)(irst letter must be capitalized)");
							betChoice = reader.nextLine();
						}
					}

					//Create Roulette Bet
					RouletteBet bet1 = new RouletteBet(RBets.Range, betChoice);
					//Check if user won and output spin
					System.out.println("\nResults of the spin:\n" + RR);
					winFactor = testBet(wheel, bet1);
				}
				else
				{
					System.out.println("ERROR");
					winFactor = 0;
				}

				//We now have the result of the spin and bets, and will now output and calculate winnings
				if(winFactor == 0)
				{
					System.out.println("\nYou have lost your money");
					p.updateMoney((bet * -1));
					System.out.println("Your balance is now " + p.getMoney());

				}
				else if(winFactor == 1)
				{
					System.out.println("\nYou have increased your money");
					p.updateMoney((bet));
					System.out.println("Your balance is now " + p.getMoney());
				}
				else if(winFactor == 35)
				{
					System.out.println("\nYou got the jackpot!");
					p.updateMoney((bet * 35));
					System.out.println("Your balance is now " + p.getMoney());
				}

				count++;

				if(p.getMoney() == 0.0)
				{
					System.out.println("You have no money. Unfortunately, you are not allowed to play again.");
					quit = false;
				}
				else
				{
					System.out.println("Would you like to keep going?(0 for no, any other integer will be taken as yes)");
					cont = reader.nextInt();
					if(cont == 0)
						quit = false;
				}



		}//Bet Loop end


		//Output Final Data and write back to file
		System.out.println("\n\nThank you for playing!");
		System.out.println("You have played " + count + " round(s)");
		System.out.println("You started with a balance of " + initialBalance);
		System.out.println("You ended with a balance of " + p.getMoney());
		if((p.getMoney() - initialBalance) > 0)
			System.out.println("Your winnings were " + (p.getMoney() - initialBalance));
		else if((p.getMoney() - initialBalance) < 0)
			System.out.println("Your losings were " + (p.getMoney() - initialBalance));
		else if((p.getMoney() - initialBalance) == 0)
			System.out.println("You gained nor loss money");

		//Write back to file
		PrintWriter outputFile = new PrintWriter(fileName);
		outputFile.println(p.getMoney());
		outputFile.close();



	}//Main End

		//Method will return different values based on the type of win
		static int testBet(RouletteWheel wheel, RouletteBet bet1)
		{
			System.out.println("Bet: " + bet1);

			int res = wheel.checkBet(bet1);
			if (res == 0)
				System.out.println("You have lost, better luck next time");
			else if (res == 1)
				System.out.println("Nice job, you have won! Playoff is 1x");
			else if (res == 35)
				System.out.println("Winner winner chickin dinner! Payoff is a whopping 35x");

			//Return number that will be used to calculate money
			return res;
		}


		//OUTPUT
		/*
		Hello user, what is your name?
		c
		Wecome c! Welcome to roulette!


		Your current balance is 10.0


		How much would you like to place for this bet(This may not exceed your overall cash)?
		2

		Interesting...What would you like to bet on?
		1)Value
		2)Color
		3)Parity
		4)Range
		4
		Bet Type: Range
		Please select a range to bet on, Low(1-18) or High(19-36)(inclusive)(First letter must be capitalized)Low


		Results of the spin:
		Value:15  Color:Black  Range:Low  Parity:Odd
		Bet: Type:Range  Value:Low
		Nice job, you have won! Playoff is 1x

		You have increased your money
		Your balance is now 12.0
		Would you like to keep going?(0 for no, any other integer will be taken as yes)
		1
		How much would you like to place for this bet(This may not exceed your overall cash)?
		2000
		Invalid - please try again
		How much would you like to place for this bet(This may not exceed your overall cash)?
		3

		Interesting...What would you like to bet on?
		1)Value
		2)Color
		3)Parity
		4)Range
		3
		Bet Type: Parity
		Please select a parity to bet on, Even or Odd(First letter must be capitalized)Odd


		Results of the spin:
		Value:10  Color:Black  Range:Low  Parity:Even
		Bet: Type:Parity  Value:Odd
		You have lost, better luck next time

		You have lost your money
		Your balance is now 9.0
		Would you like to keep going?(0 for no, any other integer will be taken as yes)
		1
		How much would you like to place for this bet(This may not exceed your overall cash)?
		3

		Interesting...What would you like to bet on?
		1)Value
		2)Color
		3)Parity
		4)Range
		2
		Bet Type: Color
		Please select a color to bet on, Black or Red(First letter must be capitalized)Black


		Results of the spin:
		Value:36  Color:Red  Range:High  Parity:Even
		Bet: Type:Color  Value:Black
		You have lost, better luck next time

		You have lost your money
		Your balance is now 6.0
		Would you like to keep going?(0 for no, any other integer will be taken as yes)
		1
		How much would you like to place for this bet(This may not exceed your overall cash)?
		6

		Interesting...What would you like to bet on?
		1)Value
		2)Color
		3)Parity
		4)Range
		1
		Bet Type: Value
		Please select a number between 1 and 36(inlusive)1

		Results of the spin:
		Value:31  Color:Black  Range:High  Parity:Odd
		Bet: Type:Value  Value:1
		You have lost, better luck next time

		You have lost your money
		Your balance is now 0.0
		You have no money. Unfortunately, you are not allowed to play again.


		Thank you for playing!
		You have played 4 round(s)
		You started with a balance of 10.0
		You ended with a balance of 0.0
		Your losings were -10.0
Press any key to continue . . .
		*/

}//Program end
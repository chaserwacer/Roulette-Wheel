/*
Chase Balmer
10/15/19 | period 5
This is my own work, CJB
Project Assignemnt2/RouletteWheel
This is 1 of the classes in assignment 2. This class handles the roulette wheel.
*/

import java.util.Random;

public class RouletteWheel
{
    //Variables
    private RColors c;
    private RRanges r;
    private RParities p;
    private int finalDice;

    //Constructors
    public RouletteWheel()
    {

    }


    //Methods
    //Spin the wheel method, and output a rouletteResult object with the rolls data
    public RouletteResult spinWheel()
    {
        //Variables
        Random ran = new Random();
        String color;
        String range;
        String parity;


        //Spin wheel by generating number and store
        finalDice = ran.nextInt(37);

        //Return a roulette result and calculate
        //Gigantic if statement to supply the numbers for the roulette result constructor
        //Enum valueOf equations are used to convert strings to be the correct data type to work in their designated date tye
        if (finalDice == 0)
        {
			//If the roll was zero there are set values as it is always a loss
			color = "Green";
			c = RColors.valueOf(color);
			range = "None";
			r = RRanges.valueOf(range);
			parity = "None";
			p = RParities.valueOf(parity);

		}
		else
		{
			//Due to the system of colors being different across different numbers,
			//This nested if/else handles what the color, parity, and range for roullette result class variabes should be assigned too
			if((finalDice >= 1 && finalDice <= 10) || (finalDice >= 19 && finalDice <= 28))
			{
				if(finalDice % 2 == 0)
				{
					color = "Black";
					c = RColors.valueOf(color);
					parity = "Even";
					p = RParities.valueOf(parity);
				}
				else
				{
					color = "Red";
					c = RColors.valueOf(color);
					parity = "Odd";
					p = RParities.valueOf(parity);
				}

				if(finalDice <= 18)
				{
					range = "Low";
					r = RRanges.valueOf(range);
				}
				else
				{
					range = "High";
					r = RRanges.valueOf(range);
				}

			}
			else
			{
				if(finalDice % 2 == 0)
				{
					color = "Red";
					c = RColors.valueOf(color);
					parity = "Even";
					p = RParities.valueOf(parity);
				}
				else
				{
					color = "Black";
					c = RColors.valueOf(color);
					parity = "Odd";
					p = RParities.valueOf(parity);
				}

				if(finalDice <= 18)
				{
					range = "Low";
					r = RRanges.valueOf(range);
				}
				else
				{
					range = "High";
					r = RRanges.valueOf(range);
				}

			}
		}//Overall if end
        return new RouletteResult(c,r,p,finalDice);


    }//Roll end

	//Determine if user has one and to what effect
    public int checkBet(RouletteBet b)
    {
		//Variables
		int winOrLoss = 5;
		RColors c1;
		RRanges r1;
        RParities p1;
		RouletteResult betCompare;
		String diceLand;


		//Get what type of bet and what value the user inputted
		RBets betType = b.getBetType();
		String betValue = b.getBetValue();

		//If statement to determine win or loss
		if(betType == RBets.Color)
		{
			c1 = RColors.valueOf(betValue);
			if(c1.equals(c))
				winOrLoss = 1;
			else
				winOrLoss = 0;
		}
		else if(betType == RBets.Range)
		{
			r1 = RRanges.valueOf(betValue);
			if(r1.equals(r))
				winOrLoss = 1;
			else
				winOrLoss = 0;

		}
		else if(betType == RBets.Value)
		{
			diceLand = String.valueOf(finalDice);
			if(betValue.equals(diceLand))
				winOrLoss = 35;
			else
				winOrLoss = 0;
		}
		else if(betType == RBets.Parity)
		{
			p1 = RParities.valueOf(betValue);
			if(p1.equals(p))
				winOrLoss = 1;
			else
				winOrLoss = 0;
		}


		//Return value of if they lost, won with minimal reward, or total reward
		return winOrLoss;
	}//Check bet end











}//Class End


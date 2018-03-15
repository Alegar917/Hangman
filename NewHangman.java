import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class NewHangman {
	public static String randomPhrase(){
		//put your own phrases to use in this list
		String phraseList[]={"It is raining cats and dogs","Froot Loops","Andriod Rocks","You gone learn today","Everything is a boomerang if you throw it upwards","if you are waiting for the waiter are you not the waiter","CS is fun","this is gonna be a good year","Got milk","Welcome to Hangman"};
		Random rand=new Random();
		int num=rand.nextInt(phraseList.length);
		String phrase=phraseList[num];
		return phrase;
	}
	public static StringBuilder generateHiddenPhrase(String phrase){
			StringBuilder hiddenPhrase= new StringBuilder(phrase);
			int i=0;
			while (i<phrase.length()){
				char ch=phrase.charAt(i);
				if (ch!=' '){
					hiddenPhrase.setCharAt(i,'*');
				}
				i++;	
			}
			return	hiddenPhrase;
		}
						
	public static StringBuilder	 processGuess(String phrase,StringBuilder hiddenPhrase,String guess){
		int x=0;
		int count=0;
		int attempts=8;
		char user=guess.charAt(0);
		while (x<phrase.length()){
			char ch=phrase.charAt(x);
			user=guess.charAt(0);

			if ((ch==user)||(ch==user-32)||(ch==user+32)){
				hiddenPhrase.setCharAt(x,ch);
				}	
			x++;	
			}
		x=0;
		return hiddenPhrase;
		}

	public static void main(String [] args) {
		ArrayList gList= new ArrayList();
		System.out.println("Welcome to Hangman!");
		System.out.println("You have 8 chances to guess the phrase");
		System.out.println("You can only type one letter or digit to guess the phrase");
		System.out.print("Are you ready to play (y/n):");
		Scanner scanner= new Scanner(System.in);
		String result=scanner.nextLine();
		int wins=0;
		int games=0;
		int totalAt=0;

		do{
		while (result.equals("y")||result.equals("Y")){
			String phrase=randomPhrase();
			StringBuilder hiddenPhrase=generateHiddenPhrase(phrase);
			System.out.println("Welcome to Hangman!");
			System.out.println(hiddenPhrase);

			int x=0;
			int count=0;
			int attempts=8;
			System.out.println("Guesses left:"+attempts);

			while (count<attempts){
				System.out.print("Guess a Character:");
				String guess=scanner.nextLine();
				System.out.println();
				char user=guess.charAt(0);

			hiddenPhrase=processGuess(phrase,hiddenPhrase,guess);

			String hiddenP=hiddenPhrase.toString();
			if (!(hiddenP.contains(guess.toUpperCase())||(hiddenP.contains(guess.toLowerCase())))){
				System.out.println(guess+" is not in the phrase ");
				count++;
				}
				System.out.println(hiddenPhrase);
				System.out.println("Guesses left:"+(attempts-count));
			hiddenP=hiddenPhrase.toString();
			if ((count>=attempts)&&(hiddenP.contains("*"))){
				System.out.println();
				System.out.println("Game Over! You Lose");
				System.out.println("The phrase was:"+phrase);
				break;
			}
			if (!(hiddenP.contains("*"))){
				wins=wins+1;
				totalAt=totalAt+count;
				System.out.println();
				System.out.println("Congrats You Won!");
				break;
			}
				
				}
		games=games+1;
		System.out.print("Are you ready to play again (y/n):");
		result=scanner.nextLine();
		}
	}while(!(result.equals("n")||result.equals("N")));
	if (result.equals("n")||result.equals("N")){
		try{
		double avgGuess=totalAt/wins;
		System.out.println("Player has won"+" "+wins+" "+"out of"+" "+games+" "+"games with an average of"+" "+avgGuess+" "+"guesses in wins ");
		}
		catch(ArithmeticException e){
			double avgGuess=0;
			System.out.println("Player has won"+" "+wins+" "+"out of"+" "+games+" "+"games with an average of"+" "+avgGuess+" "+"guesses in wins ");
		}
	}
}
}

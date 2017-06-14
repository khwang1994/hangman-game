import java.util.*;
import java.io.*;

public class Hangman {

	WordList words;
	String magic_word;
	int num_wrong_guess;
	int num_rounds;
	int num_wins;

	final static int guess_limit = 6;

	Hangman(){
		words = null;
		magic_word = null;
	}

	Hangman(WordList words){
		this.words = words;
		magic_word = this.words.getMagicWord();
		num_rounds = 1;
		num_wins = 0;
	}


	public static void main(String[] args) throws FileNotFoundException {
		// Create WordList object using input dictionary File
		WordList list = new WordList(new File(args[0]));
		// Create Hangman object using WordList object
		Hangman game = new Hangman(list);

	}

	public void run(){
		System.out.println("Let's play Hangman!");

		while(num_wrong_guess <= guess_limit){
			System.out.println("Guess a letter: ");
			Scanner in = new Scanner(System.in);
			char guess = in.next().toLower();
		}

	}
}

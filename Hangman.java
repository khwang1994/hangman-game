import java.util.*;
import java.io.*;

public class Hangman {

	WordList words;
	char[] magic; 
	int magic_len;
	int num_wrong_guess;
	int num_rounds;
	int num_wins;

	final static int guess_limit = 6;

	Hangman(){
		words = null;
	}

	Hangman(WordList words){
		this.words = words;
		num_rounds = 1;
		num_wins = 0;
		magicWord();
	}

	// Generates the magic word from the WordList, then converts the word
	// to an easily accessible array and store as Hangman object's 
	// char magic[]
	void magicWord(){
		String word = this.words.getMagicWord();
		this.magic_len = word.length();
		this.magic = new char[magic_len];

		// Iterates through word and stores in char magic[]
		for(int i = 0; i < word.length(); i++){
			magic[i] = word.charAt(i);
		}

	}


	public static void main(String[] args) throws IOException {
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
			String guess = in.next().toLowerCase();

		}

	}
}

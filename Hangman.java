import java.util.*;
import java.io.*;

public class Hangman {

	static WordList words; // Dictionary used for this game
	static char[] gameState; // State of game, initialized to array of "_"
	private static char[] magic; // The magic word for round, stored as an array
	private static String magic_str; // The magic word, in String form.
	private static int[] magic_freq; // Stores the frequency of each letter in magic word
	static int magic_len; // Length of the magic word
	static int num_wrong;
	static int num_rounds;
	static int num_wins;
	static int letter_count;
	static boolean[] guess_cache;

	final static int GUESS_LIMIT = 6;
	final static int INVALID_GUESS = -1;
	final static int WIN = 1;

	// Default constructor 
	public Hangman(){
		words = null;
	}

	public Hangman(WordList words){
		this.words = words;
		num_rounds = 1;
		num_wins = 0;
		num_wrong = 0;
		letter_count = 0;
		magicWord();
		// Initalize state with '_'
		gameState = new char[magic_len];
		Arrays.fill(gameState, '_');
		// Initalize guess_cache. 
		guess_cache = new boolean[26];
		Arrays.fill(guess_cache, false);
		initMagicFreq();

	}

	// Generates the magic word from the WordList, then converts the word
	// to an easily accessible array and store as Hangman object's 
	// char magic[]
	public static void magicWord(){
		magic_str = words.getMagicWord().toLowerCase();
		magic_len = magic_str.length();
		magic = new char[magic_len];

		// Iterates through word and stores in char magic[]
		for(int i = 0; i < magic_len; i++){
			magic[i] = magic_str.charAt(i);
		}

	}

	public static int checkGuess(String guess){
		guess.toLowerCase();
		char c = guess.charAt(0);
		if(guess.length() > 1 || c < 97 || c > 122){
			System.out.print("\nYou must guess a letter! Try again: ");
			return INVALID_GUESS;
		}

		else if(guess_cache[c - 97]){
			System.out.print("\nYou already tried this letter! Try again: ");
			guess_cache[c - 97] = true;
			return INVALID_GUESS;
		}
		// BAD GUESS
		else if(magic_freq[c - 97] == 0){
			num_wrong++;
			System.out.println("\n'" + c + "' is not in the magic word :(\n"
				+ "You have " + (GUESS_LIMIT - num_wrong) + " guesses left.");
			printState();
			}
		// GOOD GUESS
		else{
			for(int i = 0; i < magic_len; i++){
				if(magic[i] == c){
					gameState[i] = c;
					letter_count++;
				}
			}
			System.out.println("\n'" + c + "' is in the magic word!!! :D");
			printState();

			if(letter_count == magic_len){
				return WIN;
			}
		}
		return 0;

	}

	void newRound(){
		num_rounds++;
		num_wrong = 0;
		letter_count = 0;
		magicWord();
		// Initalize state with '_'
		gameState = new char[magic_len];
		Arrays.fill(gameState, '_');
		// Initalize guess_cache. 
		guess_cache = new boolean[26];
		Arrays.fill(guess_cache, false);
		initMagicFreq();
	}

	void initMagicFreq(){
		magic_freq = new int[26];
		Arrays.fill(magic_freq, 0);
		for(int i = 0; i < magic_len; i++){
			magic_freq[magic[i] - 97]++;
		}
	}

	public static void printState(){
		for(int i = 0; i < magic_len; i++){
			System.out.print(gameState[i] + " ");
		}
		System.out.println("\n");
	}

	public static String printMagic(){
		return magic_str;
	}


	public static void main(String[] args) throws IOException {
		// Create WordList object using input dictionary File
		WordList list = new WordList(new File(args[0]));
		// Create Hangman object using WordList object
		Hangman game = new Hangman(list);

		run(game);
	}

	public static void run(Hangman game){
		System.out.println("*****\nLET'S PLAY HANGMAN!!!\n*****\n\n");

		System.out.println("*Round " + num_rounds + " *");
		game.printState();
		Scanner in = new Scanner(System.in);
		int gameStatus = INVALID_GUESS;

		while(num_wrong < GUESS_LIMIT && gameStatus != WIN){
			System.out.print("Guess a letter: ");

			String guess = in.next();
			gameStatus = checkGuess(guess);

			while(gameStatus == INVALID_GUESS){
				gameStatus = checkGuess(in.next());
			}

		}

		if(gameStatus == WIN){
			System.out.println("CONGRATULATIONS! YOU WIN!");
			game.num_wins++;
		}
		else{
			System.out.println("Sorry, you lose. Hangman is dead. You suck.");
			System.out.println("The word was: " + game.printMagic());
		}

		System.out.println("TOTAL WINS: " + num_wins + 
			"\nTOTAL LOSSES: " + (num_rounds - num_wins));
		System.out.print("Do you want to play again? Type 'Y' for Yes and 'N' for no: ");
		String y_or_n = in.next().toLowerCase();
		switch(y_or_n){
			case "y":
				System.out.println("Setting up new round...\n\n");
				game.newRound();
				run(game);
				break;
			case "n":
				System.out.println("Goodbye!");
				return;
		}

	}
}

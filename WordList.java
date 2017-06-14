import java.util.*;
import java.io.*;


public class WordList {
	ArrayList<String> words;
	int num_words;

	WordList(){
		words = new ArrayList<String>();
		num_words = 0;
	}

	WordList(File f) throws IOException{
		words = new ArrayList<String>();
		Scanner in = new Scanner(f);
		while(in.hasNext()){
			words.add(in.next());
		}

		num_words = words.size();
	}

	int getSize(){
		return this.num_words;
	}

	String getMagicWord(){
		Random r = new Random();
		return words.get(r.nextInt(this.num_words));
	}
}
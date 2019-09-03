/*
 * RockBand.java
 *
 */

package assign3;
import cos126.StdDraw;
import cos126.StdAudio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class RockBand {


    static String guitarBassKeyboard ="`1234567890-=qwertyuiop[]\\asdfghjkl;'";
    static String pianoKeyboard = "~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:\"";
    static String drumKeyboard = "ZXCVBNM<>?zxcvbnm,.";
    static int index;
    public static void main(String[] args) {
	Guitar guitar = new Guitar(37);
	Bass bass = new Bass(37);
	Piano piano = new Piano(37);
	Drum drum = new Drum(19);
	boolean lowmode = false;
	
	if(args != null && args.length == 2 && args[0].equals("-play_from_file")){
	    System.out.println(args[0]+" "+args[1]);
	    int speederino = 100;
	    File song = new File(args[1]);
	    Scanner scannyboy = null;

	    try{
		scannyboy = new Scanner(song);
	    }catch(Exception e){
		e.printStackTrace();
		System.exit(0);
	    }

	    scannyboy.useDelimiter(" |\\n");

	    while(scannyboy.hasNext()){
		index = -1;
		String word = scannyboy.next();

		if(word.equals("/")){
		    restyBoi(guitar, bass, piano, drum, speederino);

		}
		int skiperino = 0;
		for(int i = 0; i<word.length(); i++){
		    char c1 = word.charAt(i);

		    if(i < word.length()-1){
			char c2 = word.charAt(i+1);

			if(c1 == '@' && c2 == '@'){
			    System.out.println(word.substring(i+2));
			    skiperino = word.length();
			}else if(c1 == '#' && c2 == '#'){
			    boolean end = false;
			    for(int j = i+2; j<word.length(); j++){
				if(word.charAt(j) < 57 || word.charAt(j) < 48){
				    end = true;
				    speederino = Integer.parseInt(word.substring(i+2, word.length()-1));
				}
				if(!end){
				    skiperino = (word.length()-i)+1;
				}
			    }
			}else if(c1 == 'L' && c2 == 'L'){
			    lowmode = !lowmode;
			    skiperino = 2;
			}
		    }
		    if(c1 == '/'){
			lowmode = !lowmode;
			skiperino = 1;
		    }

		    if(skiperino == 0){
			if(-1 != guitarBassKeyboard.indexOf(c1)){
			    index = guitarBassKeyboard.indexOf(c1);
			    if(lowmode){
				bass.playNote(index);
			    }else{
				guitar.playNote(index);
			    }
			}else if(-1 != pianoKeyboard.indexOf(c1)){
			    index = pianoKeyboard.indexOf(c1);
			    piano.playNote(index);
			}else if(-1 != drumKeyboard.indexOf(c1)){
			    index = drumKeyboard.indexOf(c1);
			    drum.playNote(index);
			}
		    }else{
			skiperino--;
		    }
		    restyBoi(guitar, bass, piano, drum, speederino);

		}
	    }
	}else{
	    while(true){
		if(StdDraw.hasNextKeyTyped()){
		    char key = StdDraw.nextKeyTyped();
		    index = -1;
		    if(key == '\n'){
			if(lowmode){
			    lowmode = false;
			}else{
			    lowmode = true;
			}
		    }
		    if(-1 != guitarBassKeyboard.indexOf(key)){
			index = guitarBassKeyboard.indexOf(key);
			if(lowmode){
			    bass.playNote(index);
			}else{
			    guitar.playNote(index);
			}
		    }else if(-1 != pianoKeyboard.indexOf(key)){
			index = pianoKeyboard.indexOf(key);
			piano.playNote(index);
		    }else if(-1 != drumKeyboard.indexOf(key)){
			index = drumKeyboard.indexOf(key);
			drum.playNote(index);
		    }
		    
		    
		}
		double ringerino = guitar.ringNotes()+bass.ringNotes()+drum.ringNotes()+piano.ringNotes();
		StdAudio.play(ringerino);
	    }
	}
    }
    private static void restyBoi(Guitar guitar, Bass bass, Piano piano, Drum drum, int speed){
	for(int i=0; i < speed; i++){
	    double sample = guitar.ringNotes()+bass.ringNotes()+drum.ringNotes()+piano.ringNotes();
	    StdAudio.play(sample);
	}
    }
}

package components.fields;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.function.Consumer;

public class StrictTextField extends TextField{
	private static final long serialVersionUID = -7105058236421731237L;
	private ArrayList<Character> include_characters; 
	private ArrayList<Integer> include_keys;
	private int int_code, column, characterLimit;
	private char char_code;
	private boolean accept, accept_char, accept_key, released, isNumbersIncluded, isSmallLettersIncluded, isCapitalLettersIncluded, isSpecialCharactersIncluded;
	private String str;
	
	public StrictTextField(String txt) { 
		super(txt);
		strict_field(txt);
	}
	public void setCharacterLimit(int characterLimit) {
		this.characterLimit = characterLimit;
	}
	public int getCharacterLimit() {
		return characterLimit;
	}
	public boolean isNumbersIncluded() {
		return isNumbersIncluded;
	}
	public boolean isSmallLettersIncluded() {
		return isSmallLettersIncluded;
	}
	public boolean isCapitalLettersIncluded() {
		return isCapitalLettersIncluded;
	}
	public boolean isLettersIncluded() {
		return isSmallLettersIncluded && isCapitalLettersIncluded;
	}
	public boolean isSpecialCharactersIncluded() {
		return isSpecialCharactersIncluded;
	}
	public void includeCharacter(char character) {
		include_characters.add(character);
	}
	public void includeKeys(int keyCode) {
		include_keys.add(keyCode);
	}
	public void includeNumbers(boolean isNumbersIncluded) {
		if(isNumbersIncluded) {
			addCharacters('0', 10);
		}
		else {
			removeCharacters('0', 10);
		}
		this.isNumbersIncluded = isNumbersIncluded;
	}
	public void includeSmallLetters(boolean isSmallLettersIncluded) {
		if(isSmallLettersIncluded) {
			addCharacters('a', 26);
		}
		else {
			removeCharacters('a', 26);
		}
		this.isSmallLettersIncluded = isSmallLettersIncluded;
	}
	public void includeCapitalLetters(boolean isCapitalLettersIncluded) {
		if(isCapitalLettersIncluded) {
			addCharacters('A', 26);
		}
		else {
			removeCharacters('A', 26);
		}
		this.isCapitalLettersIncluded = isCapitalLettersIncluded;
	}
	public void includeLetters(boolean isLettersIncluded) {
		includeSmallLetters(isLettersIncluded);
		includeCapitalLetters(isLettersIncluded);
	}
	public void includeSpecialCharacters(boolean isSpecialCharactersIncluded) {
		if(isSpecialCharactersIncluded) {
			addCharacters(' ', 16);
			addCharacters(':', 7);
			addCharacters('[', 6);
			addCharacters('{', 4);
		}
		else {
			removeCharacters(' ', 16);
			removeCharacters(':', 7);
			removeCharacters('[', 6);
			removeCharacters('{', 4);
		}
		this.isSpecialCharactersIncluded = isSpecialCharactersIncluded;
	}
	public void exceptChars(char character) {
		include_characters.remove(character);
	}
	public void exceptKeys(int keyCode) {
		include_keys.remove(keyCode);
	}
	
	private void strict_field(String txt) {
		str = txt; 
		column = 0;
		released = true;
		
		include_characters = new ArrayList<Character>();
		
		include_keys = new ArrayList<Integer>();
		include_keys.add(KeyEvent.VK_BACK_SPACE);
		include_keys.add(KeyEvent.VK_DELETE);
		include_keys.add(KeyEvent.VK_LEFT);
		include_keys.add(KeyEvent.VK_RIGHT);
		
		includeLetters(true);
		setCharacterLimit(16);
		
		addKeyListener(new KeyAdapter() {
			private boolean isLimitReached;
			private Consumer<Integer> consumer_keyCodes = new Consumer<Integer>() {
				@Override
				public void accept(Integer keyCode) {
					if(int_code==keyCode.intValue()) {
						accept_key = true;
					}
				}
			};
			private Consumer<Character> consumer_characters = new Consumer<Character>() {
				@Override
				public void accept(Character character) {
					if(char_code==character.charValue()) {
						accept_char = true;
					}
				}
			};
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_SHIFT || e.getKeyCode()==KeyEvent.VK_ALT) {
					return;
				}
				
				if(released) {
					int_code = e.getKeyCode();
					char_code = e.getKeyChar();
					
					include_keys.forEach(consumer_keyCodes);
					include_characters.forEach(consumer_characters);
					
					accept = accept_char || accept_key;
					released = false;
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				isLimitReached = getText().length() > getCharacterLimit();
				
				if(isLimitReached) {
					released = false;
					accept = false;
				}
				
				if(!released) {
					if(accept) {
						str = getText();
						column = getCaretPosition();
						
						setText(str);
						
						if(column > str.length()) {
							column = str.length();
						}
						setCaretPosition(column);
					}
					else{
						setText(str);
						Toolkit.getDefaultToolkit().beep();
						setCaretPosition(column);
					}
					
					released = true;
					accept_char = false;
					accept_key = false;
					accept = false;
				}
			}
		});
	}
	private void addCharacters(char c, int limit) {
		for(int n=0; n<limit; n++) {
			include_characters.add((char)(c + n));
		}
	}
	private void removeCharacters(char c, int limit) {
		for(int n=0; n<limit; n++) {
			include_characters.remove((Object)((char)(c + n)));
		}
	}
}

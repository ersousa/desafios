package idwall.desafio.string;

import java.util.regex.Pattern;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {
	
	private static final String linebreak = "\n";

    /**
     * Should format as described in the challenge
     *
     * @param text
     * @return
     */
    @Override
    public String format(String text) {
    	try {
    		 StringBuilder b = new StringBuilder();
    	 	    for (String line : text.split(Pattern.quote(linebreak))) {
    	 	        b.append(wrapLine(line, getLimit()));
    	 	    }
    	 	    return b.toString();
		} catch (Exception e) {
			throw new UnsupportedOperationException();
		}
    	
    }
    
	private static String wrapLine(String line, int lineLength) {
	    if (line.length() == 0) return linebreak;
	    if (line.length() <= lineLength) return line + linebreak;
	    String[] words = line.split(" ");
	    StringBuilder allLines = new StringBuilder();
	    StringBuilder trimmedLine = new StringBuilder();
	    for (String word : words) {
	        if (trimmedLine.length() + 1 + word.length() <= lineLength) {
	            trimmedLine.append(word).append(" ");
	        } else {
	            allLines.append(trimmedLine).append(linebreak);
	            trimmedLine = new StringBuilder();
	            trimmedLine.append(word).append(" ");
	        }
	    }
	    if (trimmedLine.length() > 0) {
	        allLines.append(trimmedLine);
	    }
	    allLines.append(linebreak);
	    return allLines.toString();
	}
}

/*
* Nome: idwall.desafio.string
* Compilador: 1.5.0_16
* Propósito: ${proposito_padrao_cabecalho}
* Data da Criação: 04/05/2018
*/
package idwall.desafio.string;

import java.util.regex.Pattern;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

	public IdwallFormatter(Integer limit) {
		super(limit);
	}

	private static final String linebreak = "\n";

    /**
     *
	 * Format the received string and justify or not the text
	 * according to the boolean parameter received.
     *
     * @param text, isJustify
     * @return String
     */
    @Override
	public String format(String text, Boolean isJustify) {
		try {
			StringBuilder b = new StringBuilder();
			String justifiedText;
			for (String line : text.split(Pattern.quote(linebreak))) {
				b.append(wrapLine(line, getLimit()));
			}
			if (isJustify) {
				justifiedText = justify(b.toString());
				return justifiedText;
			}
			return b.toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new UnsupportedOperationException();
		}
	}

    /**
     * Nome: justify
     *
     * Justify.
     *
     * @param text the text
     * @return string
     * @see
     */
	private String justify(String text) {

		StringBuilder allJustifiedLines = new StringBuilder();

		for (String line : text.split(Pattern.quote(linebreak))) {
			line = line.trim();

			if (line.length() == getLimit() || line.length() <= 0) {
				allJustifiedLines.append(line).append(linebreak);
			} else if (line.length() < getLimit() && line.length() > 0) {
				Integer difference = getLimit() - line.length();
				String[] words = line.split(" ");

				if (words.length == 1) {
					difference = 0;
				}

				int evenOdd = 2;
				int initialIndex;

				while (difference > 0) {
					if (evenOdd % 2 == 0) {
						initialIndex = 0;
					} else {
						initialIndex = 1;
					}

					for (int j = initialIndex; j < words.length - 1; j += 2) {
						words[j] += " ";
						difference--;
						if (difference == 0)
							break;
					}
					evenOdd++;
				}

				StringBuilder singleJustifiedLine = new StringBuilder();
				String lineText;

				for (String word : words) {
					singleJustifiedLine.append(word).append(" ");
				}

				lineText = singleJustifiedLine.toString().trim();
				allJustifiedLines.append(lineText).append(linebreak);

			}
		}
		return allJustifiedLines.toString();
	}

	/**
	 * Nome: wrapLine
	 *
	 * Wrap line.
	 *
	 * @param line the line
	 * @param lineLength the line length
	 * @return string
	 * @see
	 */
	private String wrapLine(String line, int lineLength) {
	    if (line.length() == 0) return linebreak;
	    if (line.length() <= lineLength) return line + linebreak;

	    String[] words = line.split(" ");
	    StringBuilder allLines = new StringBuilder();
	    StringBuilder trimmedLine = new StringBuilder();

		for (String word : words) {
			if (trimmedLine.length() + 1 + word.length() <= lineLength) {
				trimmedLine.append(word).append(" ");
			} else if (trimmedLine.length() + word.length() == lineLength) {
				trimmedLine.append(word);
				allLines.append(trimmedLine).append(linebreak);
				trimmedLine = new StringBuilder();
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

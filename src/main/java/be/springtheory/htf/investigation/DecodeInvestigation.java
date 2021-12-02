package be.springtheory.htf.investigation;

import org.springframework.stereotype.Component;

@Component
public class DecodeInvestigation implements Investigation {
    String[] language = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
            "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
            ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
            "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};


    @Override
    public String solve(String investigationParameters) {
        String[] paramMorse = investigationParameters.split("\\s+");
        StringBuilder builder = new StringBuilder("");
        for (String paramMorseLetter : paramMorse) {
            for (int i = 0; i < morse.length; i++) {
                if (morse[i].equals(paramMorseLetter)) {
                    builder.append(language[i]).append(" ");
                    i = morse.length;
                }
            }
        }
        builder.deleteCharAt(builder.length() -1);
        return builder.toString();
    }

    @Override
    public String getInvestigationName() {
        return "Decode the following string";
    }
}

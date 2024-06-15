package org.jvatechs.manipulations_with_textfiles_and_csv.german_articles;

import org.jvatechs.manipulations_with_textfiles_and_csv.TextFileLineByLineReader;
import org.jvatechs.manipupulations_with_files.TextIntoFileSaver;

/*
     Here we have CSV file like with lines like: "word in German, die/der/das;translate".
    The "word in German" can contain  ", die;", ", der;", ", das;" markers in Nouns.
    So the part of code transform word in german like "word, die/der/das" into "die/der/das word ..."
    For example,
    "wort das,..." => "das wort..."
 */
public class GermanArticlesToStartMover {
    private final TextFileLineByLineReader lineReader = new TextFileLineByLineReader("TXT and CSV files only", "txt", "csv");
    private final TextIntoFileSaver fileSaver = new TextIntoFileSaver("fromMiddleToStartArticles.csv");
    private final StringBuilder stringBuilder = new StringBuilder();

    public void init() {
        changeFileWithArticles();
        fileSaver.saveIntoTxt(stringBuilder.toString());
    }

    //uncomment for check
//    public static void main(String[] args) {
//        System.out.println(new GermanArticlesToStartMover().moveArticleToStart("Absicht, die, -en;намерение"));
//        System.out.println(new GermanArticlesToStartMover().moveArticleToStart("Ausdruck, der;выражение"));
//    }

    private void changeFileWithArticles() {
        lineReader.processFileWithFunction(line -> {
            line = moveArticleToStart(line);
            stringBuilder.append(line);
            stringBuilder.append('\n');
            return null;
        });
    }

        private String moveArticleToStart(String line) {
        String[] articles =
                {"die, ", "der, ", "das, ", ", die;", ", der;", ", das;", " die;", " der;", " das;", ", der (", ", die (", ", das ("};
        String[] startArticles = {"die ", "der ", "das ", "die ", "der ", "das ", "die ", "der ", "das ", "der ", "die ", "das "};
        String[] replacements = {"", "", "", ";", ";", ";", ";", ";", ";", ", (", ", (", ", ("};

        for (int i = 0; i < articles.length; i++) {
            if (line.contains(articles[i])) {
                return startArticles[i] + line.replace(articles[i], replacements[i]);
            }
        }
        return line;
    }
}

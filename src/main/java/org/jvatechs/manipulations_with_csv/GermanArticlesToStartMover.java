package org.jvatechs.manipulations_with_csv;

import org.jvatechs.manipupulations_with_files.MyFileChooser;
import org.jvatechs.manipupulations_with_files.TextIntoFileSaver;

import java.io.*;
/*
     Here we have CSV file like with lines like: "word in German, die/der/das;translate".
    The "word in German" can contain  ", die;", ", der;", ", das;" markers in Nouns.
    So the part of code transform word in german like "word, die/der/das" into "die/der/das word ..."
    For example,
    "wort das,..." => "das wort..."
 */
public class GermanArticlesToStartMover {

    private final MyFileChooser myFileChooser = new MyFileChooser("TXT and CSV files only", "txt", "csv");
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
        File file = new GermanArticlesToStartMover().myFileChooser.chooseFile() ;
        if (file != null) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine();
                    line = moveArticleToStart(line);
                    stringBuilder.append(line);
                    stringBuilder.append('\n');
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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

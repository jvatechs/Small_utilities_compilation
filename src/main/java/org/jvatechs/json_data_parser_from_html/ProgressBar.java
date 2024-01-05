package org.jvatechs.json_data_parser_from_html;

public class ProgressBar {
    private final int totalDraws;
    private int drawsProcessed;

    public ProgressBar(int totalDraws) {
        this.totalDraws = totalDraws;
        this.drawsProcessed = 0;
    }

    public void increment() {
        this.drawsProcessed++;
        printProgressBar();
    }

    private void printProgressBar() {
        int progressBarLength = 50;
        int progress = (int) ((double) drawsProcessed / totalDraws * progressBarLength);

        System.out.print("\r[");
        for (int i = 0; i < progressBarLength; i++) {
            if (i < progress) {
                System.out.print("#");
            } else {
                System.out.print(" ");
            }
        }
        System.out.print("] " + drawsProcessed + "/" + totalDraws + " (" + (int) ((double) drawsProcessed / totalDraws * 100) + "%)");
    }
}


package org.jvatechs.manipulations_with_text_files;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.util.Matrix;

import java.io.*;

public class PdfOverlayHelper {
    public static void main(String[] args) throws IOException {
        // Ways to the input and output files
        File baseFile = new File("top.pdf");
        File overlayFile = new File("half.pdf");
        File outputFile = new File("result.pdf");

        // Open the basic and Overlay PDF (use Loader.loadpdf by default PDFBOX 3.X: Contentreference [OAICICE: 3] {Index = 3})
        try (PDDocument baseDoc = Loader.loadPDF(baseFile);
             PDDocument overlayDoc = Loader.loadPDF(overlayFile)) {

            // We take the first page from each document
            PDPage basePage = baseDoc.getPage(0);
            PDPage overlayPage = overlayDoc.getPage(0);

            // We create a form (Form Xobject) in the BASDOC document
            PDFormXObject form = new PDFormXObject(baseDoc);
            // Copy all the content from the OverlayPage page to the form (example of copying the contents: Contentreference [OAICITE: 4] {Index = 4})
            PDStream formContent = form.getContentStream();
            try (OutputStream os = formContent.createOutputStream();
                 InputStream is = overlayPage.getContents()) {
                if (is != null) {
                    // Copy bytes of the contents of the Overlay page in the form
                    is.transferTo(os);
                }
            }
            //We install the resources and region (BBOX) of the form so that it corresponds to the OverlayPage page: Contentreference [Oaicite: 5] {Index = 5}
            form.setResources(overlayPage.getResources());
            form.setBBox(overlayPage.getCropBox());

            // Now we are creating content-stem for the base page in Append mode (add on top of the existing contents)
            try (PDPageContentStream contentStream = new PDPageContentStream(baseDoc, basePage, AppendMode.APPEND, true)) {
                contentStream.saveGraphicsState();
                // We set the scale and position: we form a matrix [Scale 0 0 Scale X Y]
                float scale = 1f;           // An example of a scale of 0.5
                float x = -188;               // displacement by x
                float y = 0;               // displacement by y

                Matrix matrix = new Matrix();
                matrix.translate(x, y);
                matrix.scale(scale, scale);

                contentStream.transform(matrix);
                // We draw a form (Overlay page) on the base page
                contentStream.drawForm(form);
                contentStream.restoreGraphicsState();
            }

            // Save the result and close the documents
            baseDoc.save(outputFile);
        }
    }
}

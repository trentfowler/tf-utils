package com.tfowler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.parser.PdfTextExtractor;

/**
 * @see <a href="https://itextpdf.com/en/resources/examples/itext-7"></a>
 *
 */
public class PDFTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(PDFTest.class);

  // Path to the resulting PDF file.
  private static final String RESULT = Paths.get("src", "test", "resources", "test.pdf").toString();

  /**
   * Creates a PDF file containing the text "Hello, World!": "src/test/resources/test.pdf"
   * 
   * @see <a href=
   *      "https://itextpdf.com/en/resources/examples/itext-7/chapter-1-introducing-pdf-and-itext"></a>}
   * 
   * @throws IOException Error opening file output stream, permission issue or invalid file path.
   * @throws DocumentException lowagie.text exception dealing with {@link Document}.
   */
  @Test
  public void writePdf() throws IOException, DocumentException {
    // step 1
    final Document document = new Document(PageSize.LETTER);
    // step 2
    PdfWriter.getInstance(document, new FileOutputStream(RESULT));
    // step 3
    document.open();
    // step 4
    document.add(new Paragraph("Hello, World!"));
    // step 5
    document.close();
  }

  /**
   * Opens pdf reader to a test PDF file and extracts/logs the text contents of the first page.
   * 
   * @throws IOException lowagie.text.pdf exception trying to open reader to pdf, the file does not
   *         exist or is not accessible due to permissions.
   */
  @Test
  public void readPdf() throws IOException {
    // step 1
    final PdfReader reader =
        new PdfReader(Paths.get("src", "test", "resources", "test.pdf").toString());
    // step 2
    final PdfTextExtractor extractor = new PdfTextExtractor(reader);
    // step 3
    final String firstPage = extractor.getTextFromPage(1);
    // step 4
    LOGGER.info("First page text: {}", firstPage);
    // step 5
    reader.close();
  }
}

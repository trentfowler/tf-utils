package com.tfowler;

import de.vandermeer.asciitable.AsciiTable;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create the simple table seen below as a test of the {@link AsciiTable} dependency:
 *
 * ┌──────────────────────────────────────────────────────────────────────────────┐
 * │header                                                                        │
 * ├───────────────────────────────────────┬──────────────────────────────────────┤
 * │row 1 col 1                            │row 1 col 2                           │
 * ├───────────────────────────────────────┼──────────────────────────────────────┤
 * │row 2 col 1                            │row 2 col 2                           │
 * └───────────────────────────────────────┴──────────────────────────────────────┘
 *
 * @see <a href="http://www.vandermeer.de/projects/skb/java/asciitable/features.html"></a>
 */
public class AsciiTableTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(AsciiTableTest.class);

  @Test
  public void testCreateSimpleTable() {

    final AsciiTable table = new AsciiTable();
    table.addRule();
    // span both columns
    table.addRow(null, "header");
    table.addRule();
    table.addRow("row 1 col 1", "row 1 col 2");
    table.addRule();
    table.addRow("row 2 col 1", "row 2 col 2");
    table.addRule();

    final String rend = table.render();

    LOGGER.info("\n{}", rend);

  }
}

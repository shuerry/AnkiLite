package cs3500.pa02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args command line arguments composed of three arguments:
   *             String inputPath, String orderingFlag, String outputPath
   * @throws IOException if command line arguments are not valid or less than 3
   */
  public static void main(String[] args) throws IOException {
    final ArrayList<Path> myList = new ArrayList<>();
    // Check if all three arguments are provided
    if (args.length < 3) {
      throw new IOException("Please provide three command-line arguments");
    }

    // assigning the first command line argument to the input variable
    Path input = null;
    try {
      input = Path.of(args[0]);
    } catch (IllegalArgumentException e) {
      throw new IOException("Please provide a valid path");
    }

    // assigning the second command line argument to the ordering flag variable
    String orderingFlag  = null;
    try {
      orderingFlag = args[1];
    } catch (IllegalArgumentException e) {
      throw new IOException("Please provide an ordering flag");
    }

    //assigning the third command line argument to the output variable
    String output = args[2];

    //generate a list of markdown files by walking through the input directory
    MdFinder mdList = new MdFinder(myList);
    Files.walkFileTree(input, mdList);

    // write the summarized content to the output path
    SgWriter fw = new SgWriter(mdList.getList());
    fw.writeSg(output, orderingFlag);
  }
}
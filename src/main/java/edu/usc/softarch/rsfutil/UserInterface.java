package edu.usc.softarch.rsfutil;

import java.util.List;
import java.util.LinkedList;
import laser.util.ListFiles;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;

/**
 * Basic front-end.
 */
public class UserInterface
{
  private String _instructions;

  @Parameter(names={"--outputType"}, description="dot or json", required = true)
  String _outputType;
  @Parameter(names={"--inputPath"}, description="path to rsf file", required = true)
  String _inputPath;
  @Parameter(names={"--outputPath"}, description="path for output file", required = true)
  String _outputPath;
  @Parameter(names={"--substring"}, description="substring to filter in")
  String _substring;
  @Parameter(names={"--subsetPath"}, description="path to crawl from")
  String _subsetPath;
  @Parameter(names={"--prepend"}, description="prepend String for subset")
  String _prepend;

  public static void main(String args[])
  {
    UserInterface ui = new UserInterface();
    ui.makeInstructions();
    JCommander.newBuilder().addObject(ui).build().parse(args);

    List<GenericFilter> filters = new LinkedList<GenericFilter>();
    if(ui._substring != null)
      filters.add(new FilterSubstring(ui._substring));
    if(ui._subsetPath != null)
      filters.add(new FilterSubset(
        ListFiles.listFiles(ui._subsetPath, ui._prepend)));

    Converter converter = null;

    switch(ui._outputType.toLowerCase())
    {
      case "dot":
        converter = new ConverterDot(filters);
        break;
      case "json":
        converter = new ConverterEvaJson(filters);
        break;
      default:
        System.out.println(ui._instructions);
        System.exit(1);
    }

    converter.convertToFile(ui._inputPath, ui._outputPath);
  }

  private void makeInstructions()
  {
    _instructions = "See https://github.com/MarceloLaser/rsfUtil for ";
    _instructions += "instructions on how to use this utility.";
  }
}

package edu.usc.softarch.rsfutil;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import laser.util.EasyIO;

/**
 * Abstract converter for RSF files. Reads a "*_clusters.rsf" file and
 * converts to desired output. May be decorated with Filters.
 */
public abstract class Converter
{
  private List<GenericFilter> _filters;

  public Converter(List<GenericFilter> filters)
  {
    _filters = filters;
  }

  // #region IO ****************************************************************
  public void convertToFile(String input, String output)
  {
    convertToFile(input, output, input);
  }

  public void convertToFile(String input, String output, String name)
  {
    EasyIO.printFile(output, convert(input, name));
  }
  // #endregion IO *************************************************************

  public String convert(String path)
  {
    return convert(path, path);
  }

  /**
   * Method responsible for reading the RSF file.
   */
  public String convert(String path, String name)
  {
    String input = EasyIO.readFile(path);
    Map<String, List<String>> graph = new HashMap<String, List<String>>();
    input = input.replaceAll(System.lineSeparator(), " ");
    String[] tokens = input.split(" ");

    for(int i = 0; i < tokens.length; i = i + 3)
    {
      if(!graph.containsKey(tokens[i+1]))
        graph.put(tokens[i+1], new LinkedList<String>());
      graph.get(tokens[i+1]).add(tokens[i+2]);
    }

    for(GenericFilter filter : _filters)
    {
      graph = filter.filter(graph);
    }

    return genResult(graph, name);
  }

  public abstract String genResult
    (Map<String, List<String>> graph, String name);
}

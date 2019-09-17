package laser.util.rsf;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import laser.util.EasyIO;

/**
 * Basis for RSF converters using the composite design pattern. For an example
 * of how to use, see laser.util.rsf.RsfToDot.
 */
public abstract class RsfConverter
{
  // #region IO ****************************************************************
  public void convertToFile(String input, String output)
  {
    convertToFile(input, output, input);
  }

  public void convertToFile(String input, String output, String name)
  {
    EasyIO.printFile(output, convert(input, name));
  }

  public String convert(String path)
  {
    return convert(path, path);
  }
  // #endregion IO *************************************************************

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

    return genResult(filter(graph), name);
  }

  public abstract String genResult
    (Map<String, List<String>> graph, String name);

  /**
   * This method should be overriden if one desires to filter the graph, such
   * as to remove external classes from ACDC results.
   */
  public Map<String, List<String>> filter(Map<String, List<String>> graph)
  {
    return graph;
  }
}

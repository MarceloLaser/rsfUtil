package laser.util.rsf;

import java.util.Map;
import java.util.List;

/**
 * Example of use of the RsfConverter class. Declare a private static field
 * of type RsfConverter and instantiate a new class with "genResult(...)"
 * declared inline. genResult takes the graph created by RsfConverter.convert()
 * and generates a String to be written to an output file. The IO is handled
 * by RsfConverter.
 */
public class RsfToDot
{
  private static final RsfConverter converter =
    new RsfConverter()
    {
      @Override
      public String genResult(Map<String, List<String>> graph, String name)
      {
        String output = "digraph \"" + name + "\" {" + System.lineSeparator();

        for(String key : graph.keySet())
        {
          List<String> values = graph.get(key);
          output += "\tsubgraph \"cluster_" + key + "\"{" + System.lineSeparator();
          for(String value : values)
          {
            output += "\t\t\"" + value + "\";" + System.lineSeparator();
          }
          output += "\t}" + System.lineSeparator();
        }

        output += "}";

        return output;
      }
    };

  public static void main(String args[])
  {
    converter.convertToFile(args[0], args[1]);
  }
}

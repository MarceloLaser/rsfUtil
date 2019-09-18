package edu.usc.softarch.rsfutil;

import java.util.Map;
import java.util.List;

/**
 * Generates a (disfunctional) DOT file.
 */
public class ConverterDot
  extends Converter
{
  public ConverterDot(List<GenericFilter> filters)
  {
    super(filters);
  }

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
}

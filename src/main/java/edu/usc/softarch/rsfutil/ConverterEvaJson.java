package edu.usc.softarch.rsfutil;

import java.util.Map;
import java.util.List;

/**
 * Generates a (slightly disfunctional) JSON file to be input to EVA.
 */
public class ConverterEvaJson
  extends Converter
{
  public ConverterEvaJson(List<GenericFilter> filters)
  {
    super(filters);
  }

  @Override
  public String genResult(Map<String, List<String>> graph, String name)
  {
    String version = "irrelevant";

    String output = "{ \"version_list\": [ \"" + version + "\" ], ";
    output += "\"package_list\": [ \"leaf\" ], ";
    output += "\"name\": \"combined\", ";
    output += "\"children\": [ { ";
    output += "\"clusters\": [ ";

    for(String key : graph.keySet())
    {
      output += "\"" + key + "\", ";
    }

    output = output.substring(0, output.length()-2);
    output += "], ";
    output += "\"package_level\": 3, ";
    output += "\"name\": \"" + version + "\", ";
    output += "\"children\": [ ";

    for(String key : graph.keySet())
    {
      output += "{ \"name\": \"" + key + "\", ";
      output += "\"change\": 0, ";
      output += "\"children\": [ ";
      for(String value : graph.get(key))
      {
        output += "{ \"titles\": [], ";
        output += "\"name\": \"" + value + "\", ";
        output += "\"version\": \"" + version + "\", ";
        output += "\"bodys\": [], ";
        output += "\"labels\": [], ";
        output += "\"ids\": [], ";
        output += "\"change\": 0, ";
        output += "\"size\": 1 }, ";
      }
      output = output.substring(0, output.length()-2);
      output += "] }, ";
    }
    output = output.substring(0, output.length()-2);
    output += "] } ] }";

    return output;
  }
}

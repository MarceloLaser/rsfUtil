package laser.util.rsf;

import java.util.Map;
import java.util.List;
import java.util.LinkedList;

public class RsfToEvaJson
{
  private static final RsfConverter converter =
    new RsfConverter()
    {
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

      @Override
      public Map<String, List<String>> filter(Map<String, List<String>> graph)
      {
        for(List<String> values : graph.values())
        {
          int valuesSize = values.size();
          for(int i = 0; i < valuesSize; i++)
          {
            if(!values.get(i).contains("edu.usc"))
            {
              values.remove(i);
              valuesSize--;
              i--;
            }
          }
        }

        List<String> toRemoveList = new LinkedList<String>();
        for(String key : graph.keySet())
        {
          if(graph.get(key).size() == 0)
            toRemoveList.add(key);
        }
        for(String toRemove : toRemoveList)
        {
          graph.remove(toRemove);
        }

        return graph;
      }
    };

  public static void main(String args[])
  {
    converter.convertToFile(args[0], args[1]);
  }
}

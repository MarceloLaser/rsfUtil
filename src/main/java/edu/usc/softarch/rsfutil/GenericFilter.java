package edu.usc.softarch.rsfutil;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;

/**
 * Template for RSF converter filters.
 */
public abstract class GenericFilter
{
  protected boolean _dropEmptyClusters;

  public Map<String, List<String>> filter(Map<String, List<String>> graph)
  {
    for(List<String> values : graph.values())
    {
      int valuesSize = values.size();
      for(int i = 0; i < valuesSize; i++)
      {
        if(filterCondition(values, i))
        {
          values.remove(i);
          valuesSize--;
          i--;
        }
      }
    }

    if(_dropEmptyClusters)
      removeEmptyClusters(graph);

    return graph;
  }

  protected void removeEmptyClusters(Map<String, List<String>> graph)
  {
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
  }

  protected abstract boolean filterCondition(List<String> values, int i);
}

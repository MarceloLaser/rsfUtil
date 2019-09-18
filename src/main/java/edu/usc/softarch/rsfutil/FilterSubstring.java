package edu.usc.softarch.rsfutil;

import java.util.List;

/**
 * Filters out all nodes that do not contain given substring. Removes empty
 * clusters.
 */
public class FilterSubstring
  extends GenericFilter
{
  private String _substring;

  public FilterSubstring(String substring)
  {
    _substring = substring;
    _dropEmptyClusters = true;
  }

  @Override
  protected boolean filterCondition(List<String> values, int i)
  {
    return !values.get(i).contains(_substring);
  }
}

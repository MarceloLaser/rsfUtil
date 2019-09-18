package edu.usc.softarch.rsfutil;

import java.util.List;

/**
 * Filters in only the items present in the provided subset. Removes empty
 * clusters. To easily generate subset from directory structure,
 * laser.util.ListFiles.listFiles() is provided.
 */
public class FilterSubset
  extends GenericFilter
{
  private List<String> _validItems;

  public FilterSubset(List<String> validItems)
  {
    _validItems = validItems;
    _dropEmptyClusters = true;
  }

  @Override
  protected boolean filterCondition(List<String> values, int i)
  {
    return !_validItems.contains(values.get(i));
  }
}

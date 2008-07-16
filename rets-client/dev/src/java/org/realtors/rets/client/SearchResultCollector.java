package org.realtors.rets.client;

public interface SearchResultCollector {

	public void setCount(int count);

	public void setColumns(String[] columns);

	public void addRow(String[] row);

	public void setMaxrows();

	public void setComplete();
}

package org.realtors.rets.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.logging.LogFactory;

public class SearchResultImpl implements SearchResult, SearchResultCollector {

	private String[] columnNames;
	private int count;
	private List<String[]> rows;
	private boolean maxRows;
	private boolean complete;

	public SearchResultImpl() {
		this.count = 0;
		this.rows = new ArrayList<String[]>();
		this.maxRows = false;
		this.complete = false;
	}

	@Override
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int getCount() {
		if (this.count > 0) {
			return this.count;
		}
		return this.rows.size();
	}

	@Override
	public void setColumns(String[] columns) {
		this.columnNames = columns;
	}

	@Override
	public String[] getColumns() {
		return this.columnNames;
	}

	@Override
	public boolean addRow(String[] row) {
		if (row.length > this.columnNames.length) {
			throw new IllegalArgumentException(String.format("Invalid number of result columns: got %s, expected %s",row.length, this.columnNames.length));
		}
		if (row.length < this.columnNames.length) {
			LogFactory.getLog(SearchResultCollector.class).warn(String.format("Row %s: Invalid number of result columns:  got %s, expected ",this.rows.size(), row.length, this.columnNames.length));
		}
		return this.rows.add(row);
	}

	@Override
	public String[] getRow(int idx) {
		if (idx >= this.rows.size()) {
			throw new NoSuchElementException();
		}
		return this.rows.get(idx);
	}

	@Override
	public Iterator iterator() {
		return this.rows.iterator();
	}

	@Override
	public void setMaxrows() {
		this.maxRows = true;
	}

	@Override
	public boolean isMaxrows() {
		return this.maxRows;
	}

	@Override
	public void setComplete() {
		this.complete = true;
	}

	@Override
	public boolean isComplete() {
		return this.complete;
	}
}

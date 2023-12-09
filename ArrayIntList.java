

import java.util.Iterator;

public class ArrayIntList implements Iterable{
	
	//instance fields - state of array int list object
	private int[] data;
	private int capacity; //total size of the array
	private int size; //number of elements in the array
	private static final int DEFAULT_CAPACITY = 10;//constant
	
	
	public ArrayIntList() {
		this.capacity = DEFAULT_CAPACITY;
		data = new int[capacity];
		size = 0;
	}
	
	public void add(int item) {
		resize();
		data[size] = item;
		size++;
		
	}
	
	public void add(int index, int item) {
		checkIndex(index);
		
		resize();
		for(int i = size; i>index; i--) {
			data[i]= data[i-1];
		}
		data[index] = item;
		size++;
	}

	private void checkIndex(int index) {
		if(index <0 || index > size) 
			throw new ArrayIndexOutOfBoundsException(index);
	}
	
	public int get(int index) {
		checkIndex(index);
		
		return data[index];
	}
	
	private void resize() {
		if(size == capacity) {
			capacity *=2;
			int[] temp = new int[capacity];
			for(int i=0; i<size; i++) {
				temp[i] = data[i];
			}
			data = temp;
			
		}	
	}

	public int getSize() {
		return size;
	}

	
	
	@Override
	public Iterator iterator() {
		return new ArrayIntListIterator();
	}
	
	private class ArrayIntListIterator implements Iterator{
		private int cursor = -1;

		@Override
		public boolean hasNext() {
			return cursor != size-1;
		}

		@Override
		public Object next() {
			cursor++;
			return data[cursor];
		}
	
	}
}

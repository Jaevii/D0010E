import java.util.ArrayList;
import java.util.NoSuchElementException;

public class FIFO implements Queue{
	
	private ArrayList<Object> elements = new ArrayList<Object>();
	private int largest = 0;
	
	public int size() {
		return this.elements.size();
	}
	
	public int maxSize() {
		if(this.elements.size() > largest) {
			largest = this.elements.size();
			return largest;
		}
		return largest;
	}
	
	public boolean isEmpty() {
		if(this.elements.size() == 0) {
			return true;
		}
		return false;
	}

	public Object first() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return this.elements.get(0);
	}
	
	public boolean equals(Object f) {
		if (f.getClass() != this.getClass()) {
			throw new ClassCastException();
		}
		
		if(this.size() == ((FIFO) f).size()) {
			for(int i = 0; i < this.size(); i++) {
				Object a = this.elements.get(i);
				Object b = ((FIFO)f).elements.get(i);
				
				if (a == null && b == null) {
					continue;
				}
				
				if (a != null && b == null) {
					return false;
				}
				
				if (a == null && b != null) {
					return false;
				}
				
				if(!(a.equals(b))) {
					return false;
				}
			}
		} else {
			return false;
		}
		
		return true;
	}

	public String toString() {
		
		String t = "Queue: ";
		
		for (Object item : this.elements) {
			t += "(" + String.valueOf(item) + ") ";
		}
		
		return t;
	}
	
	public void add(Object item) {
		this.elements.add(item);
	}

	public void removeFirst() {
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		}
		this.elements.remove(0);
	}

}



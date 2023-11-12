package lists;


public interface Lista<T> {
      public boolean isEmpty();
      public void add(T elem);
      public void add(int index, T elem);
      public void addAll(Lista<T> lista);
      public boolean contains(T elem);
      public Lista<T> subList(int fromIndex,int toIndex);
      public int indexOf(T elem);
      public int indexOfLast(T elem);
      public T get(int index);
      public void set(int index, T elem);
      public int size();
      public String toString();
      public void clear();
      public void remove(int index);
      public void remove(T elem);
       

}

package lists;

public class ArrayListClone<T> {
      private Object[] innerArray;
      private int count;

      public ArrayListClone() {
            innerArray = new Object[10];
            count = 0;
      }

      public ArrayListClone(Object[] innerArray) {
            this.innerArray = innerArray;
            count = innerArray.length;
      }

      public int indexOf(Object e) {
            for (int i = 0; i < count; i++) {
                  if (e.equals(innerArray[i])) return i;
            }
            return -1;
      }

      public void addAt(int index, T e) {
            add(null);
            count--;
            for (int i = count; i > index; i--) {
                  innerArray[i] = innerArray[i - 1];
            }
            innerArray[index] = e;
            count++;
      }

      public void removeAt(int index) {
            for (int i = index; i < count - 1; i++) {
                  innerArray[i] = innerArray[i + 1];
            }
            count--;
      }

      public void removeFirst(Object e) {
            int pos = indexOf(e);
            for (int i = pos; i < count - 1; i++) {
                  innerArray[i] = innerArray[i + 1];
            }
            count--;
      }

      public int size() {
            return count;
      }

      public void addAll(ArrayListClone<T> list) {
            for (int i = 0; i < count; i++) {
                  add(list.get(i));
            }
      }

      public boolean contains(Object e) {
            for (int i = 0; i < count; i++) {
                  if (e.equals(innerArray[i])) return true;
            }
            return false;
      }

      public ArrayListClone<T> subList(int fromIndex, int toIndex) {
            ArrayListClone<T> ret = new ArrayListClone<T>();
            for (int i = toIndex; i < toIndex; i++) {
                  ret.add(get(i));
            }
            return ret;
      }

      public String toString() {
            String ret = "";
            for (int i = 0; i < count - 1; i++) {
                  ret = ret + innerArray[i] + ", ";
            }
            return "[" + ret + innerArray[count - 1] + "]";
      }

      public boolean isEmpty() {
            return count == 0;
      }

      public void add(T e) {
            if (count == innerArray.length) {
                  Object[] aux = new Object[innerArray.length + innerArray.length << 1];
                  for (int i = 0; i < count; i++) {
                        aux[i] = innerArray[i];
                  }
                  innerArray = aux;
            }
            innerArray[count] = e;
            count++;
      }

      public T get(int index) {
            if (index >= count) throw new ArrayIndexOutOfBoundsException(index);
            return (T) innerArray[index];
      }

      public void set(int index, T e) {
            if (index >= count) throw new ArrayIndexOutOfBoundsException(index);
            innerArray[index] = e;
      }

      public void clear() {
            count = 0;
            innerArray = new Object[10];
      }

}

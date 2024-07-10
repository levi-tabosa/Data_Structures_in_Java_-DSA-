package lists;

public class ArrayListClone<T> {
      private Object[] arrayInterno;
      private int count;

      public ArrayListClone() {
            this.arrayInterno = new Object[10];
            this.count = 0;
      }

      public ArrayListClone(Object[] arrayInterno) {
            this.arrayInterno = arrayInterno;
            this.count = arrayInterno.length;
      }

      public int indexOf(Object elem) {
            for (int i = 0; i < count; i++) {
                  if (elem.equals(arrayInterno[i]))
                        return i;
            }
            return -1;
      }

      public int busca(Object elem, int min, int max) {
            if (min > max)
                  return -1;
            int chute = (min + max) / 2;
            if (get(chute).equals(elem))
                  return chute;

            int a = busca(elem, chute + 1, max);
            if (a > 0)
                  return a;
            else
                  return busca(elem, min, chute - 1);
      }

      public int indexOfLast(Object elem) {
            for (int i = count - 1; i >= 0; i--) {
                  if (elem.equals(arrayInterno[i]))
                        return i;
            }

            return -1;
      }

      public void add(int index, T elem) {
            add(null);
            count--;
            for (int i = count; i > index; i--) {
                  arrayInterno[i] = arrayInterno[i - 1];
            }
            arrayInterno[index] = elem;
            count++;
      }

      public void remove(int index) {
            for (int i = index; i < count - 1; i++) {
                  arrayInterno[i] = arrayInterno[i + 1];
            }
            count--;
      }

      public void remove(Object elem) {
            int pos = indexOf(elem);
            for (int i = pos; i < count - 1; i++) {
                  arrayInterno[i] = arrayInterno[i + 1];
            }
            count--;
      }

      public int size() {
            return count;
      }

      public void addAll(ArrayListClone<T> lista) {
            for (int i = 0; i < count; i++) {
                  add(lista.get(i));
            }
      }

      public boolean contains(Object elem) {
            for (int i = 0; i < count; i++) {
                  if (elem.equals(arrayInterno[i]))
                        return true;
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
            String out = "";
            for (int i = 0; i < count - 1; i++) {
                  out = out + arrayInterno[i] + ", ";
            }

            return "[" + out + arrayInterno[count - 1] + "]";
      }

      public boolean isEmpty() {
            return count == 0;
      }

      public void add(T elem) {
            if (count == arrayInterno.length) {
                  Object[] aux = new Object[arrayInterno.length + arrayInterno.length << 1];
                  for (int i = 0; i < count; i++) {
                        aux[i] = arrayInterno[i];
                  }
                  arrayInterno = aux;
            }
            arrayInterno[count] = elem;
            count++;
      }

      public T get(int index) {
            if (index >= count)
                  throw new ArrayIndexOutOfBoundsException(index);
            @SuppressWarnings("unchecked")
            T item = (T) arrayInterno[index];
            return item;
      }

      public void set(int index, T elem) {
            if (index >= count)
                  throw new ArrayIndexOutOfBoundsException(index);
            arrayInterno[index] = elem;
      }

      public void clear() {
            count = 0;
            arrayInterno = new Object[10];
      }

}

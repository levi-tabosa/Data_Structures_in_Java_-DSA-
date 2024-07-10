package Trees;

@SuppressWarnings("unchecked")
public class SequentialBST<T extends Comparable<T>> {
   Object[] innerArray;

   public SequentialBST() {
      innerArray = new Object[10000];
   }

   public void add(T elem) {
      if (innerArray[0] == null) {
         innerArray[0] = elem;
      } else {
         add(elem, 0);
      }
   }

   public void add(T elem, int index) {
      if (index >= innerArray.length)
         resize();
      if (innerArray[index] != null) {
         if (elem.compareTo((T) innerArray[index]) > 0) {
            add(elem, (index << 1) + 2);
         } else {
            add(elem, (index << 1) + 1);
         }
      } else {
         innerArray[index] = elem;
      }
   }

   private void resize() {
      Object[] array = new Object[(innerArray.length << 2) - innerArray.length];
      for (int i = 0; i < innerArray.length; i++) {
         array[i] = innerArray[i];
      }
      innerArray = array;
   }

   public boolean Contains(T elem) {
      if (innerArray[0] == null) {
         return false;
      }
      return contains(elem, 0);
   }

   private boolean contains(T elem, int index) {
      if (elem.equals(innerArray[index]))
         return true;

      int l = (index << 1) + 1;
      int r = l + 1;

      if (elem.compareTo((T) innerArray[index]) > 0) {
         if (r < innerArray.length) {
            return contains(elem, r);
         }
      } else {
         if (l < innerArray.length) {
            return contains(elem, l);
         }
      }
      return false;
   }

   public void remove(T elem) {
      if (innerArray[0] != null)
         remove(elem, 0);
   }

   private void remove(T elem, int index) {
      int l = (index << 1) + 1;
      int r = l + 1;

      if (elem.equals(innerArray[index])) {
         if (innerArray[l] == null && innerArray[r] == null)
            innerArray[index] = null;
         else if (innerArray[r] == null) {
            innerArray[index] = innerArray[l];
            siftUp(l);
         } else if (innerArray[l] == null) {
            innerArray[index] = innerArray[r];
            siftUp(r);
         } else {
            innerArray[index] = max(r);
            remove((T) innerArray[index], r);
         }
      } else if (elem.compareTo((T) innerArray[index]) > 0) {
         remove(elem, r);
      } else {
         remove(elem, l);
      }
   }

   private void siftUp(int index) {
      while (index < innerArray.length) {
         int l = (index << 1) + 1;
         int r = l + 1;

         if (l >= innerArray.length || innerArray[l] == null) {
            break;
         }

         if (innerArray[r] == null || ((T)innerArray[l]).compareTo((T) innerArray[r]) > 0) {
            innerArray[index] = innerArray[l];
            index = l;
         } else {
            innerArray[index] = innerArray[r];
            index = r;
         }
      }

      if (index < innerArray.length) {
         innerArray[index] = null;
      }
   }


   private T max(int index) {
      while (index < innerArray.length) {
         if (innerArray[(index << 1) + 1] != null)
            index = (index << 1) + 1;
         else
            return (T) innerArray[index];
      }
      return null;
   }

   @Override
   public String toString() {
      String ret = "";
      ret = preOrdem(0);
      return '[' + ret + ']';
   }

   public String printaArvre(int i) {
      if (i > 0)
         return posOrdem(0);
      if (i < 0)
         return emOrdem(0);
      return preOrdem(0);
   }

   private String preOrdem(int index) {
      String ret = innerArray[index] + "";

      int l = (index << 1) + 1;
      int r = l + 1;
      if (l < innerArray.length) {
         if (innerArray[l] != null) {
            ret += ", " + preOrdem(l) + ", ";
         }
      }
      if (r < innerArray.length) {
         if (innerArray[r] != null) {
            ret += preOrdem(r) + ", ";
         }
      }
      return ret;
   }

   private String emOrdem(int index) {
      String ret = innerArray[index] + "";

      int l = (index << 1) + 1;
      int r = l + 1;
      if (l < innerArray.length) {
         if (innerArray[l] != null) {
            ret = emOrdem(l) + ", " + ret;
         }
      }
      if (r < innerArray.length) {
         if (innerArray[r] != null) {
            ret += ", " + emOrdem(r);
         }
      }
      return ret;
   }

   private String posOrdem(int index) {
      String ret = innerArray[index].toString();

      int l = (index << 1) + 1;
      int r = l + 1;
      if (r < innerArray.length) {
         if (innerArray[r] != null) {
            ret = posOrdem(r) + ", " + ret;
         }
      }
      if (l < innerArray.length) {
         if (innerArray[l] != null) {
            ret = posOrdem(l) + ", " + ret;
         }
      }
      return ret;
   }

   private void print() {
      for (int i = 0; i < innerArray.length; i++) {
         System.out.print(innerArray[i] != null ? innerArray[i] + " " : "X ");
      }
      System.out.println();
   }

   public static void main(String[] args) {
      SequentialBST<Integer> a = new SequentialBST<>();

      a.add(6);
      a.add(8);
      a.add(2);
      a.add(4);
      a.add(1);
      a.add(3);
      a.add(9);

      System.out.println(a.emOrdem(0));
      System.out.println(a.preOrdem(0));
      System.out.println(a.posOrdem(0));
      a.print();

      System.out.println();

      a.remove(4);
      a.remove(6);

      System.out.println(a.emOrdem(0));
      System.out.println(a.preOrdem(0));
      System.out.println(a.posOrdem(0));
      a.print();
   }
}

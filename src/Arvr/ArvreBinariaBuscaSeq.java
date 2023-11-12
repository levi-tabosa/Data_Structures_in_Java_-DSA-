package Arvr;

@SuppressWarnings("unchecked")
public class ArvreBinariaBuscaSeq<T extends Comparable<T>> {
      Object[] innerArray;

      public ArvreBinariaBuscaSeq() {
            innerArray = new Object[25];
      }

      public void add(T elem) {
            if (innerArray[0] == null) {
                  innerArray[0] = elem;
            } else {
                  add(elem, 0);
            }
      }

      public void add(T elem, int index) {
            if(index >= innerArray.length) resize();
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
            for (int i = 0; i < innerArray.length; i++){
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

            int esq = (index << 1) + 1;
            int dir = esq + 1;

            if (elem.compareTo((T) innerArray[index]) > 0) {
                  if (dir < innerArray.length) {
                        return contains(elem, dir);
                  }
            } else {
                  if (esq < innerArray.length) {
                        return contains(elem, esq);
                  }
            }
            return false;
      }

      public void remove(T elem) {
            if (innerArray[0] != null)
                  remove(elem, 0);
      }

      private void remove(T elem, int index) {
            int esq = (index << 1) + 1;
            int dir = esq + 1;

            if (elem.equals(innerArray[index])) {
                  if (innerArray[esq] == null && innerArray[dir] == null)
                        innerArray[index] = null;
                  else if (innerArray[dir] == null) {
                        innerArray[index] = innerArray[esq];
                        siftUp(esq, true);
                  } else if (innerArray[esq] == null) {
                        innerArray[index] = innerArray[dir];
                        siftUp(dir, false);
                  } else {
                        //innerArray[index] = min(esq);             substitui pelo antecessor
                        //remove((T) innerArray[index], esq);       
                        innerArray[index] = max(dir);             //substitui pelo sucessor 
                        remove((T) innerArray[index], dir);
                  }
            } else if (elem.compareTo((T) innerArray[index]) > 0) {
                  remove(elem, dir);
            } else {
                  remove(elem, esq);
            }
      }
      
      private void siftUp(int index, boolean isleft) {
            if (index < innerArray.length) {
                  int esq = (index << 1) + 1;
                  int dir = esq + 1;

                  if (innerArray[esq] != null && isleft) {
                        innerArray[index] = innerArray[esq];
                        innerArray[index + 1] = innerArray[dir];
                        siftUp(esq, true);
                  } else if (innerArray[dir] != null) {
                        innerArray[index] = innerArray[dir];
                        innerArray[index - 1] = innerArray[esq];
                        siftUp(dir, false);
                  } else {
                        if (isleft) {
                              innerArray[index] = null;
                              innerArray[index + 1] = null;
                        } else {
                              innerArray[index] = null;
                              innerArray[index - 1] = null;
                        }
                  }
            }

      }
      /* 
      private T min(int index) {
            while (index < innerArray.length) {
                  if (innerArray[(index << 1) + 2] != null)
                        index = (index << 1) + 2;
                  else
                        return (T) innerArray[index];
            }
            return null;
      }
      */
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

            int esq = (index << 1) + 1;
            int dir = esq + 1;
            if (esq < innerArray.length) {
                  if (innerArray[esq] == null) {
                        //ret += ", ";
                  } else {
                        ret += ", " + preOrdem(esq);
                  }
            }
            if (dir < innerArray.length) {
                  if (innerArray[dir] == null) {
                        //ret += ", ";
                  } else {
                        ret += preOrdem(dir) + ", ";
                  }
            }
            return ret;
      }

      private String emOrdem(int index) {
            String ret = innerArray[index] + "";

            int esq = (index << 1) + 1;
            int dir = esq + 1;
            if (esq < innerArray.length) {
                  if (innerArray[esq] == null) {
                        //ret += ", ";
                  } else {
                        ret =  emOrdem(esq) + ", " + ret;
                  }
            }
            if (dir < innerArray.length) {
                  if (innerArray[dir] == null) {
                        //ret += ", ";
                  } else {
                        ret += ", " + emOrdem(dir);
                  }
            }
            return ret;
      }

      private String posOrdem(int index) {
            String ret = innerArray[index].toString();

            int esq = (index << 1) + 1;
            int dir = esq + 1;
            if (dir < innerArray.length) {
                  if (innerArray[dir] == null) {
                       // ret += ", ";
                  } else {
                        ret = posOrdem(dir) + ", " + ret;
                  }
            }
            if (esq < innerArray.length) {
                  if (innerArray[esq] == null) {
                       // ret += ", ";
                  } else {
                        ret = posOrdem(esq) + ", " + ret;
                  }
            }
            return ret;
      }

      private void printaArray(){
            for (int i = 0; i < innerArray.length; i++) {
                  if(innerArray[i] == null) System.out.print("X ");
                  else System.out.print(innerArray[i] + " ");
            }
            System.out.println();
      }

      public static void main(String[] args) {
            ArvreBinariaBuscaSeq<Integer> a = new ArvreBinariaBuscaSeq<>();

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
            a.printaArray();

            System.out.println();
            
            a.remove(4);
            a.remove(6);

            System.out.println(a.emOrdem(0));
            System.out.println(a.preOrdem(0));
            System.out.println(a.posOrdem(0));
            a.printaArray();
      }
}

package lists;

public class BusqueBi {
      public static int busca(int[] array, int min, int max, int alvo) {
            if (max < min)
                  return -1;

            int chute = (min + max) / 2;

            if (array[chute] == alvo)
                  return chute;

            int a = busca(array, chute + 1, max, alvo);

            if (a > 0)
                  return a;

            else
                  return busca(array, min, chute - 1, alvo);
      }

      public static void main(String[] args) {
            int[] arr = { 1, 7, 3, 5, 2, 3, 61, 57, 999, 5, 43, 23, 672, 3, 4, 5, 6, 7, 8, 9, 0 };
            System.out.println(arr.length);
            System.out.println(busca(arr, 0, arr.length - 1, 999));
      }
}

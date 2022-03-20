import java.util.Arrays;
import java.util.*;
import java.util.ArrayList;

public class CodingSolutionJava {

  // Minimum and maximmum of an array

  public static <T extends Comparable> List<T> minMax(T[] array) {
    T min = array[0];
    T max = array[0];

    for (int i = 0; i < array.length; i++) {
      if (min.compareTo(array[i]) == 1) {
        min = array[i];
      }
    }
    for (int i = 0; i < array.length; i++) {

      if (array[i].compareTo(max) == 1) {
        max = array[i];
      }
    }
    List<T> result = Arrays.asList(min, max);

    return result;
  }

  // Swap two variables in place

  public static Double[] swapVariables(Double variable1, Double variable2) {
    variable1 = variable1 - variable2;
    variable2 = variable1 + variable2;
    variable1 = variable2 - variable1;
    Double[] result = { variable1, variable2 };
    return result;
  }

  // Matrix sorted by the sum of its rows

  public static Double sumArray(Double[] array) {
    Double sum = 0.0;
    for (int i = 0; i < array.length; ++i) {
      sum += array[i];
    }
    return sum;
  }

  public static Double[][] matrixSortedBySum(Double[][] matrix) {

    Double[] sumRowsSorted = new Double[matrix.length];
    Double[][] matrixSorted = new Double[matrix.length][matrix[0].length];
    Double inf = Double.POSITIVE_INFINITY;

    for (int i = 0; i < matrix.length; ++i) {
      sumRowsSorted[i] = sumArray(matrix[i]);
    }
    Arrays.sort(sumRowsSorted);
    for (int i = 0; i < matrix.length; ++i) {
      int j = Arrays.asList(sumRowsSorted).indexOf(sumArray(matrix[i]));
      matrixSorted[j] = matrix[i];
      sumRowsSorted[j] = inf;
    }
    return matrixSorted;
  }

  public static int rowSum(int[][] matrix, int row) {
    int sum = 0;
    for (int i = 0; i < matrix.length; ++i) {
      if (matrix[row][i] == 0) {
        sum = -1;
        break;
      } else {
        sum += matrix[row][i];
      }

    }
    return sum;
  }

  public static int columnSum(int[][] matrix, int column) {
    int sum = 0;
    for (int i = 0; i < matrix.length; ++i) {
      if (matrix[i][column] == 0) {
        sum = -1;
        break;
      } else {
        sum += matrix[i][column];
      }

    }
    return sum;
  }

  // Has a cross

  public static int hasCross(int[][] matrix) {
    int count1 = 0;
    int count2 = 0;

    for (int i = 0; i < matrix.length; ++i) {
      if (rowSum(matrix, i) == -1) {
        continue;
      } else if (rowSum(matrix, i) == matrix.length) {
        count1 += 1;
      } else if (rowSum(matrix, i) == 2 * matrix.length) {
        count2 += 1;
      }
    }
    if ((count1 > 0 && count2 > 0) || (count1 == 0 && count2 == 0)) {
      return 0;
    }
    if (count1 == 0 && count2 > 0) {
      for (int i = 0; i < matrix.length; ++i) {
        if (columnSum(matrix, i) == 2 * matrix.length) {
          return 2;
        }
      }
    }
    if (count1 > 0 && count2 == 0) {
      for (int i = 0; i < matrix.length; ++i) {
        if (columnSum(matrix, i) == matrix.length) {
          return 1;
        }
      }
    }
    return 0;
  }

  // Pairs in an array with a given sum

  public static ArrayList<List> pairsSum(int[] array, int sum) {
    ArrayList<List> pairs = new ArrayList<>();
    Arrays.sort(array);

    for (int i = 0; i < array.length; ++i) {

      if (pairs.contains(Arrays.asList(sum - array[i], array[i]))) {
        continue;
      }

      if (binarysearch(array, sum - array[i])) {
        pairs.add(Arrays.asList(array[i], sum - array[i]));
      }
    }
    return pairs;

  }

  // Binary search

  public static boolean binarysearch(int[] array, int guess) {
    int high = array.length;
    int low = 0;

    int mid;

    while (low < high) {
      mid = (high + low) / 2;
      if (array[mid] == guess) {
        return true;
      } else {
        if (array[mid] < guess) {
          low = mid + 1;
        } else {
          high = mid;
        }
      }
    }

    return false;
  }

  // Peak of an array

  public static int peakArray(int[] array) {

    int mid = array.length / 2;

    if (array.length == 1) {
      return array[0];
    }
    if (array.length == 2) {
      return Math.max(array[0], array[1]);
    }

    if (array[mid] >= array[mid + 1] && array[mid] >= array[mid - 1]) {
      return array[mid];
    }

    if (array[mid] < array[mid + 1]) {
      return peakArray(Arrays.copyOfRange(array, mid + 1, array.length));
    }

    if (array[mid] < array[mid - 1]) {
      return peakArray(Arrays.copyOfRange(array, 0, mid));
    }
    return 0;
  }

  // Peak of a matrix
  public static Double[][] submatrix0fmatrix(Double[][] matrix, int firstColumn, int secondColumn) {

    Double[][] submatrix = new Double[matrix.length][secondColumn - firstColumn + 1];

    for (int i = 0; i < matrix.length; ++i) {
      submatrix[i] = Arrays.copyOfRange(matrix[i], firstColumn, secondColumn + 1);
    }
    return submatrix;
  }

  public static Double[] column(Double[][] matrix, int index) {

    Double[] column = new Double[matrix.length];

    for (int i = 0; i < matrix.length; ++i) {
      column[i] = matrix[i][index];
    }

    return column;
  }

  public static Double peakMatrix(Double[][] matrix) {

    int n = matrix[0].length / 2;
    int p = matrix[0].length;

    if (p == 1) {
      return minMax(column(matrix, 0)).get(1);

    }
    if (p == 2) {
      return Math.max(minMax(column(matrix, 0)).get(1),
          minMax(column(matrix, 1)).get(1));

    }

    Double m = minMax(column(matrix, n)).get(1);

    int j = Arrays.asList(column(matrix, n)).indexOf(m);

    if (matrix[j][n - 1] <= m && matrix[j][n + 1] <= m) {
      return m;
    }
    if (matrix[j][n - 1] > m) {
      return peakMatrix(submatrix0fmatrix(matrix, 0, n - 1));
    }

    if (matrix[j][n + 1] > m) {
      return peakMatrix(submatrix0fmatrix(matrix, n + 1, p - 1));
    }
    return 0.0;
  }

   

}

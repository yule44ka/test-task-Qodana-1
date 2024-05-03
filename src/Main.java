import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

  static final int N = 26;
  static List<List<Integer>> graph = new ArrayList<>(N);
  static ArrayList<Integer> mark = new ArrayList<>(Collections.nCopies(N, 0));
  static ArrayList<Boolean> used = new ArrayList<>(Collections.nCopies(N, false));
  static ArrayList<Integer> topologicalOrder = new ArrayList<>();

  static {
    for (int i = 0; i < N; i++) {
      graph.add(new ArrayList<>());
    }
  }

  static boolean addEdgesAndFindProblems(ArrayList<String> names) {
    for (int i = 0; i < names.size(); i++) {
      for (int j = i + 1; j < names.size(); j++) {
        boolean edgeAdded = false;
        String firstName = names.get(i);
        String secondName = names.get(j);
        for (int k = 0; k < Math.min(firstName.length(), secondName.length()); k++) {
          if (firstName.charAt(k) != secondName.charAt(k)) {
            graph.get(firstName.charAt(k) - 'a').add(secondName.charAt(k) - 'a');
            edgeAdded = true;
            break;
          }
        }
        if (!edgeAdded && firstName.length() > secondName.length()) {
          return true;
        }
      }
    }
    return false;
  }

  static boolean dfs(int v) {
    mark.set(v, 1);
    used.set(v, true);
    for (int u : graph.get(v)) {
      if (mark.get(u) == 0) {
        if (dfs(u)) {
          return true;
        }
      } else if (mark.get(u) == 1) {
        return true;
      }
    }
    mark.set(v, 2);
    topologicalOrder.add(v);
    return false;
  }

  static boolean topologicalSortAndSearchCycle() {
    boolean cycleFound = false;
    for (int v = 0; v < N; v++) {
      if (!used.get(v)) {
        cycleFound = dfs(v);
        if (cycleFound) {
          break;
        }
      }
    }
    Collections.reverse(topologicalOrder);
    return cycleFound;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    ArrayList<String> names = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      names.add(scanner.next());
    }
    if (addEdgesAndFindProblems(names)) {
      System.out.println("Impossible");
    } else if (topologicalSortAndSearchCycle()) {
      System.out.println("Impossible");
    } else {
      for (int el : topologicalOrder) {
        System.out.print((char) ('a' + el));
      }
      System.out.println();
    }
  }
}

# test-task-Qodana-1

#### Algorithm:
1. Construct a graph with letters of the Latin alphabet as vertices. Edges represent the lexicographical order of the letters.
2. Find a topological sorting or a cycle in this graph using depth-first search (DFS). A topological sorting exists if and only if there are no cycles in the graph.
3. Print the necessary answer.

#### Methods:
1. **addEdgesAndFindProblems**: Check pairs of names where the first name is lexicographically greater than the second name. Search for the first differing letter. Build an edge from the letter of the first name to the letter of the second name. If the prefix of the first name and the second name are equal but the first name is longer than the second one (for example: "apple" and "app"), the lexicographical order is not correct, so return true (problem found). Otherwise, return false.
2. **dfs**: Classic depth-first search for finding a cycle. After processing a node, add it to the list containing the topological order. If there is a cycle, return true. Otherwise, return false.
3. **topologicalSortAndSearchCycle**: Run depth-first search on the edges from every connectivity component. Reverse the list of the topological order. If there is a cycle, return true. Otherwise, return false.

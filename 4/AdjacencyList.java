/**
 * AdjacencyList container for the representation of a maze graph.
 * Each vertex is decremented by one and assigned as an index in an array of all
 * vertices. Each array element is an ArrayLinkedList that contains all the
 * adjacencies of the parent vertex.
 * @author Mitchell Sawatzky
 * @since March 2016
 * @version 1.0
 */
public class AdjacencyList {
    /**
     * The array of all vertices
     */
    private AdjacencyLinkedList[] list;

    /**
     * Adds an adjacency to a vertex in the maze graph
     * @throws DuplicateEdgeException when the adjacency being added already exists
     * @param fromNode - the source vertex (index)
     * @param toNode - the destination vertex (index)
     * @param weight - the weight of the edge
     */
    private void addAdjacency (int fromNode, int toNode, int weight) throws DuplicateEdgeException {
        if (list[fromNode] == null) {
            list[fromNode] = new AdjacencyLinkedList(fromNode);
        }
        try {
            list[fromNode].setNode(toNode, weight);
        } catch (DuplicateEdgeException e) {
            throw new DuplicateEdgeException("Adjacency already exists from node " + (fromNode + 1) + " to " + (toNode + 1));
        }
    }

    /**
     * Constructs an AdjacencyList object with length n
     * @constructor
     * @param n - the length of the vertex adjacency array
     */
    public AdjacencyList (int n) {
        list = new AdjacencyLinkedList[n*n];
    }

    /**
     * Parse a string representation of an adjacency and add it to its vertex
     * @throws InputLengthException when the adjacency string is not 3 tokens long
     * @throws DuplicateEdgeException when the edge in the adjacency string already exists
     * @throws UnexpectedTokenException when a token in the string is not a parseable integer
     * @param line - a string representation of an adjacency in the form of
     * "<fromVertex> <toVertex> <weight>"
     */
    public void parseAdjacency (String line) throws InputLengthException, DuplicateEdgeException, UnexpectedTokenException {
        String[] tokens = line.split("\\s+");
        if (tokens.length != 3) {
            throw new InputLengthException(line);
        } else {
            for (int i = 0; i < 3; i++) {
                if (!tokens[i].matches("^[0-9]+$")) {
                    throw new UnexpectedTokenException(tokens[i]);
                }
            }
            addAdjacency(Integer.parseInt(tokens[0])-1, Integer.parseInt(tokens[1])-1, Integer.parseInt(tokens[2]));
        }
    }

    /**
     * Preform a breadth-first search for the shortest unweighted path between from and to
     * @param from - the source vertex index
     * @param to - the destination vertex index
     * @returns a string representation of the path, in the form "<source> <path> <path> ... <desintation>"
     */
    public String BFS (int from, int to) {
        Queue q = new Queue(list.length);
        int[] visited = new int[list.length];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -2;
        }
        q.enq(list[from]);
        visited[from] = -1;
        while (!q.isEmpty()) {
            AdjacencyLinkedList n = q.deq();
            if (n.getParent() == to) {
                break;
            }
            Adjacency a = n.getHead();
            if (a != null) {
                do {
                    if (visited[a.getNode()] == -2) {
                        visited[a.getNode()] = n.getParent();
                        q.enq(list[a.getNode()]);
                    }
                    if (a.getNext() == null) {
                        break;
                    }
                    a = a.getNext();
                } while (true);
            }
        }
        if (visited[to] == -2) {
            return (from + 1) + " -1 " + (to + 1);
        } else {
            int[] reverse = new int[list.length];
            int i = 0;
            int b = visited[to];
            reverse[i++] = to + 1;
            do {
                reverse[i++] = b + 1;
                b = visited[b];
            } while (b != -1);
            String res = "";
            for (i -= 1; i >= 0; i--) {
                res += reverse[i] + " ";
            }
            return res.trim();
        }
    }

    /**
     * Implementation of Dijkstra's algorithm to find the shortest weighted path from from to to
     * @param from - the source vertex index
     * @param to - the destination vertex index
     * @returns a string representation of the path, in the form "<source> <path> <path> ... <desintation>"
     */
    public String dij (int from, int to) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                list[i].setDist(Integer.MAX_VALUE);
            }
        }
        PQueue pq = new PQueue(list.length);
        list[from].setDist(0);
        pq.push(list[from]);
        while (!pq.isEmpty()) {
            AdjacencyLinkedList n = pq.pop();
            if (n.getParent() == to) {
                int[] reverse = new int[list.length];
                int i = 0;
                int b = list[to].getBack();
                list[from].setBack(-1);
                reverse[i++] = to + 1;
                do {
                    reverse[i++] = b + 1;
                    b = list[b].getBack();
                } while (b != -1);
                String res = "";
                for (i -= 1; i >= 0; i--) {
                    res += reverse[i] + " ";
                }
                return res.trim();
            }
            Adjacency a = n.getHead();
            if (a != null) {
                do {
                    int dist = n.getDist() + a.getWeight();
                    if (dist < list[a.getNode()].getDist()) {
                        pq.del(list[a.getNode()]);
                        list[a.getNode()].setDist(dist);
                        list[a.getNode()].setBack(n.getParent());
                        pq.push(list[a.getNode()]);
                    }
                    if (a.getNext() == null) {
                        break;
                    }
                    a = a.getNext();
                } while (true);
            }
        }
        return (from + 1) + " -1 " + (to + 1);
    }

    /**
     * Print a represenation of the AdjacencyList to stdout
     */
    public void display () {
        for (int i = 0; i < list.length; i++) {
            System.out.print((i) + ": ");
            if (list[i] != null) {
                Adjacency a = list[i].getHead();
                if (a != null) {
                    System.out.print((a.getNode()) + " ");
                    while (a.getNext() != null) {
                        a = a.getNext();
                        System.out.print((a.getNode()) + " ");
                    }
                }
            }
            System.out.println();
        }
    }
}

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;


class Node {
	int data;

	Node(int value) {
		this.data = value;
	}
}

public class Dag 
{
    
    private int V;           // number of vertices in this graph
	private int E;                 // number of edges in this graph
	private ArrayList<Integer>[] adj;    // adj[v] = adjacency list for vertex v
	public boolean DAGShawty;
	public boolean[] marked; //boolean array for visited vertices
	public boolean[] stack; 
    


    public Dag(int V)
    {
		if (V < 0) throw new IllegalArgumentException("Number of vertices in a DAG must be non-negative");
        this.V = V;
        this.E = 0;
        DAGShawty = true;
		marked = new boolean[V];
		stack = new boolean[V];

		adj = (ArrayList<Integer>[]) new ArrayList[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new ArrayList<Integer>();
		}

    }


        public int findLCA(int x, int y){

            validateVertex(x);
            validateVertex(y);
            isAcyclic();
		    if(!DAGShawty){
			return -1;
		    }
            if(E==0)
            {
                return-1;
            }
            
            //Check if acyclical
            //Check if DAG
            boolean LCAExists = false;
            Dag reverseDAG = this.reverse();

            ArrayList<Integer> bluePath = reverseDAG.BFS(x);
            ArrayList<Integer> redPath = reverseDAG.BFS(y);
            ArrayList<Integer> purplePath = new ArrayList();

            for(Integer blueAncestors:bluePath){
                for(Integer redAncestors:redPath)
                {
                    if(redAncestors==blueAncestors){
                        purplePath.add(redAncestors);       
                        LCAExists = true;
                    }
                }
            }

           
        	if(LCAExists) 
    		{
    			return purplePath.get(0);
    		}
    			
    		else 
    		{
    			return -1;
    		}

        }




        public int V() {
            return V;
        }
    
        /**
         * Returns the number of edges in this graph.
         *
         * @return the number of edges in this graph
         */
        public int E() {
            return E;
        }
    
        public void validateVertex(int v) {
            if (v < 0 || v >= V)
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));	
    
        }
    
        /**
         * Adds the directed edge v-w to this graph.
         *
         * @param  v the tail vertex
         * @param  w the head vertex
         * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
         */
        public void addEdge(int v, int w) {
            validateVertex(v);
            validateVertex(w);
            adj[v].add(w);
            E++;
        }
    
        /**
         * Returns the vertices adjacent from vertex {@code v} in this graph.
         *
         * @param  v the vertex
         * @return the vertices adjacent from vertex {@code v} in this graph, as an iterable
         * @throws IllegalArgumentException unless {@code 0 <= v < V}
         */
        public Iterable<Integer> adj(int v) {
            validateVertex(v);
            return adj[v];
        }

        public boolean acyclic()
        {
            return DAGShawty;	
        }
    
        public void isAcyclic()
        {
            for(int i=0; i<V()&& DAGShawty;i++)
            {
                stack = new boolean[V];
                marked= new boolean[V];
                acyclic(i);
            }
        }
        
        private void acyclic(int v)
        {
            stack[v] =true; 
            marked[v] = true;
    
            for (int w : adj(v)) {
                if(!marked[w]) {
                    acyclic(w);
                } else if (stack[w]) {
                    DAGShawty = false;
                    return;
                }
            }
            stack[v] = false;
        } 
	public int getVertexDepth(int start, int destination) {
		Stack<Integer> visited = new Stack<Integer>();
		int depth = -1;
		depth = getVertexDepthRecursive(start, destination, visited, depth);
		return depth;
	}

	private int getVertexDepthRecursive(int currentVertex, int destinationVertex, Stack<Integer> visited, int depth) {
		visited.push(currentVertex);
		if(currentVertex == destinationVertex) {
			depth = visited.size()-1;
		}
		for(Integer i : this.adj(currentVertex)) {
			if (!visited.contains(i)) {
				depth = getVertexDepthRecursive(i, destinationVertex, visited, depth);
				if (!visited.empty()) {
					visited.pop();
				}
			}
		}
		return depth;
	}
    public Dag reverse() {
		Dag reverse = new Dag(V);
		for (int v = 0; v < V; v++) {
			for (int w : adj(v)) {
				reverse.addEdge(w, v);
			}
		}
		return reverse;
	}
    public ArrayList<Integer> BFS(int s) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		marked = new boolean[this.V()];
		path = bfs(path, s);
		return path;
	}

	private ArrayList<Integer> bfs(ArrayList<Integer> path, int s) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		marked[s] = true;
		queue.add(s);
		while (!queue.isEmpty()) {
			s = queue.poll();					// dequeue a vertex from queue
			path.add(s);
			for (int w : this.adj(s)) {
				if (!marked[w]) {
					marked[w] = true;
					queue.add(w);
				}
			}
		}
		return path;	
	}
        
    
    
}

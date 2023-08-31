

import java.util.NoSuchElementException;
import java.util.HashMap;
import java.util.LinkedList;

public class BetterDiGraph implements EditableDiGraph {
	
	private int V;
    private int E;
    private HashMap<Integer, LinkedList<Integer>> adj;
    
    public BetterDiGraph() {
        this.V = 0;
        this.E = 0;
        this.adj = new HashMap<>();
    }

	@Override
	public void addEdge(int v, int w) {
		if (!adj.containsKey(v)) {
            addVertex(v);
        }

        if (!adj.containsKey(w)) {
            addVertex(w);
        }

        adj.get(v).push(w);
        E++;
		
	}

	@Override
	public void addVertex(int v) {
		if (adj.containsKey(v)) {
			return; 
		}
		if (!adj.containsKey(v)) {
            adj.put(v, new LinkedList<>());
            V++;
        }
		
	}

	@Override
	public Iterable<Integer> getAdj(int v) {
		return adj.get(v);
	}

	@Override
	public int getEdgeCount() {
		return this.E;
	}

	@Override
	public int getIndegree(int v) throws NoSuchElementException {
		if (!adj.containsKey(v)) {
            throw new NoSuchElementException();
        }

        int count = 0;

        for (Integer i : vertices()) {
                if (adj.get(i).contains(v)) {
                    count++;
            }
        }

        return count;
    }

	@Override
	public int getVertexCount() {
		return this.V;
	}

	@Override
	public void removeEdge(int v, int w) {
		if  ( !adj.containsKey(v) ) {
			return; 
		}
		
		
		if  ( !adj.containsKey(w) ) {
			return;
		}
		
		adj.get(v).remove(w);
		E--;
	}

	@Override
	public void removeVertex(int v) {
		if  ( !adj.containsKey(v) ) {
			return; 
		}
		for(int w : adj.keySet()) {
            while(adj.get(w).contains(v)){
                adj.get(w).removeFirstOccurrence(v);
                this.E--;
            }
        }
        if(adj.containsKey(v)) {
            adj.remove(new Integer(v));
            V--;
        }
	}

	@Override
	public Iterable<Integer> vertices() {
		return adj.keySet();
	}

	@Override
	public boolean isEmpty() {
		return this.V == 0;
	}

	@Override
	public boolean containsVertex(int v) {
		return adj.containsKey(v);
	}

}

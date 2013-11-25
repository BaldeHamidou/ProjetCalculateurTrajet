package calculateur.implementations;

import java.util.ArrayList;
import java.util.List;

import calculateur.interfaces.IGraphEdge;
import calculateur.interfaces.IGraphNode;


public class GraphNode<T> implements IGraphNode<T> {
	List<IGraphEdge<T>> edges = new ArrayList<IGraphEdge<T>>();
	T value;

	public GraphNode(T name) {
		value = name;
	}
	
	@Override
	public T getValue() {
		return value;
	}

	@Override
	public List<IGraphEdge<T>> getNeighbors() {
		return edges;
	}

	@Override
	public void addEdge(IGraphEdge<T> edge) {
		edges.add(edge);
	}

	@Override
	public String toString() {
		return value.toString();
	}
	
	
}

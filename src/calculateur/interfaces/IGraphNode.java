package calculateur.interfaces;
import java.util.List;

public interface IGraphNode<T> {
	public T getValue();
	public List<IGraphEdge<T>> getNeighbors();
	public void addEdge(IGraphEdge<T> edge);
}

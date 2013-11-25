//package calculateur.implementations;
//
//import calculateur.interfaces.IGraphEdge;
//import calculateur.interfaces.IGraphNode;
//
//
//
//public class GraphEdge<T> implements IGraphEdge<T>{
//
//	IGraphNode<T> a, b;
//	int cost;
//	
//	public GraphEdge(IGraphNode<T> a, IGraphNode<T> b, int cost) {
//		super();
//		this.a = a;
//		this.b = b;
//		this.cost = cost;
//	}
//
//	@Override
//	public IGraphNode<T> getExtremityA() {
//		return a;
//	}
//
//	@Override
//	public IGraphNode<T> getExtremityB() {
//		return b;
//	}
//
//	@Override
//	public int getCost() {
//		return cost;
//	}
//
//	@Override
//	public String toString() {
//		return a + "->" + b;
//	}
//	
//	
//
//}

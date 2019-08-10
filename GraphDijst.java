import java.io.*;
import java.util.*;

class Node{

	int nodeid;
	int dist_from_zero;

	Node(int nodeid){
		this.nodeid = nodeid;	
		this.dist_from_zero = Integer.MAX_VALUE;
	}

	LinkedList <AdjNode>  ladn = new LinkedList<AdjNode>();
}

class AdjNode{

	public int adjdist;
	public int nodeid;

	AdjNode(int nodeid, int adjdist){
		this.nodeid = nodeid;
		this.adjdist = adjdist;
	}
}


class GraphDijst{

	static HashMap<Integer,Node> hmap = new HashMap<Integer, Node>();
	static Set<Integer> hash_set = new HashSet<Integer>();

	static void addNode(Node nobj, int adjnodeid, int dist){

		nobj.ladn.add(new AdjNode(adjnodeid, dist));

	}


	public static Node minimun_node(){
		int n = Integer.MAX_VALUE;
		int index = 0;
		for(int i=0; i<hmap.size();i++){

			if(!hash_set.contains(hmap.get(i).nodeid) && n >= hmap.get(i).dist_from_zero){
				index = i;
				n = hmap.get(i).dist_from_zero;
			}
		}
		return hmap.get(index);
	}


	public static void main(String[]args){


		Node nobj0 = new Node(0);
		nobj0.dist_from_zero = 0;
		addNode(nobj0, 1, 3);
		addNode(nobj0, 2, 4);

		hmap.put(0,nobj0);

		Node nobj1 = new Node(1);
		addNode(nobj1, 0, 3);
		addNode(nobj1, 2, 2);
		addNode(nobj1, 3, 1);
		hmap.put(1,nobj1);

		Node nobj2 = new Node(2);
		addNode(nobj2, 0, 4);
		addNode(nobj2, 1, 2);
		addNode(nobj2, 3, 2);
		addNode(nobj2, 4, 4);
		hmap.put(2,nobj2);

		Node nobj3 = new Node(3);
		addNode(nobj3, 1, 1);
		addNode(nobj3, 2, 2);
		addNode(nobj3, 4, 1);
		addNode(nobj3, 5, 8);
		hmap.put(3,nobj3);

		Node nobj4 = new Node(4);
		addNode(nobj4, 5, 15);
		addNode(nobj4, 2, 4);
		addNode(nobj4, 3, 1);
		hmap.put(4,nobj4);

		Node nobj5 = new Node(5);
		addNode(nobj5, 3, 8);
		addNode(nobj5, 4, 15);
		hmap.put(5,nobj5);




		for(int i =0;i<6;i++)
		{
			Node nobj = hmap.get(i);

			LinkedList<AdjNode> adjnd = nobj.ladn;

			for(int j=0	; j<adjnd.size(); j++){

					System.out.println("Node ID: "+Integer.toString(nobj.nodeid)+" Adj Node: "+Integer.toString(adjnd.get(j).nodeid)+" Dist: "+Integer.toString(adjnd.get(j).adjdist));


			}
			System.out.println("============================================================");

		}




		hash_set.add(0);
		Node tmpnode = nobj0;
		AdjNode adjtmp = new AdjNode(0,Integer.MAX_VALUE);

		while(hash_set.size() <6){

			System.out.println(hash_set);
			LinkedList<AdjNode> adjlst = tmpnode.ladn;

			for(int i =0 ; i<adjlst.size(); i++){
					AdjNode adjnd = adjlst.get(i);
					Node ntmp = hmap.get(adjnd.nodeid);

					if( !hash_set.contains(adjnd.nodeid) && tmpnode.dist_from_zero +  adjnd.adjdist < ntmp.dist_from_zero ){
						ntmp.dist_from_zero = tmpnode.dist_from_zero +  adjnd.adjdist;
					}
			}


			//find new node for new iteration
			tmpnode = minimun_node();
			hash_set.add(tmpnode.nodeid);
			System.out.println("Size of Set: "+Integer.toString(hash_set.size()));

		}





		for(int i =0;i<6;i++)
		{
			Node nobj = hmap.get(i);

			LinkedList<AdjNode> adjnd = nobj.ladn;
			System.out.println("NodeID: "+Integer.toString(nobj.nodeid)+" Dist_From_Zeroth: "+Integer.toString(nobj.dist_from_zero));
			for(int j=0	; j<adjnd.size(); j++){

					System.out.println("Node ID: "+Integer.toString(nobj.nodeid)+" Adj Node: "+Integer.toString(adjnd.get(j).nodeid)+" Dist: "+Integer.toString(adjnd.get(j).adjdist));


			}
			System.out.println("============================================================");

		}










	}

}
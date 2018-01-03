import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;
public class SAP {
    Digraph g;
    BreadthFirstDirectedPaths bfdp, bfdps;
    public SAP(Digraph G) {
        this.g = new Digraph(G);
    }
    public int length(int v, int w) {
        if (v == w) {
            return 0;
        }
        bfdp = new BreadthFirstDirectedPaths(g, v);
        bfdps = new BreadthFirstDirectedPaths(g, w);
        int graphSize = g.V();
        int smallestLen = Integer.MAX_VALUE;
        for (int i = 0; i < graphSize; i++) {
            if (bfdp.hasPathTo(i) && bfdps.hasPathTo(i)) {
                int newLen = bfdp.distTo(i) + bfdps.distTo(i);
                if (newLen < smallestLen) {
                    smallestLen = newLen;
                }
            }
        }
        if (smallestLen == Integer.MAX_VALUE) {
            return -1;
        }
        return smallestLen;
    }
    public int length(Set<Integer> v, Set<Integer> w) {
        if (v == w) {
            return 0;
        }
        bfdp = new BreadthFirstDirectedPaths(g, v);
        bfdps = new BreadthFirstDirectedPaths(g, w);
        int graphSize = g.V();
        int smallestLen = Integer.MAX_VALUE;
        for (int i = 0; i < graphSize; i++) {
            if (bfdp.hasPathTo(i) && bfdps.hasPathTo(i)) {
                int newLen = bfdp.distTo(i) + bfdps.distTo(i);
                if (newLen < smallestLen) {
                    smallestLen = newLen;
                }
            }
        }
        if (smallestLen == Integer.MAX_VALUE) {
            return -1;
        }
        return smallestLen;
    }
    public int ancestor(int v, int w) {
        if (v == w) {
            return v;
        }
        bfdp = new BreadthFirstDirectedPaths(g, v);
        bfdps = new BreadthFirstDirectedPaths(g, w);
        int graphSize = g.V();
        int smallestLen = Integer.MAX_VALUE;
        int ans = -1;
        for (int i = 0; i < graphSize; i++) {
            if (bfdp.hasPathTo(i) && bfdps.hasPathTo(i)) {
                int newLen = bfdp.distTo(i) + bfdps.distTo(i);
                if (newLen < smallestLen) {
                    smallestLen = newLen;
                    ans = i;
                }
            }
        }
        if (smallestLen == Integer.MAX_VALUE) {
            return -1;
        }
        return ans;
    }
    public int ancestor(Set<Integer> v, Set<Integer> w) {
        bfdp = new BreadthFirstDirectedPaths(g, v);
        bfdps = new BreadthFirstDirectedPaths(g, w);
        int graphSize = g.V();
        int smallestLen = Integer.MAX_VALUE;
        int ans = -1;
        for (int i = 0; i < graphSize; i++) {
            if (bfdp.hasPathTo(i) && bfdps.hasPathTo(i)) {
                int newLen = bfdp.distTo(i) + bfdps.distTo(i);
                if (newLen < smallestLen) {
                    smallestLen = newLen;
                    ans = i;
                }
            }
        }
        if (smallestLen == Integer.MAX_VALUE) {
            return -1;
        }
        return ans;
    }
    public static void main(String args[]) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP s = new SAP(G);
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(args[1]));
            String opiu = bufferedReader.readLine();
            while (opiu != null){
                String[] parsed = opiu.split(" ");
                int v = Integer.parseInt(parsed[0]);
                int w = Integer.parseInt(parsed[1]);
                int anc = s.ancestor(v, w);
                int len = s.length(v, w);
                StdOut.printf("sap = %d, ancestor = %d\n", len, anc);
                opiu = bufferedReader.readLine();
            }
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}

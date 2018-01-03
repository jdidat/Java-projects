import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class WordNet {
    public static Map<Integer, String> syn;
    public static Map<String, Set<Integer>> hyp;
    public static SAP sap;
    public static String ans;
    public WordNet(String synsets, String hypernyms) {
        syn = new HashMap<>();
        hyp = new HashMap<>();
        In file = new In(synsets);
        while (file.hasNextLine()) {
            String[] l = file.readLine().split(",");
            Integer idSyn = Integer.valueOf(l[0]);
            String n = l[1];
            syn.put(idSyn, n);
            String[] nouns = n.split(" ");
            for (String noun : nouns) {
                Set<Integer> ids = hyp.get(noun);
                if (ids == null) {
                    ids = new HashSet<>();
                }
                ids.add(idSyn);
                hyp.put(noun, ids);
            }
        }
        Digraph graph = new Digraph(syn.size()+1);
        In file1 = new In(hypernyms);
        while (!file1.isEmpty()) {
            String[] line1 = file1.readLine().split(",");
            Integer synsetId = Integer.valueOf(line1[0]);
            for (int i = 1; i < line1.length; i++) {
                Integer id1 = Integer.valueOf(line1[i]);
                graph.addEdge(synsetId, id1);
            }
        }
        sap = new SAP(graph);
    }
    public boolean isNoun(String word) {
        if (word == null || word.equals("")) {
            return false;
        }
        return hyp.containsKey(word);
    }
    public void printSAP(String nounA, String nounB) {
        if (nounA == null || nounB == null) {
            return;
        }
        Set<Integer> idsOfNounA = hyp.get(nounA);
        Set<Integer> idsOfNounB = hyp.get(nounB);
        int length = sap.length(idsOfNounA, idsOfNounB);
        int ansc = sap.ancestor(idsOfNounA, idsOfNounB);
        if (length == -1) {
            ans = "null";
        }
        else {
            ans = syn.get(ansc);
        }
    }
    public static void main(String args[]) {
        WordNet wN = new WordNet(args[0], args[1]);
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(args[2]));
            String opiu = bufferedReader.readLine();
            while (opiu != null){
                String[] parsed = opiu.split(" ");
                String v = parsed[0];
                String w = parsed[1];
                if (!wN.isNoun(v) || !wN.isNoun(w)) {
                    StdOut.printf("sap = -1, ancestor = null\n");
                    opiu = bufferedReader.readLine();
                    continue;
                }
                Set<Integer> nounAID = hyp.get(v);
                Set<Integer> nounBID = hyp.get(w);
                int len = sap.length(nounAID, nounBID);
                wN.printSAP(v, w);
                StdOut.printf("sap = %d, ancestor = %s\n", len, ans);
                opiu = bufferedReader.readLine();
            }
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}

import java.util.*; 
 
public class LangkahResep { 
    private Map<String, List<String>> adjList; 
 
    public LangkahResep() { 
        adjList = new HashMap<>(); 
    } 
 
    public void addEdge(String src, String dest) { 
        adjList.putIfAbsent(src, new ArrayList<>()); 
        adjList.putIfAbsent(dest, new ArrayList<>());  
        adjList.get(src).add(dest); 
        adjList.get(dest).add(src); 
    }

        public List<String> bfs(String start) { 
        List<String> result = new ArrayList<>(); 
        Set<String> visited = new HashSet<>(); 
        Queue<String> queue = new LinkedList<>(); 
 
        visited.add(start); 
        queue.add(start); 
 
        while (!queue.isEmpty()) { 
            String node = queue.poll(); 
            result.add(node); 
 
            List<String> neighbors = new ArrayList<>(adjList.get(node)); 
            Collections.sort(neighbors);  
            for (String neighbor : neighbors) { 
                if (!visited.contains(neighbor)) { 
                    visited.add(neighbor); 
                    queue.add(neighbor); 
                } 
            } 
        } 
 
        return result; 
    }

      public List<String> dfs(String start) { 
        List<String> result = new ArrayList<>(); 
        Set<String> visited = new HashSet<>(); 
        Stack<String> stack = new Stack<>(); 
 
        stack.push(start); 
 
        while (!stack.isEmpty()) { 
            String node = stack.pop(); 
            if (!visited.contains(node)) { 
                visited.add(node); 
                result.add(node);
                List<String> neighbors = new ArrayList<>(adjList.get(node)); 
                Collections.sort(neighbors, Collections.reverseOrder());  
                for (String neighbor : neighbors) { 
                    if (!visited.contains(neighbor)) { 
                        stack.push(neighbor); 
                    } 
                } 
            } 
        } 
 
        return result; 
    } 

       public void tampilkanInfoTraversal(String nama, List<String> hasil) { 
        System.out.println(nama + " traversal: "); 
        for (int i = 0; i < hasil.size(); i++) { 
            System.out.print(hasil.get(i)); 
            if (i < hasil.size() - 1) { 
                System.out.print(" -> "); 
            } 
        } 
        System.out.println(); 
 
        if (!hasil.isEmpty()) { 
            System.out.println("Awal: " + hasil.get(0)); 
            System.out.println("Tengah: " + hasil.get(hasil.size() / 2)); 
            System.out.println("Akhir: " + hasil.get(hasil.size() - 1)); 
        } 
        System.out.println(); 
    } 
 
    public static void main(String[] args) { 
        LangkahResep graph = new LangkahResep(); 
 
        graph.addEdge("preheat oven", "bake bread"); 
        graph.addEdge("preheat oven", "preheat pan"); 
        graph.addEdge("bake bread", "save bread"); 
        graph.addEdge("preheat pan", "add karbby patty"); 
        graph.addEdge("save bread", "eat");
        graph.addEdge("preheat pan", "set plate");
        graph.addEdge("add karbby patty", "save bread");
        graph.addEdge("set plate", "add some pickles");
        graph.addEdge("add some pickles", "eat");
        graph.addEdge("save plate", "save patty");
        graph.addEdge("add krabby patty", "save patty");
        graph.addEdge("save patty", "pour souce over patty");
        graph.addEdge("save krabby patty", "add tartar sauce");
        graph.addEdge("add tartar sauce", "pour sauce over patty");
        graph.addEdge("pour sauce over patty", "eat");
   
 
        List<String> bfsResult = graph.bfs("preheat oven"); 
        List<String> dfsResult = graph.dfs("preheat oven"); 
 
        graph.tampilkanInfoTraversal("BFS", bfsResult); 
        graph.tampilkanInfoTraversal("DFS", dfsResult); 
    }
}

import java.util.LinkedList;

public class BloodGroup {
    private static int source = 0;
    private static int destination = 9;
    private static int noOfVertices = 10;
    private boolean bfs(int[][] flowNetwork,int [] parentOfVisited) {
        boolean visited[] = new boolean[noOfVertices];
        for(int i=0; i<noOfVertices; ++i)
            visited[i]=false;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parentOfVisited[source] = -1;
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < noOfVertices; v++) {
                if ( flowNetwork[u][v] > 0 && !visited[v] ) {
                    queue.add(v);
                    parentOfVisited[v] = u;
                    visited[v] = true;
                }
            }
        }
        return (visited[destination]);

    }
    public static void main(String[] args) {
        BloodGroup bloodGroup = new BloodGroup();
        int max = Integer.MAX_VALUE;

        int flowNetwork[][] = { {0,50,36,11 ,8 ,0,0,0,0,0},
                {0,0,0,0 ,0 ,max,max,max,max,0},
                {0,0,0,0 ,0 ,0,max,0,max,0},
                {0,0,0,0 ,0 ,0,0,max,max,0},
                {0,0,0,0 ,0 ,0,0,0,max,0},
                {0,0,0,0 ,0 ,0,0,0,0,45},
                {0,0,0,0 ,0 ,0,0,0,0,42},
                {0,0,0,0 ,0 ,0,0,0,0,8},
                {0,0,0,0 ,0 ,0,0,0,0,3},
                {0,0,0,0 ,0 ,0,0,0,0,0}

        };
        int res=bloodGroup.FindingMaxFlow(flowNetwork);
        System.out.println("The max flow is ");
        System.out.println(res);
    }

    private int FindingMaxFlow(int[][] flowNetwork) {
        int maxFlow=0;
        int indMinFlow;
        int u;
        int parentOfVisited[] = new int[noOfVertices];
        int[][] residualGraph = new int[flowNetwork.length][flowNetwork.length];
        for (int lV = 0; lV < flowNetwork.length; lV++) {
            for (int lV2 = 0; lV2 < flowNetwork.length; lV2++) {
                residualGraph[lV][lV2] = flowNetwork[lV][lV2];
            }
        }
        while (bfs(residualGraph, parentOfVisited))
        {
             indMinFlow = Integer.MAX_VALUE;
            for (int v=destination; v!=source; v=parentOfVisited[v])
            {
                u = parentOfVisited[v];
                indMinFlow = Math.min( residualGraph[u][v], indMinFlow);
            }

            for (int v=destination; v != source; v=parentOfVisited[v])
            {
                u = parentOfVisited[v];
                residualGraph[u][v] = residualGraph[u][v] - indMinFlow;
                residualGraph[v][u] = residualGraph[u][v] + indMinFlow;
            }
            System.out.println("Max flow for current path"+indMinFlow);
            maxFlow += indMinFlow;
            System.out.println("Graph After every iteration");
            System.out.println("******************************");
            printGrid(flowNetwork);
        }

        boolean[] Vis = new boolean[noOfVertices];
        for(int i=0; i<noOfVertices; ++i) {
            Vis[i] = false;
        }
        dfs(residualGraph, source, Vis);
        System.out.println("The Min Cut is along the below mentioned edges");
        for (int i = 0; i < flowNetwork.length; i++) {
            for (int j = 0; j < flowNetwork.length; j++) {
                if (flowNetwork[i][j] > 0 && Vis[i] && !Vis[j]) {
                    System.out.println(i + " - " + j);
                }
            }
        }

        return maxFlow;
    }
    private static void dfs(int[][] residualGraph, int s,
                            boolean[] visited) {
        visited[s] = true;
        for (int i = 0; i < noOfVertices; i++) {
            if (residualGraph[s][i] > 0 && !visited[i]) {
                dfs(residualGraph, i, visited);
            }
        }
    }



    private void printGrid(int[][] flowNetwork)
    {
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                System.out.printf("%5d ",flowNetwork [i][j]);
            }
            System.out.println();
        }
    }

}

package bellmanford;


import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 
class Grafo
{
 //questa classe rappresenta la struttura di un grafo.
    
    class Edge {
        int src, dest, weight;
        Edge() {
            src = dest = weight = 0;
        }
    };
 
    int V, E;
    Edge edge[];
 
   
    Grafo(int v, int e)
    {
        V = v;
        E = e;
        edge = new Edge[e];
        for (int i=0; i<e; ++i)
            edge[i] = new Edge();
    }
 
    
    void BellmanFord(Grafo grafo,int src)
    {
        int V = grafo.V, E = grafo.E;
        int[] distanze = new int[V];
 
        
        for (int i=0; i<V; ++i)
            distanze[i] = Integer.MAX_VALUE;
        distanze[src] = 0;
 
        
        for (int i=1; i<V; ++i)
        {
            for (int j=0; j<E; ++j)
            {
                int u = grafo.edge[j].src;
                int v = grafo.edge[j].dest;
                int weight = grafo.edge[j].weight;
                if (distanze[u]!=Integer.MAX_VALUE &&
                    distanze[u]+weight<distanze[v])
                    distanze[v]=distanze[u]+weight;
            }
        }
 
       
        for (int j=0; j<E; ++j)
        {
            int u = grafo.edge[j].src;
            int v = grafo.edge[j].dest;
            int weight = grafo.edge[j].weight;
            if (distanze[u] != Integer.MAX_VALUE &&
                distanze[u]+weight < distanze[v])
              System.out.println("Il grafo contiene un ciclo negativo");
        }
        printArr(distanze, V);
    }
 
    void printArr(int[] distanze, int V)
    {
        System.out.println("Nodo   Distanza dal nodo di partenza");
        String output = "";
        for (int i=0; i<V; ++i){
            System.out.println(i+"\t\t"+distanze[i]);
            output+=(i+" = "+distanze[i]);
            output+='\n';
        }
        stampa(output);
    }
 
   
    public static void main(String[] args)
    {
        try{
        Grafo grafo = input();
        grafo.BellmanFord(grafo, 0);
        }catch(Exception e){
            System.out.println("errore nella lettura del file");
            System.out.println(e.toString());
        }
        
        
    }
    
    public void stampa(String output){
        try{
        File file = new File("output.txt");
        if(file.exists())
            file.delete();
        file.createNewFile();
        
        FileWriter fw = new FileWriter(file);
        fw.write(output);
        fw.flush();
        fw.close();
        }catch(Exception e){
            System.out.println("errore nell'output "+ e.toString());
        }
    }
    
    public static Grafo input() throws FileNotFoundException, IOException{
        BufferedReader b = new BufferedReader(new FileReader(new File("input.txt")));
        int nodi = Integer.parseInt(b.readLine());
        int collegamenti = Integer.parseInt(b.readLine());
        
        Grafo graph = new Grafo(nodi, collegamenti);
        
        for(int i=0; i<collegamenti; i++){

            String a = b.readLine();
            String []valori;
            valori = a.split(" ");
            graph.edge[i].src = Integer.parseInt(valori[0]);
            graph.edge[i].dest = Integer.parseInt(valori[1]);
            graph.edge[i].weight = Integer.parseInt(valori[2]);
            
        }
        return graph;
        
    }
}

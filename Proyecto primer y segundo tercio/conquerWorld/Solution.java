package conquerWorld;

import java.util.*;

public class Solution {
    private static Scanner in;
    private static int nations,u,v,c,x,y;
    private static int[][] path,mat;
    private static int[] xI,yI;
    private static final int  max_int = 429496729;
    private static Vector<Integer> donantes,necesitados;
    
    public static void main(String[] args) {
        in = new Scanner(System.in);
        nations = in.nextInt();
        path = new int[nations+1][nations+1]; mat = new int[nations+1][nations+1];
        xI = new int[nations+1]; yI = new int[nations+1];
        donantes = new Vector<Integer>(); necesitados = new Vector<Integer>();
        //llenamos la matriz de distancias de  infinitos
        for(int i=0;i<=nations;i++) {
            for(int j=0;j<=nations;j++) {
                if(i==j) { mat[i][j]=0;}
                else { mat[i][j]=max_int;}
                path[i][j]=i;
            }
        }
        for(int i=1;i<nations;i++) {
            u = in.nextInt(); v=in.nextInt(); c=in.nextInt();
            mat[u][v] = mat[v][u] = c;
        }
        for(int k=1;k<=nations;k++) {
            x = in.nextInt(); y = in.nextInt();
            xI[k] = x; yI[k] = y;
            if(x>y) donantes.add(k);
            else if(x < y) necesitados.add(k);
        }
        floyd_Warshall();
        System.out.println(solve());
        in.close();
    }
    
    public Solution(){}
    
    public static void floyd_Warshall() {
        for(int k=0;k<=nations;k++) {
            for(int i=0;i<=nations;i++) {
                for(int j=0;j<=nations;j++) {
                    if(mat[i][j]>mat[i][k]+mat[k][j]) {
                        mat[i][j]=mat[i][k]+mat[k][j];
                        path[i][j]=path[k][j];
                    }
                }
            }
        }
    }

    public static int flowPath(int source, int target,int value) {
        int cost=mat[source][target];
        while(source != target) {

            xI[source]-=value;
            source = path[target][source];
            xI[source]+= value;
            
        }
        return cost;
    }
    public static long solve() {
        int xneed,ydon,ydonpos,value;
        long cost=0;
        while(necesitados.size()>0) {
            xneed=necesitados.get(0);
            ydon = donantes.get(0);
            ydonpos = 0;
            for(int i=0;i<donantes.size();i++) {
                if(mat[donantes.get(i)][xneed] < mat[ydon][xneed]) {
                    ydon = donantes.get(i);
                    ydonpos = i;
                }
            }
            if((xI[ydon]-yI[ydon]) <= (yI[xneed]-xI[xneed])) { value = xI[ydon]-yI[ydon]; }
            else { value = yI[xneed]-xI[xneed]; }
            cost += flowPath(ydon,xneed,value);
            if(xI[xneed]>=yI[xneed]) necesitados.remove(0);
            if(xI[ydon]==yI[ydon]) donantes.remove(ydonpos);
        }
        return cost;
    }
}

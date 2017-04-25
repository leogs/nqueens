/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import algorithms.AStar;
import algorithms.BreadthFirstSearch;
import algorithms.DepthFirstSearch;
import algorithms.GreedySearch;

/**
 *
 * @author Jardeson
 */
public class Game {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        /*
        Board b = new Board(8, 0);
        DepthFirstSearch ds = new DepthFirstSearch(b);
        Board aux = null;
        
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long start = System.currentTimeMillis();
        
        aux = ds.execute();
        
        long time = System.currentTimeMillis() - start;
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed=afterUsedMem-beforeUsedMem;
        
        if (aux == null) {
            System.out.println("Nao Existe Solução para o Tabuleiro de Dimensao "+b.getSize());
        }else{
            System.out.println("Solução");
            aux.printBoard();
            System.out.println("Profundidade da Solução: "+aux.getDepth());
            System.out.println("Quantidade de Nós gerados: "+ds.getNodeCount());
            System.out.println("Memória: "+actualMemUsed);
            System.out.println("Tempo: "+time);
        }
        
        */
        
                
        
        Board b = new Board(8, 0);
        //b.insertQueen(4, 3);
        //b.printBoard();
        AStar ds = new AStar(b);
        Board aux = null;
        
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long start = System.currentTimeMillis();
                
        while (!ds.isSolution()) {            
            aux = ds.execute();
            if (aux == null) {
                break;
            }
        }
        long time = System.currentTimeMillis() - start;
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed=afterUsedMem-beforeUsedMem;
        
        if (aux == null) {
            System.out.println("Nao Existe Solução para o Tabuleiro de Dimensao "+b.getSize());
        }else{
            System.out.println("Solução");
            aux.printBoard();
            System.out.println("Profundidade da Solução: "+aux.getDepth());
            System.out.println("Quantidade de Nós gerados: "+ds.getNodeCount());
            System.out.println("Memória: "+actualMemUsed);
            System.out.println("Tempo: "+time);
        }
        
    }
    
}

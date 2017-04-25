/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import game.Board;
import java.util.ArrayList;

/**
 *
 * @author Jardeson
 */
public class GreedySearch {
    private ArrayList<Board> border;
    private ArrayList<Board> visitedNodes;
    private Board node;
    private int[][] lastState;
    private int nodeCount;
    private int depth;
    
    
    public boolean isSolution() {
        int queenCount = 0;

        // quantidade de rainhas no tabuleiro
        for (int i = 0; i < this.node.getSize(); i++) {
            for (int j = 0; j < this.node.getSize(); j++) {
                if (this.node.getBoard()[i][j] == 2) {
                    queenCount++;
                 }
            }
        }
        // quantidade de rainhas é igual a dimensao da matriz pra ser solucao

        return queenCount == this.node.getSize();
    }
    
    public Board execute(){
        boolean test = false;
        this.border.remove(this.node);
        
        if (isSolution() == false)
        {
            this.nodeCount = this.nodeCount + this.node.generateChildren(); // expandir o nó root
            this.visitedNodes.add(this.node); // adiciona nó root na lista de visitados
            this.addBoard(this.node.getChildren()); // add filhos do root na fronteira
            
            for (Board b : this.node.getChildren()) {
                System.out.println();
                b.printBoard();
                System.out.println();
                
                test = true;
            }
            
            if (test) {
                System.out.println("----------------------------------------------------------------");
            }
            
             
            this.selectNext(); //selecionar proximo root
        }
        return this.node;
    }

    public void addBoard(ArrayList<Board> list){
        boolean test = true;
        
        for (Board b1: list)
        {
            for (Board b2: this.visitedNodes) // percorrer lista de visitados
            {
                if (compareBoard(b1.getBoard(), b2.getBoard()))//verificar se a board a adiconar na fronteira não está na lista de visitadas
                    test = false;
            }
            
            for (Board b2: this.border)//verificar se já está na borda
            {
                if (compareBoard(b1.getBoard(), b2.getBoard()))
                    test = false;
            }
            
            if (test == true)
            {
                this.border.add(b1);
                if (this.depth < b1.getDepth())
                    this.depth = b1.getDepth();
            } else {
                test = true;
            }
        }
    }
    
    public boolean compareBoard(int[][]a, int[][]b){
        for (int i = 0; i<a.length; i++)
        {
            for (int j = 0; j<a.length; j++)
            {
                if (a[i][j] != b[i][j])
                    return false;
            }
        }
        return true;
    }
    
    public void selectNext()
    {
        if (!border.isEmpty()) {
            Board aux = border.get(0);
            int threshold = aux.builtChildrenCount();
            
            for (Board board : border)
            {
                if (board.builtChildrenCount() < threshold)
                {
                    threshold = board.builtChildrenCount();
                    aux = board;
                    
                }
            }

            this.node = aux;
        }else{
            this.node = null;
        }
        
    }
    
    public Board getNode() {
        return node;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public int getDepth() {
        return depth;
    }
    
    public GreedySearch (Board b){
        this.node = b;
        this.visitedNodes = new ArrayList<Board>();
        this.border = new ArrayList<Board>();
        this.border.add(b);
        this.depth = node.getDepth();
        this.nodeCount = 1;
        
        System.out.println("\nTabuleiro Inicial");
        b.printBoard();
        System.out.println("----------------------------------------------------------------");
    }
}

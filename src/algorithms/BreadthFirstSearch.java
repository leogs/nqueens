/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;
import game.Board;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author Jardeson
 */
public class BreadthFirstSearch {
    private Queue<Board> queue;
    private ArrayList<Board> visitedNodes;
    private Board node;
    private int depth;
    private int nodeCount;
    
    public boolean isSolution() {
        int queenCount = 0;

        // quantidade de rainhas no tabuleiro
        for (int i = 0; i < this.node.getSize(); i++) {
            for (int j = 0; j < node.getSize(); j++) {
                if (this.node.getBoard()[i][j] == 2) {
                    queenCount++;
                }
            }
        }
        // quantidade de rainhas é igual a dimensao da matriz pra ser solucao

        return queenCount == this.node.getSize();
    }
    
    
    public Board execute() {
	boolean test = false;
        
        while (this.isSolution() == false) {
            // se fila vazia
            if (this.queue.isEmpty()) {
                return null;    
            }else{
                this.node = queue.remove(); // remove

                this.nodeCount = this.nodeCount+this.node.generateChildren(); // gera filhos
                
                for (Board b : this.node.getChildren()) {
                    this.queue.add(b);

                    if (this.depth < this.node.getDepth()) {
                        this.depth = this.node.getDepth();
                    }
                    
                    System.out.println();
                    b.printBoard();
                    System.out.println();
                    
                    test = true;
                } 
                
                if (test) {
                    System.out.println("---------------------------------------------------------");
                    test = false;
                }
            }
        }

        //
        return this.node ;		
    }

    public int getDepth() {
        return depth;
    }

    public int getNodeCount() {
        return nodeCount;
    }
    
    public BreadthFirstSearch(Board board)
    {
        this.queue = new LinkedList<>();
        this.queue.add(board);
        this.visitedNodes = new ArrayList<>();
        this.node = board;
        this.depth = node.getDepth();
        nodeCount = 1;
        
        System.out.println("\nTabuleiro Inicial");
        board.printBoard();
        System.out.println("---------------------------------------------------------");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import game.Board;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Jardeson
 */
public class DepthFirstSearch {
    private Stack<Board> stack;
    private ArrayList<Board> visitedNodes; 
    private Board node;
    private int depth; // profundidade da arvore
    private int nodeCount;
    
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
        // se for igual a dimensão do tabuleiro? solução encontrada

        return queenCount == this.node.getSize();
    }

    public Board execute() {
	boolean test = false;	
        
        while (this.isSolution() == false) {
            if (this.stack.isEmpty()) {
                return null;    
            }else{
                this.node = stack.pop(); // remove

                nodeCount = nodeCount+this.node.generateChildren();
                System.out.println(nodeCount);
                // add filho na fila
                for (Board b : this.node.getChildren()) {
                    this.stack.push(b);

                    if (this.depth < this.node.getDepth()) {
                        this.depth = node.getDepth();
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

        return this.node ;		
    }

    public int getDepth() {
        return depth;
    }

    public int getNodeCount() {
        return nodeCount;
    }
    
    public DepthFirstSearch(Board board) {
        this.stack = new Stack<Board>();
        this.stack.push(board);
        this.visitedNodes = new ArrayList<Board>();
        this.node = board;
        nodeCount = 0;
        
        System.out.println("\nTabuleiro Inicial");
        board.printBoard();
        System.out.println("---------------------------------------------------------");
    }
}

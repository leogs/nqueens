/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;

/**
 *
 * @author Jardeson
 */
public class Board {
    private int [][] board; //tabuleiro
    private int size; //dimensão do tabuleiro
    private ArrayList<Board> children; //filhos do tabuleiro
    private int depth; //profundidade da solução
    
    //reseta board para estado inicial sem rainhas (espaço com 0)
    public void eraseBoard(){
        for (int i=0; i<this.size; i++){
            for (int j=0; j<this.size; j++){
                this.board[i][j] = 0;
            }
        }
    }
    
    //verifica se existe alguma rainha (espaço com 2) no tabuleiro
    public boolean isItEmpty(int x, int y){
        boolean empty = true;
        if(this.board[x][y] == 2){
            empty = false;
        }
        return empty;
    }
    
    //insere rainha no tabuleiro
    public void insertQueen (int row, int column){
        
        //Define a linha e coluna como ocupadas (marcadas com 1)
        for (int i=0; i<this.size; i++){
            this.board[row][i] = 1;
            this.board[i][column] = 1;
        }
        
        //Define as diagonais como ocupadas (marcadas com 1)
        for (int i = row, j = column; (i<this.size && j<this.size); i++, j++){
            this.board[i][j] = 1;
        }
        
        for (int i = row, j = column; (i>=0 && j<this.size); i--, j++){
            this.board[i][j] = 1;
        }
        
        for (int i = row, j = column; (i>=0 && j>=0); i--, j--){
            this.board[i][j] = 1;
        }
        
        for (int i = row, j = column; (i<this.size && j>=0); i++, j--){
            this.board[i][j] = 1;
        }
        
        //Coloca a rainha na posição dela
        this.board[row][column] = 2;
    }
    
    //Gera todos os filhos do nó da árvore dada a posição atual do tabuleiro
    public int generateChildren(){
        int count=0;
        
        for (int i=0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j] == 0) {
                    //System.out.println("aqui");
                    Board child = new Board(this.size, this.depth+1, this.returnMatrix());
                    child.insertQueen(i, j);
                    this.children.add(child);
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public int[][] returnMatrix(){
        int[][] aux = new int[size][size];
        
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                aux[i][j] = board[i][j];
            }
        }
        
        return aux;
    }
    
    //retorna o tabuleiro (matriz de int)
    public int[][] getBoard(){
        return this.board;
    }
    
    public int builtChildrenCount() {
        int count=0;
        
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j] == 0) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    //retorna quantidade de rainhas atualmente no tabuleiro
    public int howManyQueens(){
        int count = 0;
        
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j] == 2) {
                   count++; 
                }
            }
        }
        
        return count;
    }
    
    public int emptyColumns(){
        int[] col = new int[size];
        
        for (int i = 0; i < this.size; i++) {
            col[i] = 0;
        }
        
        int k=0;
        int count=0;
        
        for (int j = 0; j < this.size; j++) {
            for (int i = 0; i < this.size; i++) {
                if (this.board[i][j] == 0) {
                   col[k]=j+1;
                }
            }
            k++;
        }
        
        for (int i = 0; i < col.length; i++) {
            if (col[i]!=0) {
                count++;
            }
        }
        
        return count;
    }
 
    
    //Para debugging, printar o tabuleiro no prompt
    public void printBoard(){
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    //retorna dimensão do tabuleiro (matriz)
    public int getSize() {
        return size;
    }

    public ArrayList<Board> getChildren() {
        return children;
    }

    public int getDepth() {
        return depth;
    }
       
    public Board(int dimension, int tDepth) {
        this.size = dimension;
        this.board = new int [size][size];
        this.depth = tDepth;
        this.children = new ArrayList<Board>();
        this.eraseBoard();
    }
    
    public Board(int dimension, int tDepth, int[][] matrix){
        this.size = dimension;
        this.board = matrix;
        this.depth = tDepth;
        this.children = new ArrayList<Board>();
    }
}

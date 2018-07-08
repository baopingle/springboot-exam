package com.brian.springboot.example;

public class Solution {

    public static void main(String[] args){
        char[][] board = {{'b','b'},{'a','b'},{'b','a'}};
        System.out.println(exist(board,"a"));
    }
    public static boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0;i<board.length;i++){
            for(int j = 0; j<board[i].length;j++){
                if(search(board,visited,i,j,word,0)){
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean search(char[][] board, boolean[][] visited, int row, int col, String word, int idx){
        if(idx == word.length()){
            // got the result;
            return true;
        }

        // start to search, first to check current element of board
        boolean found = canFind(board, visited, row, col, word, idx);
        if(found){
            visited[row][col] = true;
            idx++;

            // if found, then go to check the next char, to find the path of the board
            boolean hasPath = search(board, visited, row-1,col,word, idx) // up
                    || search(board, visited, row+1, col, word, idx) // down
                    || search(board, visited, row, col-1, word, idx) // left
                    || search(board, visited, row, col+1, word, idx); // right

            if(!hasPath){
                // if the path can not be found, then go back to the original point
                visited[row][col] = false;
                idx--;
            }

            return hasPath;
        }

        return false;


    }

    private static boolean canFind(char[][] board, boolean[][] visited, int row, int col, String word, int idx){
        if(row < board.length && row >= 0
                && col < board.length && col >= 0
                && !visited[row][col]
                && word.charAt(idx)==board[row][col]){
            return true;
        }

        return false;
    }
}

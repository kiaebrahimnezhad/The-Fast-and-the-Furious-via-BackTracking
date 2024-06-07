import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[][] firstTest = {
                {1, 0, 1, 1, 1, 1},
                {1, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1}
        };
        int[][] secondTest = {
                {1, 1, 1, 1, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 0, 1}
        };
        int[][] thirdTest = {
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 1}
        };

        findPath(firstTest);
        findPath(secondTest);
        findPath(thirdTest);

    }

    public static void printMatrix(int[][] matrix) {// to print matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] ignoreNeighbor(int[][] input) {//حرکت به خانه ای که مانع است یا در مجاورت با مانع قرار دارد مجاز نیست
        //so , we change main matrix to ignore neighbor of point that matrix[indI][indJ] == 0
        int l = input[0].length;
        for (int index = 0; index < (input[0].length) * (input.length); index++) {
            if (input[row(index, l)][col(index, l)] == 0) {
                if (row(index, l) == 0) {
                    if (col(index, l) == 0) {
                        if (input[row(index, l)][col(index, l) + 1] != 0) {
                            input[row(index, l)][col(index, l) + 1] = 2;
                        }
                        if (input[row(index, l) + 1][col(index, l)] != 0) {
                            input[row(index, l) + 1][col(index, l)] = 2;
                        }
                    } else if (col(index, l) == l - 1) {
                        if (input[row(index, l)][col(index, l) - 1] != 0) {
                            input[row(index, l)][col(index, l) - 1] = 2;
                        }
                        if (input[row(index, l) + 1][col(index, l)] != 0) {
                            input[row(index, l) + 1][col(index, l)] = 2;
                        }
                    } else {
                        if (input[row(index, l)][col(index, l) + 1] != 0) {
                            input[row(index, l)][col(index, l) + 1] = 2;
                        }
                        if (input[row(index, l) + 1][col(index, l)] != 0) {
                            input[row(index, l) + 1][col(index, l)] = 2;
                        }
                        if (input[row(index, l)][col(index, l) - 1] != 0) {
                            input[row(index, l)][col(index, l) - 1] = 2;
                        }
                    }
                } else if (row(index, l) == input.length - 1) {
                    if (col(index, l) == 0) {
                        if (input[row(index, l) - 1][col(index, l)] != 0) {
                            input[row(index, l) - 1][col(index, l)] = 2;
                        }
                        if (input[row(index, l)][col(index, l) + 1] != 0) {
                            input[row(index, l)][col(index, l) + 1] = 2;
                        }
                    } else if (col(index, l) == l - 1) {
                        if (input[row(index, l) - 1][col(index, l)] != 0) {
                            input[row(index, l) - 1][col(index, l)] = 2;
                        }
                        if (input[row(index, l)][col(index, l) - 1] != 0) {
                            input[row(index, l)][col(index, l) - 1] = 2;
                        }
                    } else {
                        if (input[row(index, l) - 1][col(index, l)] != 0) {
                            input[row(index, l) - 1][col(index, l)] = 2;
                        }
                        if (input[row(index, l)][col(index, l) + 1] != 0) {
                            input[row(index, l)][col(index, l) + 1] = 2;
                        }
                        if (input[row(index, l)][col(index, l) - 1] != 0) {
                            input[row(index, l)][col(index, l) - 1] = 2;
                        }
                    }
                } else {
                    if (col(index, l) == 0) {
                        if (input[row(index, l) - 1][col(index, l)] != 0) {
                            input[row(index, l) - 1][col(index, l)] = 2;
                        }
                        if (input[row(index, l) + 1][col(index, l)] != 0) {
                            input[row(index, l) + 1][col(index, l)] = 2;
                        }
                        if (input[row(index, l)][col(index, l) + 1] != 0) {
                            input[row(index, l)][col(index, l) + 1] = 2;
                        }
                    } else if (col(index, l) == l - 1) {
                        if (input[row(index, l) - 1][col(index, l)] != 0) {
                            input[row(index, l) - 1][col(index, l)] = 2;
                        }
                        if (input[row(index, l) + 1][col(index, l)] != 0) {
                            input[row(index, l) + 1][col(index, l)] = 2;
                        }
                        if (input[row(index, l)][col(index, l) - 1] != 0) {
                            input[row(index, l)][col(index, l) - 1] = 2;
                        }
                    } else {
                        if (input[row(index, l) - 1][col(index, l)] != 0) {
                            input[row(index, l) - 1][col(index, l)] = 2;
                        }
                        if (input[row(index, l) + 1][col(index, l)] != 0) {
                            input[row(index, l) + 1][col(index, l)] = 2;
                        }
                        if (input[row(index, l)][col(index, l) + 1] != 0) {
                            input[row(index, l)][col(index, l) + 1] = 2;
                        }
                        if (input[row(index, l)][col(index, l) - 1] != 0) {
                            input[row(index, l)][col(index, l) - 1] = 2;
                        }
                    }
                }
            }
        }
        System.out.println("The matrix , will change to below matrix to ignore neighbors : ");
        printMatrix(input);
        return input;
    }


    public static void findPath(int[][] input) {
        input = ignoreNeighbor(input);
        List<Integer> shortestPath = null;
        for (int j = 0; j < input[0].length; j++) {
            List<Integer> temp = path(j, input, new ArrayList<>());
            if (temp == null) {
                continue;
            }
            if (shortestPath == null || shortestPath.size() > temp.size()) {
                shortestPath = temp;
            }
        }

        if (shortestPath == null){
            System.out.println("No path found :( !!! ");
        } else {
            int step;//for print each count of step
            //print final path and size
            System.out.println("the shortest path is ([i][j] of each step) : ");
            for (int i = 0; i < shortestPath.size(); i++) {
                step = i+1;
                System.out.println("Step "+ step + "- [" + row(shortestPath.get(i), input[0].length) + "] [" + col(shortestPath.get(i), input[0].length) + "]");
            }
            System.out.println("shortest path size is : " + shortestPath.size());
            System.out.println("----------------------------------------------------------------");
        }
    }


    public static List<Integer> path(int start, int[][] matrix, List<Integer> currentPath) {
        int l = matrix[0].length; // length of column to find indexes and moving top and down
        if (matrix[row(start, l)][col(start, l)] == 1 && !currentPath.contains(start)) {// If start Point Is Promising
            List<Integer> copyOfCurrentPath = new ArrayList<>(currentPath);
            copyOfCurrentPath.add(start);
            if (row(start, l) == matrix.length - 1) {// If you are in last row
                return copyOfCurrentPath;// FINAL PATH!!!!!!!!!!!!!!!!!   جواب مساله
            }
            List<Integer> neighbor = new ArrayList<>(); // مجاور  //neighbor
            if (row(start, l) == 0) {//neighbors are TOP LEFT RIGHT DOWN . NO DIAGONAL move!!!!
                if (col(start, l) == 0) {
                    neighbor.add(start + l);// down
                    neighbor.add(start + 1);// right
                } else if (col(start, l) == l - 1) {
                    neighbor.add(start + l);// down
                    neighbor.add(start - 1);// left
                } else {
                    neighbor.add(start + l);// down
                    neighbor.add(start + 1);// right
                    neighbor.add(start - 1);// left
                }
            } else if (row(start, l) == matrix.length - 1) {
                if (col(start, l) == 0) {
                    neighbor.add(start + 1);// right
                    neighbor.add(start - l);// top
                } else if (col(start, l) == l - 1) {
                    neighbor.add(start - 1);// left
                    neighbor.add(start - l);// top
                } else {
                    neighbor.add(start + 1);// right
                    neighbor.add(start - 1);// left
                    neighbor.add(start - l);// top
                }
            } else {
                if (col(start, l) == 0) {
                    neighbor.add(start + l);// down
                    neighbor.add(start + 1);// right
                    neighbor.add(start - l);// top
                } else if (col(start, l) == l - 1) {
                    neighbor.add(start + l);// down
                    neighbor.add(start - 1);// left
                    neighbor.add(start - l);// top
                } else {
                    neighbor.add(start + l);// down
                    neighbor.add(start + 1);// right
                    neighbor.add(start - 1);// left
                    neighbor.add(start - l);// top
                }
            }
            List<Integer> shortestPath = null;
            for (Integer candidate : neighbor) {// path(for all neighbors of one PROMISING POINT !!!);
                List<Integer> temp = path(candidate, matrix, copyOfCurrentPath);
                if (temp == null) {
                    continue; //if the neighbor isn't promising , path(for next neighbor);
                }
                if (shortestPath == null || shortestPath.size() > temp.size()) {
                    shortestPath = temp;//if the path(neighbor) is shortest path that have seen in program ...
                }
            }
            return (shortestPath);

        } else {
            return null;
        }
    }

    public static int row(int input, int length) {
        return input / length;
    }

    public static int col(int input, int length) {
        return input % length;
    }
}
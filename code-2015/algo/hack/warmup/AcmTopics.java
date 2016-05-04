package algo.hack.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AcmTopics {

    private static void findMaxTopicsKnown(boolean[][] A, int N, int M) {
        if (A == null)
            throw new NullPointerException();
        
        int i = 0;
        int j = 0;
        int k = 0;
        int max = -1;
        int[] teamsWithMax = new int[M + 1];
        int topicCount = 0;
        for (i = 0; i < N - 1; i++){
            for (j = i + 1; j < N; j++){
                topicCount = 0;
                for (k = 0; k < M; k++){
                    if((A[i][k] | A[j][k]) == true)
                      topicCount += 1;  
                }
                teamsWithMax[topicCount] += 1;
                if(max < topicCount)
                    max = topicCount;  
            }
        }
        System.out.println(max);
        System.out.println(teamsWithMax[max]);

    }

    /**
     * This reads from keyboard using BufferReader class
     */
    private  static boolean[][] readInput() {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        int N = 0;
        int M = 0;
        boolean[][] map = null;
        try {
            String firstLine = br.readLine();
            String[] in = firstLine.split(" ");
            
            N = Integer.parseInt(in[0]);
            M = Integer.parseInt(in[1]);
            map = new boolean[N][M];
            int i = 0, j = 0;
            while (i < N) {
                for (j = 0; j < M; j++) {
                    if (br.read() == '1')
                        map[i][j] = true;
                    else
                        map[i][j] = false;  
                }
                br.skip(2);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
    
   

    public static void main(String[] args) {
        boolean[][] map = readInput();
        findMaxTopicsKnown(map, map.length, map[0].length);

    }

}

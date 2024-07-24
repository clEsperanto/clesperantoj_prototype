import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class TestAbsolute {

    @Test
    public void testAbsolute() {
        int[][] input = {{1, -1}, {1, -1}};
        int[][] result = absolute(input);
        
        for (int[] row : result) {
            for (int value : row) {
                assertEquals(1, value);
            }
        }
        
        assertEquals(1, Arrays.stream(result).flatMapToInt(Arrays::stream).min().getAsInt());
        assertEquals(1, Arrays.stream(result).flatMapToInt(Arrays::stream).max().getAsInt());
        assertEquals(1, Arrays.stream(result).flatMapToInt(Arrays::stream).average().getAsDouble(), 0.001);
    }

    @Test
    public void testAbsolute1() {
        int[][] input = {{1, -1}, {1, -1}};
        int[][] result = absolute(input);
        
        for (int[] row : result) {
            for (int value : row) {
                assertEquals(1, value);
            }
        }
    }

    private int[][] absolute(int[][] input) {
        int[][] result = new int[input.length][input[0].length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                result[i][j] = Math.abs(input[i][j]);
            }
        }
        return result;
    }
}
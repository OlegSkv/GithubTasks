package oskv.tasks;

import java.util.*;

public class Java1DArrayPart2 {

    public static boolean canWin(int leap, int[] game) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int gameLength = game.length;
        for (int i = 0; i < gameLength; i++) {
            Set<Integer> possibleTransition = new HashSet<>();
            if (game[i] != 0) {
                continue;
            }

            if (i - 1 >= 0 && game[i - 1] == 0) {  // it is possible to go backward
                possibleTransition.add(i - 1);
            }
            if (i == gameLength - 1) { // last index of the array
                possibleTransition.add(-1);
            } else {
                if (game[i + 1] == 0) { // it is possible to go forward
                    possibleTransition.add(i + 1);
                }
                if (i + leap <= gameLength - 1 && game[i + leap] == 0) {
                    // leap up to the end element=0, valid leap
                    possibleTransition.add(i + leap);
                }
                if (i + leap > gameLength - 1) {
                    // leap outside of the array
                    possibleTransition.add(-1);
                }
            }

            if (possibleTransition.size() != 0) {  // we don't need empty sets
                map.put(i, possibleTransition);
            }
        }



        Set<Integer> exits = new HashSet<>(Arrays.asList(-1)); // contains options of transition
        int mapsize = map.size();
        while (true) {
            Set<Integer> temp = new HashSet<>();
            for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {

                for (int index : exits) {
                    if (entry.getValue().contains(index)) {
                        temp.add(entry.getKey());
                    }
                }
                ;
            }
            exits = temp;
            for (Integer exit : exits) {
                map.remove(exit);
            }
            if (mapsize - map.size() == 0) {
                return false;
            }
            if (exits.contains(0)) {
                return true;
            }
            mapsize = map.size();

        }
    }

    public static void main(String[] args) {
        System.out.println(canWin(3, new int[]{0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0}));
    }
}

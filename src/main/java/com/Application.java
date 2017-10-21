package com;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);


        //System.out.println(nextSmaller(2071));

    }

    /*public static long nextSmaller(long n) {

        long nextSmaller = -1;

        for(long i = n - 1; i >= 1; i--) {
            if(sameDigits(n, i)) {
                nextSmaller = i;
                break;
            }
        }

        return nextSmaller;
    }


    private static boolean sameDigits(long i, long j) {


        char[] _i = String.valueOf(i).toCharArray();
        char[] _j = String.valueOf(j).toCharArray();

        if(_j.length != _i.length) {
            return false;
        }


        Set<Character> digits1 = new HashSet<>();
        Set<Character> digits2 = new HashSet<>();

        for(int k = 0; k<_i.length; k++) {
            digits1.add(_i[k]);
            digits2.add(_j[k]);
        }


        return digits1.containsAll(digits2) && digits2.containsAll(digits1);
    }


    public static Map<String, List<Integer>> getPeaks(int[] arr) {

        Map<String, List<Integer>> peaks = new HashMap<>();

        for(int i = 1; i < arr.length - 1; i++) {
            if(arr[i] >= arr[i-1] && arr[i] >= arr[i+1]) {
                if(peaks.get(String.valueOf(i)) != null) {
                    peaks.get(String.valueOf(i)).add(arr[i]);
                } else {
                    ArrayList<Integer> ints = new ArrayList<>();
                    ints.add(arr[i]);
                    peaks.put(String.valueOf(i), ints);

                }
            }
        }


        return peaks;
    }
*/
}

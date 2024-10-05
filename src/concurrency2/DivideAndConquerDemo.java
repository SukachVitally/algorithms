package concurrency2;

/**
 * Recursively sum a range of numbers
 */

import java.util.concurrent.*;

class RecursiveSum extends RecursiveTask<Long> { //

    private long lo, hi;

    public RecursiveSum(long lo, long hi) {
        this.lo = lo;
        this.hi = hi;
    }

    protected Long compute() {
        if (hi-lo <= 100_000) { // base case threshold
            long total = 0;
            for (long i = lo; i <= hi; i++)
                total += i;
            return total;
        } else {
            long mid = (hi+lo)/2; // middle index for split
            RecursiveSum left = new RecursiveSum(lo, mid);
            RecursiveSum right = new RecursiveSum(mid+1, hi);
            left.fork(); // forked thread computes left half in separate thread !!!!
            return right.compute() // Right compute in current thread!!!!
                    + left.join(); // left.join - wait until result is ready and return raw value.
        }
    }
}

public class DivideAndConquerDemo {
    public static void main(String args[]) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        Long total = pool.invoke(new RecursiveSum(0, 1_000_000_000));
        pool.shutdown();
        System.out.println("Total sum is " + total);
    }
}
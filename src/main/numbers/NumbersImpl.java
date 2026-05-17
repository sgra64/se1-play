package numbers;

import java.util.Arrays;
import java.util.List;
import java.util.Set;


/**
 * Non-public implementation class of interface {@link Numbers}.
 */
class NumbersImpl implements Numbers {

    @Override
    public long sum(int[] numbers) {
        if(numbers==null)
            throw new IllegalArgumentException(String.format("illegal argument: %s", numbers));
        // 
        // long sum = 0L;
        // for(int i=0; i < numbers.length; i++) {
        //     sum += numbers[i];
        // }
        // return sum;
        // 
        // return Arrays.stream(numbers).sum();
        // 
        // return Arrays.stream(numbers).reduce(0, (acc, n) -> acc + n);
        // 
        return Arrays.stream(numbers).asLongStream().reduce(0, (acc, n) -> acc + n);
    }

    @Override
    public long sumPositiveEvenNumbers(int[] numbers) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sumPositiveEvenNumbers'");
    }

    @Override
    public long sumRecursive(int[] numbers, int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sumRecursive'");
    }

    @Override
    public int findFirst(int[] numbers, int x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findFirst'");
    }

    @Override
    public int findLast(int[] numbers, int x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findLast'");
    }

    @Override
    public List<Integer> findAll(int[] numbers, int x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Set<Pair> findSums(int[] numbers, int sum) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findSums'");
    }

    @Override
    public Set<Set<Integer>> findAllSums(int[] numbers, int sum) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllSums'");
    }

}

package numbers;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import numbers.Numbers.Pair;


/**
 * Hamcrest matcher and other helper functions.
 * 
 * @author sgra64
 *
 */
public class Matchers {

    /**
     * Matcher for collections ignoring the order or elements, including lists
     * where order matters and duplicates may occur.
     * 
     * @param expected collection to match.
     * @param actual collection to match.
     * @return true if both collections are equal in size and element values.
     */
    public static <T> boolean matchIgnoreOrder(Collection<T> expected, Collection<T> actual) {
        return expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected);
    }


    /**
     * Matcher for collections ignoring the order or elements, including lists
     * where order matters and duplicates may occur.
     * 
     * @param expected int[][] to match
     * @param actual collection of Pairs to match
     * @return true if both collections are equal in size and element values
     */
    public static boolean matchIgnoreOrder(int[][] expected, Collection<Pair> actual) {
        // convert int[][] to List<Pair(a,b)>
        var exp = Arrays.stream(expected)
                .map(inner -> Optional.ofNullable(inner != null && inner.length==2? new Pair(inner[0], inner[1]) : null))
                .filter(opt -> opt.isPresent()).map(opt -> opt.get())
                .collect(Collectors.toList());
        //
        var result = exp.size() == actual.size();
        if(result) {
            for(Pair pe : exp) {
                boolean found = false;
                for(Pair pa : actual) {
                    found = found || (pe.a()==pa.a() && pe.b()==pa.b()) || (pe.a()==pa.b() && pe.b()==pa.a());
                }
                if( ! found)
                    return false;
            }
        }
        return result;
    }


    /**
     * Matcher for collections ignoring the order or elements, including lists
     * where order matters and duplicates may occur.
     * 
     * @param expected int[][] to match
     * @param actual collection to match
     * @return true if both collections are equal in size and element values
     */
    public static boolean matchIgnoreOrder(int[][] expected, Set<Set<Integer>> actual) {
        // convert int[][] to List<List<T>>
        var exp = Arrays.stream(expected)
                .map(arr -> Arrays.stream(arr).boxed().collect(Collectors.toList())
            ).collect(Collectors.toList());
        //
        var result = exp.size() == actual.size();
        if(result) {
            for(Set<Integer>act : actual) {
                boolean found = false;
                for(int i=0; ! found && i < exp.size(); i++) {
                    List<Integer> ex = exp.get(i);
                    found = act.size()==ex.size() && act.containsAll(ex) && ex.containsAll(act);
                }
                result = result && found;
            }
        }
        return result;
    }


//    private static Set<Class<?>> wrapperTypes = Set.of(
//        Integer.class, Long.class, String.class, Boolean.class,
//        Character.class, Byte.class, Short.class, Double.class, Float.class
//    );
//
//    /**
//     * Helper function to map a nested collection into a textual form.
//     * 
//     * @param obj untyped nested collection.
//     * @param sbArg optional StringBuffer arg to store output.
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    public static StringBuffer toString(Object obj, StringBuffer... sbArg) {
//        @SuppressWarnings("rawtypes")
//        var clazz = Optional.ofNullable(obj).map(o -> o.getClass()).orElse((Class)Object.class);
//        var sb = Arrays.stream(sbArg).filter(s -> s != null).findFirst().orElse(new StringBuffer());
//        //
//        if(obj==null || clazz.isPrimitive() || wrapperTypes.contains(clazz)) {
//            sb.append(obj==null? "null" : obj);
//        } else {
//            if(Collection.class.isAssignableFrom(obj.getClass())) {
//                sb.append("[");
//                int len = sb.length();
//                ((Collection<?>)obj).forEach(o -> {
//                    sb.append(sb.length()==len? "" : ", ");
//                    toString(o, sb);
//                });
//                sb.append("]");
//            }
//        }
//        return sb;
//    }
}
package patterns;

/**
 * Class shows the implementation of the standard <b><i>Singleton pattern</i></b>
 * in the <i>lazy</i> and the <i>strict</i> form.
 * <p>
 * The <i>strict</i> form of the <i>Singleton pattern</i> always creates the
 * object of the <i>Singleton</i> class.
 * <p>
 * The <i>lazy</i> form of the <i>Singleton pattern</i> creates only an
 * object of the <i>Singleton</i> class when the getter method {@code getInstance()}
 * is called.
 * <p>
 * The implementation of the <i>Singleton pattern</i> has three features:
 * <ol>
 * <li> a private static variable of the <i>Singleton</i> class.
 * <li> a private constructor to disable object creation outside the <i>Singleton</i> class.
 * <li> a public static getter method {@code getInstance()} that provides access
 *      to the <i>Singleton</i> object.
 * </ol>
 * <img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/patterns/singleton.png" width="360"/>
 * <p>
 * The implementation of the <i>strict Singleton pattern</i>:
 * <pre>public class Singleton {
 * 
 *     /**
 *      * 1. Private static variable of the <i>Singleton</i> class (the object is always created).
 *      * /
 *     private static Singleton singletonInstance = new Singleton();
 *     
 *     /**
 *      * 2. Private constructor to disable object creation outside the <i>Singleton</i> class. 
 *      * /
 *     private Singleton() { }
 *     
 *     /**
 *      * 3. Public static getter method that provides access to the <i>Singleton</i> object.
 *      * @return the <i>Singleton</i> object.
 *      * /
 *     public static Singleton <b><i>getInstance()</i></b> {
 *         return singletonInstance;
 *     }
 * }</pre>
 * <p>
 * The implementation of the <i>lazy Singleton pattern</i>:
 * <pre>public class Singleton {
 * 
 *     /**
 *      * 1. Private static variable of the <i>Singleton</i> class (no object is created).
 *      * /
 *     private static Singleton singletonInstance = null;
 *     
 *     /**
 *      * 2. Private constructor that disables object creation outside the <i>Singleton</i> class. 
 *      * /
 *     private Singleton() { }
 *     
 *     /**
 *      * 3. Public static getter method that provides access to the <i>Singleton</i> object.
 *      * @return the <i>Singleton</i> object.
 *      * /
 *     public static Singleton <b><i>getInstance()</i></b> {
 *         if(singletonInstance==null) {
 *             singletonInstance = new Singleton();
 *         }
 *         return singletonInstance;
 *     }
 * }</pre>
 * 
 */
public class Singleton {

    /**
     * Private static variable of the <i>Singleton</i> class.
     */
    private static Singleton singletonInstance = new Singleton();

    /**
     * Private constructor to disable object creation outside the <i>Singleton</i> class. 
     */
    private Singleton() { }

    /**
     * Public static getter method that provides access to the <i>Singleton</i> object.
     * @return reference to the <i>Singleton</i> object.
     */
    public static Singleton getInstance() {
        return singletonInstance;
    }
}

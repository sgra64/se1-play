package patterns;

/**
 * The standard <b><i>Builder pattern</i></b> is used when objects of a
 * <b><i>ClassToBuild</i></b> require multiple steps. The <i>"build"</i>-
 * methods are encapsulated in a separate <b><i>Builder</i></b>-class
 * that is often included in the <i>ClassToBuild</i> (<i>part-of</i>).
 * <p>
 * The <i>ClassToBuild</i> has a static method called {@code builder()}
 * that returns an instance of the <i>Builder</i>-class. Objects of the
 * <i>Builder</i>-class encapsulate the state of the build process for
 * each <i>object-to-build</i>.
 * <p>
 * <i>Build</i> methods are often <i>chainable</i>. The <i>chain</i> ends
 * with the {@code build()}-method that creates the actual
 * <i>object-to-build</i> ending the build process.
 * <p>
 * <img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/patterns/builder.png" width="360"/>
 * <p>
 * The implementation of the <i>strict Singleton pattern</i>:
 * <pre>public class ClassToBuild {
 * 
 *     public static class Builder {
 * 
 *         public Builder step_1() {
 *              return this;
 *         }
 *         public Builder step_2() {
 *              return this;
 *         }
 *         public Builder step_3() {
 *              return this;
 *         }
 *   
 *         public ClassToBuild build() {
 *              return new ClassToBuild();
 *         }
 *     }
 * 
 *     public static Builder builder() {
 *         return new Builder();
 *     }
 * }</pre>
 */
public class ClassToBuild {

    /**
     * <i>Builder</i> class for the {@link ClassToBuild} that keeps state
     * of the build process of an <i>object-to-build</i> (instance of the
     * {@link ClassToBuild}).
     * <p>
     * The <i>Builder</i> class defines <i>chainable build-methods</i>
     * ({@code step_[123]()}) that perform the <i>build</i>-steps.
     * <p>
     * The chain of build methods ends with the {@code build()}-method
     * that creates the <i>object-to-build</i>.
     */
    public static class Builder {

        /**
         * Method of <i>step 1</i> of the build process.
         * @return chainable self-reference to the <i>Builder</i> instance.
         */
        public Builder step_1() {
            return this;
        }

        /**
         * Method of <i>step 2</i> of the build process.
         * @return chainable self-reference to the <i>Builder</i> instance.
         */
        public Builder step_2() {
            return this;
        }

        /**
         * Method of <i>step 2</i> of the build process.
         * @return chainable self-reference to the <i>Builder</i> instance.
         */
        public Builder step_3() {
            return this;
        }

        /**
         * Final method that ends the build process and creates the
         * <i>object-to-build</i>.
         * @return <i>object-to-build</i>.
         */
        public ClassToBuild build() {
            return new ClassToBuild();
        }
    }

    /**
     * Static method that returns an instance of the {@link Builder} class
     * that controls the build process.
     * @return instance of the {@link Builder} class.
     */
    public static Builder builder() {
        return new Builder();
    }
}

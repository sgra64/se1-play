package components;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Public interface of system a component that stores objects of type
 * {@code <T>} (or of sub-type {@code <S>}) indexed by an {@code id}
 * of type {@code <ID>}.
 * @param <T> type of objects stored in the {@link Repository}.
 * @param <ID> type of the {@code <ID>} used to index objects in the {@link Repository}.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public interface Repository<T, ID> {

    /**
     * Return objects of the repository as {@link Stream}{@code <T>}.
     * @return {@link Stream}{@code <T>} of objects stored in the repository.
     */
    Stream<T> stream();

    /**
     * Return the number of entities stored in the repository.
     * @return number of entities stored in the repository.
     */
    long count();

    /**
     * Return whether an entity with the given {@code id} exists.
     * @param id {@code id} attribute of the object to check.
     * @return {@code true} if an entity with the given {@code id} exists, {@code false} otherwise.
     */
    boolean existsById(ID id);

    /**
     * Return all instances of the repository.
     * @return all instances of the repository.
     */
    Iterable<T> findAll();

    /**
     * Return all objects matching the given {@code id's}.
     * @param ids set of {@code id} to retrieve objects.
     * @return objects with matching {@code id}.
     */
    Iterable<T> findAllById(Iterable<ID> ids);

    /**
     * Retrieve an object matching the given {@code id}.
     * @param id {@code id} of the object to retrieve.
     * @return {@link Optional} with matching object or empty {@link Optional} if not found.
     */
    Optional<T> findById(ID id);

    /**
     * Delete the given object.
     * @param entity object to delete.
     */
    void delete(T entity);

    /**
     * Delete all objects stored in the repository.
     */
    void deleteAll();

    /**
     * Delete all given objetcs.
     * @param entities objects to delete.
     */
    void deleteAll(Iterable<? extends T> entities);

    /**
     * Delete all objects matching the given {@code id's}.
     * @param ids set of {@code id} to delete objects.
     */
    void deleteAllById(Iterable<? extends ID> ids);

    /**
     * Delete the object with the given {@code id}.
     * @param id {@code id} of the object to delete.
     */
    void deleteById(ID id);

    /**
     * Save object to the repository. Object replaces a potentially
     * existing object with the same {@code id}.
     * @param <S> sub-type of object to save.
     * @param entity object to save.
     * @return saved object or prior replaced object.
     */
    <S extends T> S save(S entity);

    /**
     * Saves all given entities.
     * @param <S> sub-type of objects to save.
     * @param entities objects to save.
     * @return saved objects.
     */
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);
}

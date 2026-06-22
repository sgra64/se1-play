package components.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import components.Repository;

/**
 * Non-public implementation class of interface {@link Repository} that stores
 * objects of type {@code <T>} indexed by an {@code id} of type {@code <ID>}.
 * @param <T> type of objects stored in the {@link Repository}.
 * @param <ID> type of the {@code <ID>} used to index objects in the {@link Repository}.
 */
class RepositoryImpl<T, ID> implements Repository<T, ID> {

    /*
     * Function to retrieve the {@code id} from an object of type {@code <T>}.
     */
    private final Function<T, ID> idMapper;

    /*
     * Internal store of objects of type {@code <T>} indexed by an {@code id}
     * of type {@code <ID>}.
     */
    private final Map<ID, T> items = new HashMap<>();

    /**
     * Constructor for {@link RepositoryImpl}.
     * @param idMapper function to retrieve the {@code id} from an object of type {@code <T>}.
     */
    RepositoryImpl(Function<T, ID> idMapper) {
        this.idMapper = idMapper;
    }

    @Override
    public Stream<T> stream() {
        return items.values().stream();
    }

    @Override
    public long count() {
        return items.size();
    }

    @Override
    public boolean existsById(ID id) {
        return id != null && items.containsKey(id);
    }

    @Override
    public Iterable<T> findAll() {
        return new ArrayList<>(items.values());
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> ids) {
        var result = new ArrayList<T>();
        if(ids != null) {
            for(ID id : ids) {
                // Optional.ofNullable(items.get(id)).map(item -> result.add(item));
                if(id != null) {
                    var item = items.get(id);
                    if(item != null) {
                        result.add(item);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(id==null? null : items.get(id));
    }

    @Override
    public void delete(T entity) {
        if(entity != null) {
            Optional.ofNullable(idMapper.apply(entity)).map(id -> items.remove(id));
        }
    }

    @Override
    public void deleteAll() {
        items.clear();
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        if(entities != null) {
            for(T entity : entities) {
                if(entity != null) {
                    delete(entity);
                }
            }
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {
        if(ids != null) {
            for(ID id : ids) {
                if(id != null) {
                    deleteById(id);
                }
            }
        }
    }

    @Override
    public void deleteById(ID id) {
        if(id != null) {
            items.remove(id);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <S extends T> S save(S entity) {
        S prior = null;
        if(entity != null) {
            ID id = idMapper.apply(entity);
            if(id != null) {
                prior = (S) items.put(id, entity);
            }
        }
        return prior;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        var saved = new ArrayList<S>();
        if(entities != null) {
            for(S entity : entities) {
                saved.add(save(entity));
            }
        }
        return saved;
    }
}

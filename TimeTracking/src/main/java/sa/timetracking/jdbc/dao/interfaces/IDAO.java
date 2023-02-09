package sa.timetracking.jdbc.dao.interfaces;

import java.util.List;

public interface IDAO<T> {
    List<T> getAll();
    T getById(final Integer id);
    boolean update(final T object);
    boolean delete(final T object);
    boolean create(final T object);
    boolean exists(final Integer id);
}

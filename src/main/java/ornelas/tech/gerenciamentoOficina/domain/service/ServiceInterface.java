package ornelas.tech.gerenciamentoOficina.domain.service;

import java.util.List;

public interface ServiceInterface<T, I> {

    List<T> findAll();
    T findById(I id);
    T save(T entity);
    void deleteById(I id);
}

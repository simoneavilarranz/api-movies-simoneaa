package dev.simoneaa.demop5.implementations;

import java.util.List;

public interface InterfaceGenericGetService<T> {
    List<T> getEntities();
    T getById(Long id);
}

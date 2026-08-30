package dev.simoneaa.demop5.implementations;

public interface InterfaceGenericEditService<T, S> {
    public S storeEntity(T dto);
    public S updateEntity(Long id, T dto);
}

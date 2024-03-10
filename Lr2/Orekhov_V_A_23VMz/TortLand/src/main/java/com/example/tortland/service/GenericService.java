package com.example.tortland.service;

import com.example.tortland.model.GenericModel;
import com.example.tortland.repository.GenericRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public abstract class GenericService<T extends GenericModel> {

    private final GenericRepository<T> repository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    protected GenericService(GenericRepository<T> repository) {
        this.repository = repository;
    }

    public List<T> listAll() {
        return repository.findAll();
    }

    public T getOne(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public T create(T object) {
        return repository.save(object);
    }

    public T update(T object) {
        return repository.save(object);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}

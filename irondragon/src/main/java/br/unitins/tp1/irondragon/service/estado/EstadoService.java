package br.unitins.tp1.irondragon.service.estado;

import java.util.List;

import br.unitins.tp1.irondragon.dto.request.EstadoRequestDTO;
import br.unitins.tp1.irondragon.model.Estado;

public interface EstadoService {

    Estado create(EstadoRequestDTO estado);
    void update(long id, EstadoRequestDTO estado);
    void delete(long id);
    Estado findById(long id);
    Estado findBySigla(String sigla);
    List<Estado> findAll(Integer page, Integer pageSize);
    List<Estado> findByNome(String nome, Integer page, Integer pageSize);
    long count();
    long count(String nome);
    
}
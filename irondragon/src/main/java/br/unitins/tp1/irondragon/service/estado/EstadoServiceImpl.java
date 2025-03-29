package br.unitins.tp1.irondragon.service.estado;

import java.util.List;

import br.unitins.tp1.irondragon.dto.request.EstadoRequestDTO;
import br.unitins.tp1.irondragon.model.Estado;
import br.unitins.tp1.irondragon.repository.EstadoRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    EstadoRepository estadoRepository;

    @Override
    @Transactional
    public Estado create(EstadoRequestDTO estado) {
        Estado novoEstado = new Estado();
        novoEstado.setNome(estado.nome());
        novoEstado.setSigla(estado.sigla());

        // realizando inclusao
        estadoRepository.persist(novoEstado);

        return novoEstado;
    }

    @Override
    @Transactional
    public void update(long id, EstadoRequestDTO estado) {
        Estado edicaoEstado = estadoRepository.findById(id);

        edicaoEstado.setNome(estado.nome());
        edicaoEstado.setSigla(estado.sigla());
    }

    @Override
    @Transactional
    public void delete(long id) {
        estadoRepository.deleteById(id);
    }

    @Override
    public Estado findById(long id) {
        return estadoRepository.findById(id);
    }

    @Override
    public Estado findBySigla(String sigla) {
        return null;
        //return estadoRepository.findBySigla(sigla);
    }

    @Override
    public List<Estado> findAll(Integer page, Integer pageSize) {
        PanacheQuery<Estado> query = null;
        if (page == null || pageSize == null)
            query = estadoRepository.findAll();
        else 
            query = estadoRepository.findAll().page(page, pageSize);

        return query.list();
    }

    @Override
    public List<Estado> findByNome(String nome, Integer page, Integer pageSize) {
        return estadoRepository.findByNome(nome).page(page, pageSize).list();
    }

    public List<Estado> findByNome(String nome) {
        return estadoRepository.findByNome(nome).list();
    }

    @Override
    public long count() {
        return estadoRepository.findAll().count();
    }

    @Override
    public long count(String nome) {
        return estadoRepository.findByNome(nome).count();
    }
    
}
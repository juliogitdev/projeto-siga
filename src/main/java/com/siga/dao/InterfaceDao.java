package com.siga.dao;

import com.siga.model.Entidade;
import java.util.List;

public interface InterfaceDao {
    public void cadastrar(Entidade e);
    public void atualizar(Entidade e);
    public void deletar(Entidade e);
    public List<Entidade> listar_todos();
}

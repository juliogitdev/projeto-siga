package com.siga.dao;


import java.sql.SQLException;
import java.util.List;

public interface InterfaceDao<T> {
    public void cadastrar(T Entidade) throws SQLException;
    public void atualizar(T Entidade) throws SQLException;
    public void deletar(T Entidade) throws SQLException;
    public List<T> listarTodos() throws SQLException;
}

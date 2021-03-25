/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.dao;

import java.io.IOException;
import java.util.Collection;

/**
 *
 * @author UZIEL
 */
public interface Dao<T>
{
    void create(T t) throws IOException;
    int update(T t) throws IOException;
    boolean delete(T t) throws IOException;
    Collection<T> getAll() throws IOException;
}

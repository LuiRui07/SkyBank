/*
@author: Pablo García Platero
*/

package com.example.skybank.dao;

import com.example.skybank.entity.ConversacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<ConversacionEntity,Integer> {
    @Query("select c from ConversacionEntity c where c.asistenteByIdasistente.idasistente = :idAsistente")
    public List<ConversacionEntity> filtrarChatPorAsistente(@Param("idAsistente") Integer idAsistente);

    @Query("select c  from ConversacionEntity c where c.cerrada= :cerrado and c.idasistente = :idAsistente and c.clienteByIdcliente.dni like %:dni%  and c.clienteByIdcliente.nombre like %:nombre%")
    public List<ConversacionEntity> filtrarPorTodo(@Param("idAsistente") Integer idAsistente, @Param("cerrado") Byte cerrado,@Param("dni") String dni,@Param("nombre") String nombre);

    @Query("select c  from ConversacionEntity c where c.cerrada= :cerrado and c.idasistente = :idAsistente and c.clienteByIdcliente.dni like %:dni% ")
    public List<ConversacionEntity> filtrarSinNombre(@Param("idAsistente") Integer idAsistente, @Param("cerrado") Byte cerrado,@Param("dni") String dni);

    @Query("select c  from ConversacionEntity c where  c.idasistente = :idAsistente and c.clienteByIdcliente.dni like %:dni%  and c.clienteByIdcliente.nombre like %:nombre%")
    public List<ConversacionEntity> filtrarSinCerrado(@Param("idAsistente") Integer idAsistente,@Param("dni") String dni,@Param("nombre") String nombre);

    @Query("select c  from ConversacionEntity c where c.cerrada= :cerrado and c.idasistente = :idAsistente  and c.clienteByIdcliente.nombre like %:nombre%")
    public List<ConversacionEntity> filtrarSinDNI(@Param("idAsistente") Integer idAsistente, @Param("cerrado") Byte cerrado,@Param("nombre") String nombre);

    @Query("select c  from ConversacionEntity c where c.cerrada= :cerrado and c.idasistente = :idAsistente")
    public List<ConversacionEntity> filtrarPorSoloCerrado(@Param("idAsistente") Integer idAsistente, @Param("cerrado") Byte cerrado);

    @Query("select c  from ConversacionEntity c where c.idasistente = :idAsistente and c.clienteByIdcliente.nombre like %:nombre%")
    public List<ConversacionEntity> filtrarPorSoloNombre(@Param("idAsistente") Integer idAsistente, @Param("nombre") String nombre);

    @Query("select c  from ConversacionEntity c where c.idasistente = :idAsistente and c.clienteByIdcliente.dni like %:dni%")
    public List<ConversacionEntity> filtrarPorSoloDNI(@Param("idAsistente") Integer idAsistente,@Param("dni") String dni);

}


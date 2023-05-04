/*
    @autor: Luis Ruiz Nuñez
 */

package com.example.skybank.service;

import com.example.skybank.dao.ClienteRepository;
import com.example.skybank.dao.CuentaRepository;
import com.example.skybank.dao.DivisaRepository;
import com.example.skybank.dto.Cuenta;
import com.example.skybank.dto.Divisa;
import com.example.skybank.dto.Empresa;
import com.example.skybank.entity.ClienteEntity;
import com.example.skybank.entity.CuentaEntity;
import com.example.skybank.entity.DivisaEntity;
import com.example.skybank.entity.EmpresaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skybank.dto.Cliente;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DivisaRepository divisaRepository;

    public List<Cuenta> obtenerCuentasDeCliente(Cliente cliente){
        List<Cuenta> cuentas = cuentaRepository.findByCliente(cliente.getIdcliente()).stream().map(c -> c.toDTO()).toList();
        System.out.println(cuentas);
        return cuentas;
    }

    public Cliente autenticar (String dni, String password){
        ClienteEntity c = clienteRepository.autenticar(dni,password);
        return c == null ? null : c.toDTO();
    }

    public Cliente guardarCliente(Cliente cliente){
        ClienteEntity clienteNuevo = new ClienteEntity();

        clienteNuevo.setIdcliente(cliente.getIdcliente());
        clienteNuevo.setDni(cliente.getDni());
        clienteNuevo.setNombre(cliente.getNombre());
        clienteNuevo.setApellido1(cliente.getApellido1());
        clienteNuevo.setApellido2(cliente.getApellido2());
        clienteNuevo.setNacimiento(cliente.getNacimiento());
        clienteNuevo.setCalle(cliente.getCalle());
        clienteNuevo.setEmail(cliente.getEmail());
        clienteNuevo.setPassword(cliente.getPassword());
        clienteNuevo.setNumero(cliente.getNumero());
        clienteNuevo.setCiudad(cliente.getCiudad());
        clienteNuevo.setPais(cliente.getPais());
        clienteNuevo.setPlanta(cliente.getPlanta());
        clienteNuevo.setRegion(cliente.getRegion());
        clienteNuevo.setCp(cliente.getCp());
        clienteNuevo.setBloqueado(cliente.getBloqueado());
        clienteNuevo.setVerificado(cliente.getBloqueado());

        clienteRepository.save(clienteNuevo);

        CuentaEntity c = new CuentaEntity();
        DivisaEntity d = divisaRepository.getById(1);

        c.setDivisaByDivisa(d);
        c.setSaldo(10.00);
        c.setClienteByIdcliente(clienteNuevo);
        cuentaRepository.save(c);

        ArrayList<CuentaEntity> cuentas = new ArrayList<>();
        cuentas.add(c);

        clienteNuevo.setCuentasByIdcliente(cuentas);

        clienteRepository.save(clienteNuevo);

        return clienteNuevo.toDTO();
    }

    public Cliente getClienteById (Integer idcliente){

        ClienteEntity c = clienteRepository.findById(idcliente).orElse(null);
        return c == null ? null : c.toDTO();
    }

    public void editarCliente (Cliente clienteForm){
        ClienteEntity cliente = clienteRepository.getById(clienteForm.getIdcliente());
        cliente.setNombre(clienteForm.getNombre());
        cliente.setApellido1(clienteForm.getApellido1());
        cliente.setApellido2(clienteForm.getApellido2());
        cliente.setDni(clienteForm.getDni());
        cliente.setNacimiento(clienteForm.getNacimiento());
        cliente.setEmail(clienteForm.getEmail());
        cliente.setCalle(clienteForm.getCalle());
        cliente.setNumero(clienteForm.getNumero());
        cliente.setPlanta(clienteForm.getPlanta());
        cliente.setCp(clienteForm.getCp());
        cliente.setCiudad(clienteForm.getCiudad());
        cliente.setRegion(clienteForm.getRegion());
        cliente.setPais(clienteForm.getPais());
        cliente.setBloqueado(clienteForm.getBloqueado());
        cliente.setVerificado(clienteForm.getBloqueado());

        clienteRepository.save(cliente);
    }
}

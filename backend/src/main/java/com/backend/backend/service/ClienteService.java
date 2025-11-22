package com.backend.backend.service;

import com.backend.backend.model.Cliente;

public interface ClienteService {

    //Metodo de validaciones y verificación (Si fallan, lanza un excepción, si pasan no devuelven nada)
    void verificacionUnica(Cliente cliente); //Que todos los clientes sean unicos en la base de datos
    void validarDominio(Cliente cliente); //Los emails sean validos dentro de los parametros establecidos
    void validarSinEspacios(Cliente cliente); //Usuario, email, contraseña, cedula, tipo de persona, telefono, direccion, nombre y apellido
    void validarContrasena(Cliente cliente); //Que la contraseña sea valida con los parametros establecidos
    void validarContrasenas(Cliente cliente); //Que la contraseña sea igual a la confirmación de contraseña
    void validarCedula(Cliente cliente); //Que la cedula sea valida con los parametros establecidos
    void validarTelefono(Cliente cliente); //Que el telefono sea valido con los parametros establecidos


    //Metodo de registro que usará controller
    Cliente registrarCliente (Cliente cliente); //Registrar cliente en la base de datos
}

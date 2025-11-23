package com.backend.backend.service;

import com.backend.backend.model.Cliente;
import com.backend.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{


    @Autowired //Relación con el repositorio (base de datos)
    private ClienteRepository clienteRepository;

    //Implementación del metodo de verificación unica
    @Override
    public void verificacionUnica(Cliente cliente) {

        //Se intenta buscar si ya existe alguien
        //Usuario
        Cliente existente = clienteRepository.findByUsuario(cliente.getUsuario());

        //Email
        Cliente existente2 = clienteRepository.findByEmail(cliente.getEmail());

        //Cedula
        Cliente existente3 = clienteRepository.findByCedula(cliente.getCedula());

        //Telefono
        Cliente existente4 = clienteRepository.findByTelefono(cliente.getTelefono());

        if(existente != null)
        {
            //Si no es nulo, ¡el cliente ya existe! Detenemos el proceso
            throw new RuntimeException("El nombre de usuario " + cliente.getUsuario() + " ya esta registrado. Ingrese otro");
        }

        if(existente2 != null)
        {
            //Si no es nulo, ¡el cliente ya existe! Detenemos el proceso
            throw new RuntimeException("El correo " + cliente.getEmail() + " ya esta registrado. Ingrese otro");
        }

        if(existente3 != null)
        {
            //Si no es nulo, ¡el cliente ya existe! Detenemos el proceso
            throw new RuntimeException("La cedula " + cliente.getCedula() + " ya esta registrado. Ingrese otro");
        }

        if(existente4 != null)
        {
            //Si no es nulo, ¡el cliente ya existe! Detenemos el proceso
            throw new RuntimeException("El telefono " + cliente.getTelefono() + " ya esta registrado. Ingrese otro");
        }

        //Si es null, el proceso continua (no hace nada y es void)

    }

    //Lista de clasificación de dominios
    List<String> DOMINIOS_UCAB = Arrays.asList(
            "@ucab.edu.ve",
            "@est.ucab.edu.ve"
    );

    List<String> DOMINIOS_VISITANTES = Arrays.asList(
            "@gmail.com",
            "@hotmail.com",
            "@outlook.com",
            "@yahoo.com",
            "@aol.com",
            "@gmx.es",
            "@gmax.com",
            "@mail.com",
            "@icloud.com"
    );

    //Implementación del metodo de validación de dominio
    @Override
    public void validarDominio(Cliente cliente) {

        String email = cliente.getEmail();
        String tipoSeleccionado = cliente.getTipodePersona();

        //Chequear si el email coincide con algun dominio UCAB
        boolean esUcab = DOMINIOS_UCAB.stream().anyMatch(email::endsWith);

        //Chequear si el email coincide con algun dominio VISITANTE
        boolean esVisitante = DOMINIOS_VISITANTES.stream().anyMatch(email::endsWith);


        //Validar si no coinciden en ninguna de las dos listas, lanzamos error
        if(!esUcab && !esVisitante)
        {
            throw new RuntimeException("El dominio de email " + email + " no esta permitido");
        }

        // ------------------ Verificacion de tipodePersona con cliente -------------------------
        //Si el usuario selecciono UCAB
        if("UCAB".equalsIgnoreCase(tipoSeleccionado))
        {
            if(!esUcab)
            {
                //Si selecciono UCAB pero el email no es de dominio UCAB
                throw new RuntimeException("El email proporcionado no coincide con el dominio UCAB");
            }
        }
        //Si el usuario selecciono VISITANTE
        else if("VISITANTE".equalsIgnoreCase(tipoSeleccionado))
        {
            if(!esVisitante)
            {
                //Si selecciono VISITANTE pero el email no es de dominio VISITANTE
                throw new RuntimeException("El email proporcionado no coincide con el dominio VISITANTE");
            }
        }
        //Si el usuario envio un tipo invalido
        else
        {
            throw new RuntimeException("El tipo de persona seleccionado es invalido");
        }

    }

    //Implementación del metodo de validar espacios
    @Override
    public void validarSinEspacios(Cliente cliente) {

        //--------------------- Credenciales de Acceso --------------------------------
        //Usuario
        if(estanVacio(cliente.getUsuario()) || espaciosBlanco(cliente.getUsuario()))
        {
            throw new RuntimeException("EL usuario ingresado no puede estar vacio o con espacios en blanco");
        }

        //Email
        if(estanVacio(cliente.getEmail()) || espaciosBlanco(cliente.getEmail()))
        {
            throw new RuntimeException("El email ingresado no puede estar vacio o con espacios en blanco");
        }

        //Contraseña
        if(estanVacio(cliente.getContrasena()) || espaciosBlanco(cliente.getContrasena()))
        {
            throw new RuntimeException("El contraseña ingresada no puede estar vacio o con espacios en blanco");
        }

        //Confirmar Contraseña
        if(estanVacio(cliente.getConfirmarContrasena()) || espaciosBlanco(cliente.getConfirmarContrasena()))
        {
            throw new RuntimeException("La contraseña repetida ingresada no puede estar vacio o con espacios en blanco");
        }

        //----------------------------------- Datos Personales --------------------------------------------------
        //Cedula
        if(estanVacio(cliente.getCedula()) || espaciosBlanco(cliente.getCedula()))
        {
            throw new RuntimeException("La cedula ingresada no puede estar vacio o con espacios en blanco");
        }

        //Nombre
        if(estanVacio(cliente.getNombre()))
        {
            throw new RuntimeException("El nombre no puede ir vacio");
        }

        //Apellido
        if(estanVacio(cliente.getApellido()))
        {
            throw new RuntimeException("El apellido no puede ir vacio");
        }

        //Tipo de Persona
        if(estanVacio(cliente.getTipodePersona()))
        {
            throw new RuntimeException("El tipo de persona no puede ir vacio");
        }

        //----------------------------------------- Contacto y Ubicación -----------------------------------------
        //Telefono
        if(estanVacio(cliente.getTelefono()) || espaciosBlanco(cliente.getTelefono()))
        {
            throw new RuntimeException("El telefono ingresado no puede ir con espacios en blanco");
        }

        //Dirección
        if(estanVacio(cliente.getDireccion()))
        {
            throw new RuntimeException("La dirreción no puede ir vacio");
        }


    }

    // Funciones privadas que verifica si una cadena es nula, esta vacia o tiene espacios en blanco
    private boolean estanVacio(String texto)
    {
        if(texto == null || texto.trim().isEmpty())
        {
            return true; //Es nulo o esta vacio
        }

        return false;
    }

    private boolean espaciosBlanco(String texto)
    {
        if(texto.contains(" "))
        {
            return true;
        }
        return false;
    }

    private static final String CONTRASEÑA_FORMATO="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8}$";

    //Implementar el metodo de la validación de cedula con el formato establecido
    @Override
    public void validarContrasena(Cliente cliente) {

        String contrasena= cliente.getContrasena();

        //Validar el formato de la cedula
        if(!contrasena.matches(CONTRASEÑA_FORMATO))
        {
            throw new RuntimeException("El formato de la contraseña es invalida. Ejemplo del formato: 05Ar56$2");
        }

    }

    @Override
    public void validarContrasenas(Cliente cliente) {

        String confirmarContrasena= cliente.getConfirmarContrasena();
        String contrasena = cliente.getContrasena();

        //Usamos equals para una comparación exacta
        if(!contrasena.equals(confirmarContrasena))
        {
            throw new RuntimeException("La contraseña y la confirmación de constraseña no coinciden");
        }

    }

    private static final String CEDULA_FORMATO = "^[VEJ]-?\\d{6,9}$";

    //Implementar el metodo de la validación de cedula con el formato establecido
    @Override
    public void validarCedula(Cliente cliente)
    {
        String cedula = cliente.getCedula();

        //Validar el formato de la cedula
        if(!cedula.matches(CEDULA_FORMATO))
        {
            throw new RuntimeException("El formato de la cedula es invalida. Use el formato: [V, E, J]-Números");
        }
    }

    private static final String TELEFONO_FORMATO = "^(\\+[0-9]{2}-)?[0-9]{3}-?[0-9]{7}$";

    //Implementar el metodo de la validación de telefono con el formato establecido
    @Override
    public void validarTelefono(Cliente cliente)
    {
        String telefono = cliente.getTelefono();

        //Validar el formato del telefono
        if(!telefono.matches(TELEFONO_FORMATO))
        {
            throw new RuntimeException("El formato del telefono es invalida. Use el formato con 10 digitos, opcionalmente con codigo de país (Ejemplo: 0412-1234567 o +58-4121234567");
        }
    }


    @Override
   public Cliente registrarCliente(Cliente cliente) {

        //Verificar si el cliente existe en la base de datos (Verificación de unicidad)
        verificacionUnica(cliente);

        //Verifica si el cliente registro un email permitido (UCAB o VISITANTE) y es del tipo correcto
        validarDominio(cliente);

        //Validar metodo de espacios en blanco y vacio
        validarSinEspacios(cliente);

        //Validar metodo de la contraseña con el formato establecido
        validarContrasena(cliente);

        //Validar metodo para confirmar igualar contraseña y confirmar contraseña
        validarContrasenas(cliente);

        //Validar metodo de la cedula con el formato establecido
        validarCedula(cliente);

        //Validar metodo del telefono con el formato establecido
        validarTelefono(cliente);

        return clienteRepository.guardar(cliente);
    }


}

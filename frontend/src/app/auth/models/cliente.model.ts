export interface Cliente
{
    //Credenciales de Acceso 
    id?:number; 
    usuario: string; 
    email:string; 
    contrasena:string; 
    confirmarContrasena:string; 

    //Datos Personales 
    cedula:string; 
    nombre: string; 
    apellido:string; 
    tipodePersona:'UCAB' | 'VISITANTE'; 

    //Contactos y Ubicación 
    telefono: string; 
    direccion:string; 
}
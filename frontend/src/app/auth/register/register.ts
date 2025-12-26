import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators, ValidatorFn } from '@angular/forms';
import { AuthService} from '../auth'; 
import { Cliente } from '../models/cliente.model'; 
import { ReactiveFormsModule } from '@angular/forms';


@Component({
  selector: 'app-register',
  templateUrl: './register.html',
  styleUrl: './register.css',
  imports: [ReactiveFormsModule]
})

export class register implements OnInit {

  registerForm!: FormGroup; 
  errorMessaje: string | null = null; 
  successMessage: string | null = null; 

  //Variable para controlar la sección visible 
  currentStep: number = 1; 

  //Total de pasos del formulario
  totalStep: number = 5; //(Credenciales + Datos Personales + Contacto y Ubicación + Vista + Confirmación ) 


  //Relacionar el FormBuilder para contruir el formulario y el AuthService
  constructor(private fb:FormBuilder, private authService: AuthService)
  {

  }



  ngOnInit(): void {
      this.initializeForm(); 
  }

  //Validador personalizado 
  passwordMatchValidator: ValidatorFn = (control: AbstractControl): {[key:string]: any} | null => 
  {
    const password = control.get('contrasena')?.value; 
    const confirmPassword = control.get('confirmarContrasena')?.value;

    if (password && confirmPassword && password != confirmPassword)
    {
      //Si no coinciden, devuelve un error 
      return {'mismatch': true}
    }

    //Si coinciden o uno de los campos no esta lleno, no hay error de coincidencia 
    return null; 

  }; 


  initializeForm(): void{


    this.registerForm = this.fb.group({
      //Credenciales de Acceso 
      usuario: ['', [Validators.required, Validators.minLength(8)]],
      email: ['', [Validators.required, Validators.email]], 
      contrasena: ['', [Validators.required]], 
      confirmarContrasena: ['', [Validators.required]], 

      //Datos Personales 
      cedula: ['', [Validators.required]],
      nombre: ['', [Validators.required]],
      apellido: ['', [Validators.required]],
      tipodePersona: ['', [Validators.required]],

      //Contacto
      telefono: ['', [Validators.required]],
      direccion: ['', [Validators.required]],
    },
  {
    validators: this.passwordMatchValidator
  });

    this.registerForm.get('contrasena')?.valueChanges.subscribe(() => 
    {
      this.registerForm.get('confirmarContrasena')?.updateValueAndValidity();
    });

    this.registerForm.get('confirmarContrasena')?.valueChanges.subscribe(() => 
    {
      this.registerForm.updateValueAndValidity();
    });
  }

  // --------------------------------------- Nuevas Funciones de Navegación -----------------------------------------
  //Obtener un subconjunto de controles para validar antes de avanzar 
  getStepControls(step: number): string[]
  {
    switch (step)
    {
      case 1: //Credenciales de Acceso
        return ['usuario', 'email', 'contrasena', 'confirmarContrasena']; 
      case 2: //Datos Personales (UCAB/ Estacionamiento)
        return ['cedula', 'nombre', 'apellido', 'tipodePersona']; 
      case 3: //Contacto y Ubicación 
        return ['telefono', 'direccion']; 
      case 4: //Revisión final 
        return []; 
      default: 
        return []; 
    }
  }

  //Verifica si los campos del paso actual son válidos 
  isCurrentStepValid(): boolean
  {
    const controls = this.getStepControls(this.currentStep); 
    let allValid = true; 

    for (const controlName of controls)
    {
      const control = this.registerForm.get(controlName); 

      //Comprobar si el control existe y si es valido o no esta tocado
      if(control)
      { 
        //Marcar como tocado para mostrar errores visualmente 
        control.markAsTouched(); 

        //Luego, revisamos solo si es invalido 
        if(control.invalid)
        {

          console.log(`Control INVÁLIDO en Paso ${this.currentStep}: ${controlName}`);
          console.log('Errores:', control.errors);

          allValid = false; 
        }
      }
    }

          if(!allValid)
      {
        return false; 
      }

      if(this.currentStep === 1)
      {
        if(this.registerForm.errors?.['mismatch'])
        {
          return false; 
        }
      }

      return true; 

  }

  //Avanza al siguiente paso si el actual es valido 
  goToNextStep(): void
  {
    if(this.isCurrentStepValid())
    {
      this.errorMessaje = null; //Limpia los errores si la validacion fue exitosa

      if(this.currentStep === 4)
      {
        this.onSubmit();  
      }
      else if(this.currentStep < 4)
      {
        //Si es el ultimo paso, llama al metodo de envio
        this.currentStep ++; 
      }
    }
    else
    {
      this.errorMessaje = ' Por favor llenar correctamente los campos solicitados ';
    }
  }


  //Vuelve al paso anterior 
  goToPreviousStep(): void
  {
    if(this.currentStep > 1)
    {
      this.currentStep --; 
      this.errorMessaje = null;  //Limpiar mensaje de error al retroceder
    }
  }


  //Metodo de envio de datos (Se llama en el ultimo paso)
  onSubmit(): void{
    this.errorMessaje = null; 
    this.successMessage = null;

    //Dado que ya validamos el paso actual en goToNextStep
    //Solo validamos el estado final del formulario 
    if(this.registerForm.valid)
    {

      //Creamos el objeto cliente para enviarlo 
      const clienteData: Cliente = this.registerForm.value; 

      this.authService.registrar(clienteData).subscribe({
        next: (Response) => 
        {
          this.successMessage = `Registro exitoso  Cliente ${Response.nombre} ${Response.apellido} registrado`; 
          this.currentStep = 5; 
        },
        error: (err)=>
        {
          //Si el Backend responde con un error de negocio 
          const errorMsg = err.error && typeof err.error === 'string'

                          ? err.error :'Ocurrio un error inesperado al registrarse. '; 
          
          this.errorMessaje = errorMsg; 
          console.error('Error de registro: ', err); 
        }
      }); 
    }
    else{
      this.errorMessaje = 'Por favor, complete correctamente todos los campos'; 
    }
  }

}

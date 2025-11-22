import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { register} from './auth/register/register';

export const routes: Routes = [

    //Ruta principal: Carga el componente de Portada/Home
    { path: '',  component: Home}, 

    //Ruta de registro: Carga el componente de Registro
    { path: 'registro', component: register},

    // Opcional: una ruta de fallback para URLs no encontradas
    { path: '**', redirectTo: '' }

];

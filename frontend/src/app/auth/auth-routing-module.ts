import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'registro',
    //Usamos loadComponent para cargar la clase registrar
    loadComponent: ()=> import('./register/register').then( m => m.register)
  },
  {
    path:'', redirectTo: 'registro', pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }

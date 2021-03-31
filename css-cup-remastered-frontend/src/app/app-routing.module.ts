import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UebersichtComponent} from "./uebersicht/uebersicht.component";
import {AdministrationComponent} from "./administration/administration.component";
import {AuthComponent} from "./auth/auth.component";

const routes: Routes = [
  // {
  //   path: 'home',
  //   pathMatch: 'full',
  //   component: null,
  // },
  {
    path: 'administration',
    component: AdministrationComponent
  },
  // {
  //   path: 'profile',
  //   component: null
  // },
  {
    path: 'uebersicht',
    component: UebersichtComponent,

  },
  // {
  //   path: 'begegnungen',
  //   component: null,
  // },
  {
    path: 'auth/login',
    component: AuthComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

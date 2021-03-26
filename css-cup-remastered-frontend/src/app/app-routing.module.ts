import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UebersichtComponent} from "./uebersicht/uebersicht.component";
import {AdministrationComponent} from "./administration/administration.component";

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
  // }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

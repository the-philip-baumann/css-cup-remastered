import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UebersichtComponent} from "./uebersicht/uebersicht.component";
import {AdministrationComponent} from "./administration/administration.component";
import {AuthComponent} from "./auth/auth.component";
import {CreateTeamComponent} from "./create-team/create-team.component";
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
  {
    path: 'home',
    pathMatch: 'full',
    component: HomeComponent,
  },
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
  {
    path: 'team-create',
    component: CreateTeamComponent
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

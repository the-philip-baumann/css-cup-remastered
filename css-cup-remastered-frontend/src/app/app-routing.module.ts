import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UebersichtComponent} from "./uebersicht/uebersicht.component";
import {AdministrationComponent} from "./administration/administration.component";
import {AuthComponent} from "./auth/auth.component";
import {CreateTeamComponent} from "./create-team/create-team.component";
import {HomeComponent} from "./home/home.component";
import {RegisterComponent} from "./register/register.component";

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
  {
    path: 'uebersicht',
    component: UebersichtComponent,

  },
  {
    path: 'team-create',
    component: CreateTeamComponent
  },
  {
    path: 'auth/login',
    component: AuthComponent
  },
  {
    path: 'auth/register',
    component: RegisterComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

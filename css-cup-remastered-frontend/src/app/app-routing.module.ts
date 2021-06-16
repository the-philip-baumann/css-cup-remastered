import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UebersichtComponent} from "./uebersicht/uebersicht.component";
import {AdministrationComponent} from "./administration/administration.component";
import {AuthComponent} from "./auth/auth.component";
import {CreateTeamComponent} from "./create-team/create-team.component";
import {HomeComponent} from "./home/home.component";
import {RegisterComponent} from "./register/register.component";
import {Role} from "./service/auth/role.enum";
import {AuthGuard} from "./service/auth/auth.guard";

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: HomeComponent,
  },
  {
    path: 'administration',
    component: AdministrationComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_ADMIN]}
  },
  {
    path: 'uebersicht',
    component: UebersichtComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_PARTICIPANT, Role.ROLE_CAPTAIN, Role.ROLE_ADMIN]}
  },
  {
    path: 'team-create',
    component: CreateTeamComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_CAPTAIN, Role.ROLE_ADMIN]}
  },
  {
    path: 'auth/login',
    component: AuthComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ROLE_UNDECIDED]}
  },
  {
    path: 'auth/register',
    component: RegisterComponent,
    // data: {roles: [Role.ROLE_UNDECIDED]}
    // canActivate: [AuthGuard],
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

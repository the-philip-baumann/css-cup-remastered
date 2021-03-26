import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  // {
  //   path: 'home',
  //   pathMatch: 'full',
  //   component: null,
  // },
  // {
  //   path: 'administration',
  //   component: null
  // },
  // {
  //   path: 'profile',
  //   component: null
  // },
  // {
  //   path: 'uebersicht',
  //   component: null
  // },
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

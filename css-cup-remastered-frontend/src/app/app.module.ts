import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { UebersichtComponent } from './uebersicht/uebersicht.component';
import { AdministrationComponent } from './administration/administration.component';
import { AuthComponent } from './auth/auth.component';
import { HttpClientModule } from "@angular/common/http";
import { CreateTeamComponent } from './create-team/create-team.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    UebersichtComponent,
    AdministrationComponent,
    AuthComponent,
    CreateTeamComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

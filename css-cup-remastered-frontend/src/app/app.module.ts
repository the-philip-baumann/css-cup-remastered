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
import {FormsModule} from "@angular/forms";
import { DisciplineSwitchButtonComponent } from './util/discipline-switch-button/discipline-switch-button.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    UebersichtComponent,
    AdministrationComponent,
    AuthComponent,
    CreateTeamComponent,
    DisciplineSwitchButtonComponent,
    HomeComponent,
    RegisterComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

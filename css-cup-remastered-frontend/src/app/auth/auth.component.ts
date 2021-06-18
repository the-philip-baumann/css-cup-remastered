import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../service/auth/auth.service";
import {AuthState} from "../service/auth/auth.state";

@Component({
  selector: 'app-login',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit {

  loginCredentials = {
    email: 'philip.baumann@sluz.ch',
    password: 'test'
  }

  error = {
    ok: false,
    message: 'Invalid Credentials'
  }
  private displayLoginFailed: boolean;

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
  }

  async navigateToRegister(): Promise<void> {
    await this.router.navigate(['/auth/register'])
  }

  async login(): Promise<void> {
    if (this.loginCredentials.email.length == 0 || !this.loginCredentials.email.includes('@')) {
      this.error.ok = true
      return
    }

    if (this.loginCredentials.email.length <= 5) {
      this.error.ok = true
      return
    }

    let state: AuthState = await this.authService.login({email: this.loginCredentials.email, password: this.loginCredentials.password})

    if (state.ok) {
      await this.router.navigate([''])
    } else {
      this.displayLoginFailed = true
    }

  }
}

import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../service/auth/auth.service";
import {RegisterDto} from "../service/dto/register.dto";
import {AuthState} from "../service/auth/auth.state";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  isFootball: boolean
  role: 'Captain'
  errorMessage: 'Invalid Attempt To Register'
  displayError: boolean

  options = [
    'Captain',
    'Participant'
  ]

  constructor(private authService: AuthService, private router: Router) {
    this.displayError = false
    this.registerForm = new FormGroup({
      firstname: new FormControl('', [
        Validators.minLength(1),
        Validators.maxLength(30),
        Validators.required
      ]),
      lastname: new FormControl('', [
        Validators.minLength(1),
        Validators.maxLength(30),
        Validators.required
      ]),
      email: new FormControl('', [
        Validators.minLength(5),
        Validators.maxLength(50),
        Validators.email,
        Validators.required
      ]),
      func: new FormControl('', [
        Validators.minLength(1),
        Validators.maxLength(8),
        Validators.required,
      ]),
      password: new FormControl('', [
        Validators.minLength(5),
        Validators.maxLength(30),
        Validators.required
      ]),
      passwordConfirm: new FormControl('', [
        Validators.minLength(5),
        Validators.maxLength(30),
        Validators.required
      ]),
      role: new FormControl('Captain')
    })
  }

  ngOnInit(): void {
  }

  async register(form: FormGroup) {

    if (form.valid && form.get('password').value == form.get('passwordConfirm').value) {
      let registerDto: RegisterDto = {
        firstname: form.get('firstname').value,
        lastname: form.get('lastname').value,
        email: form.get('email').value,
        function: form.get('func').value,
        discipline: this.isFootball == true ? 'FOOTBALL' : 'VOLLEYBALL',
        password: form.get('password').value,
        role: form.get('role').value.toUpperCase()
      }

      const authState: AuthState = await this.authService.register(registerDto)

      console.log(authState)

      if (authState.ok) {
        await this.router.navigate([''])
      } else {
        this.displayError = true
      }
    } else {
      this.displayError = true
    }


  }

  switchDiscipline($event: boolean) {
    this.isFootball = $event;
  }
}

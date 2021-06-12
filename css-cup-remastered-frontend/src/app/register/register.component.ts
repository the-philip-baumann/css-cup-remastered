import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;

  constructor() {
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
      password: new FormControl('', [
        Validators.minLength(5),
        Validators.maxLength(30),
        Validators.required
      ]),
      passwordConfirm: new FormControl('', [
        Validators.minLength(5),
        Validators.maxLength(30),
        Validators.required
      ])

    })
  }

  ngOnInit(): void {
  }

  register(form: FormGroup) {
    console.log(form)
    console.log(form.errors)
    console.log('asdfasdf')
  }
}

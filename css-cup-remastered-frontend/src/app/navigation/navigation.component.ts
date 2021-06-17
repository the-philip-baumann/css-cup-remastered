import { Component, OnInit } from '@angular/core';
import {AuthService} from "../service/auth/auth.service";
import has = Reflect.has;

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  constructor(public authService: AuthService) { }

  ngOnInit(): void {
  }
}

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-discipline-switch-button',
  templateUrl: './discipline-switch-button.component.html',
  styleUrls: ['./discipline-switch-button.component.scss']
})
export class DisciplineSwitchButtonComponent implements OnInit {

  constructor() { }

  football = true;

  ngOnInit(): void {
  }

  switchDiscipline(state: boolean): void {
    this.football = state;
  }

}

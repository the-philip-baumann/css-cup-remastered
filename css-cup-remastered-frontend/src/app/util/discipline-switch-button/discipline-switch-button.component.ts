import {Component, OnInit, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-discipline-switch-button',
  templateUrl: './discipline-switch-button.component.html',
  styleUrls: ['./discipline-switch-button.component.scss']
})
export class DisciplineSwitchButtonComponent implements OnInit {

  @Output()
  emitter = new EventEmitter<boolean>()

  constructor() { }

  isFootball = true

  ngOnInit(): void {
  }

  switchDiscipline(state: boolean): void {
    this.isFootball = state
    this.emit()
  }

  emit(): void {
    this.emitter.emit(this.isFootball)
  }

}

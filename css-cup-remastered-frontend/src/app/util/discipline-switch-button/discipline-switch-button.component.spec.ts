import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisciplineSwitchButtonComponent } from './discipline-switch-button.component';

describe('DisciplineSwitchButtonComponent', () => {
  let component: DisciplineSwitchButtonComponent;
  let fixture: ComponentFixture<DisciplineSwitchButtonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisciplineSwitchButtonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisciplineSwitchButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

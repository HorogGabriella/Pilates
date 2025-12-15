import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassComponent } from './class.component';

describe('ClassesComponent', () => {
  let component: ClassComponent;
  let fixture: ComponentFixture<ClassComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClassComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClassComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

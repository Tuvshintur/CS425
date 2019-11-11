import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TasktosprintdialogComponent } from './tasktosprintdialog.component';

describe('TasktosprintdialogComponent', () => {
  let component: TasktosprintdialogComponent;
  let fixture: ComponentFixture<TasktosprintdialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TasktosprintdialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TasktosprintdialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

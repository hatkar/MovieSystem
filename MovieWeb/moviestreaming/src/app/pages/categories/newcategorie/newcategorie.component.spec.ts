import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewcategorieComponent } from './newcategorie.component';

describe('NewcategorieComponent', () => {
  let component: NewcategorieComponent;
  let fixture: ComponentFixture<NewcategorieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewcategorieComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewcategorieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

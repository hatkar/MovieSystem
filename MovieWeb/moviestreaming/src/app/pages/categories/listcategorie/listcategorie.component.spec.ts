import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListcategorieComponent } from './listcategorie.component';

describe('ListcategorieComponent', () => {
  let component: ListcategorieComponent;
  let fixture: ComponentFixture<ListcategorieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListcategorieComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListcategorieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

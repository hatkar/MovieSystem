import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchedmovieComponent } from './searchedmovie.component';

describe('SearchedmovieComponent', () => {
  let component: SearchedmovieComponent;
  let fixture: ComponentFixture<SearchedmovieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchedmovieComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchedmovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

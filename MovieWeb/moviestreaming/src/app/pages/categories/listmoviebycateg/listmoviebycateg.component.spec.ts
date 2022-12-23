import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListmoviebycategComponent } from './listmoviebycateg.component';

describe('ListmoviebycategComponent', () => {
  let component: ListmoviebycategComponent;
  let fixture: ComponentFixture<ListmoviebycategComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListmoviebycategComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListmoviebycategComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

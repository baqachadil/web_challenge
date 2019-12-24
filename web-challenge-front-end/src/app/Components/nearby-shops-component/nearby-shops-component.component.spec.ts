import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NearbyShopsComponentComponent } from './nearby-shops-component.component';

describe('NearbyShopsComponentComponent', () => {
  let component: NearbyShopsComponentComponent;
  let fixture: ComponentFixture<NearbyShopsComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NearbyShopsComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NearbyShopsComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

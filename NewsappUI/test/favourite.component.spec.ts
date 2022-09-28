import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { FavouriteComponent } from '../src/app/favourite/favourite.component';

describe('FavouriteComponent', () => {
  let component: FavouriteComponent;
  let fixture: ComponentFixture<FavouriteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FavouriteComponent ],
      schemas: [NO_ERRORS_SCHEMA]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FavouriteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create favourite component', () => {
    const fixture = TestBed.createComponent(FavouriteComponent);
    const favourite = fixture.debugElement.componentInstance;
    expect(favourite).toBeTruthy();
  });
});

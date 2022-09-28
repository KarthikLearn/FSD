import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommendedComponent } from '../src/app/recommended/recommended.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('RecommendedComponent', () => {
  let component: RecommendedComponent;
  let fixture: ComponentFixture<RecommendedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecommendedComponent ],
      schemas: [NO_ERRORS_SCHEMA]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommendedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create recommend component ', () => {
    const fixture = TestBed.createComponent(RecommendedComponent);
    const recommend = fixture.debugElement.componentInstance;
    expect(recommend).toBeTruthy();
  });
});

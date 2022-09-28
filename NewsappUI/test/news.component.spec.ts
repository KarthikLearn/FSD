import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsComponent } from '../src/app/news/news.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('NewsComponent', () => {
  let component: NewsComponent;
  let fixture: ComponentFixture<NewsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsComponent ],
      schemas: [NO_ERRORS_SCHEMA]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create News Component', () => {
    const fixture = TestBed.createComponent(NewsComponent);
    const news = fixture.debugElement.componentInstance;
    expect(news).toBeTruthy();
  });
  
});

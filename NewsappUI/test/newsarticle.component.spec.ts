import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsarticleComponent } from '../src/app/newsarticle/newsarticle.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('NewsarticleComponent', () => {
  let component: NewsarticleComponent;
  let fixture: ComponentFixture<NewsarticleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsarticleComponent ],
      schemas: [NO_ERRORS_SCHEMA]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsarticleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create NewsArticle Component', () => {
    const fixture = TestBed.createComponent(NewsarticleComponent);
    const NewsArticle = fixture.debugElement.componentInstance;
    expect(NewsArticle).toBeTruthy();
  });

  it('should have a Favourite button', () => {
    const fixture = TestBed.createComponent(NewsarticleComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('button').textContent).toContain('Add Favourite');
  });

});

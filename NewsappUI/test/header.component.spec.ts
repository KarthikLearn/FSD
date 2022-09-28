import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderComponent } from '../src/app/header/header.component';

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create app', () => {
    const fixture = TestBed.createComponent(HeaderComponent);
    const head = fixture.debugElement.componentInstance;
    expect(head).toBeTruthy();
  });

  it(`should have as title 'Newsapp'`, () => {
    const fixture = TestBed.createComponent(HeaderComponent);
    const head = fixture.debugElement.componentInstance;
    expect(head.title).toEqual('Newsapp');
  });

  it('should render title in a h1 tag', () => {
    const fixture = TestBed.createComponent(HeaderComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h1').textContent).toContain('Welcome to Newsapp');
  });
  
});

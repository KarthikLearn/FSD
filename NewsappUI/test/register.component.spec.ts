import { async, ComponentFixture, TestBed, fakeAsync, tick, inject} from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { RegisterComponent } from '../src/app/register/register.component';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthenticationService } from '../src/app/services/authentication.service';
import { User } from '../src/app/model/user';
import { HttpClientTestingModule,HttpTestingController } from '@angular/common/http/testing';


describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let authenticationService: AuthenticationService;
  let user: User;
  let http: HttpTestingController;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule
      ],
      providers: [
        AuthenticationService],
      declarations: [ RegisterComponent ],
      schemas: [NO_ERRORS_SCHEMA]
    })
    .compileComponents();
  }));
  afterEach(() => http.verify());
  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    authenticationService = TestBed.get(AuthenticationService);
    http = TestBed.get(HttpTestingController);
    user = {"userId":"admin8","userPassword":"admin8","firstName":"admin","lastName":"admin","userEmail":"admin8@mail.com"}
    fixture.detectChanges();
  });

  it('should create register component', () => {
    const fixture = TestBed.createComponent(RegisterComponent);
    const register = fixture.debugElement.componentInstance;
    expect(register).toBeTruthy();
  });

  it('should handle to register the user in NewsApp', fakeAsync(() => {
    const mockData = {"userId":"admin8","userPassword":"admin8","firstName":"admin","lastName":"admin","userEmail":"admin8@mail.com"};
    
        const mockUrl = `http://localhost:8765/userService/api/v1/authService/register`;

        authenticationService.registerUser(user)
            .subscribe(() => { });
        const req = http.expectOne(mockUrl);
        expect(req.request.urlWithParams).toBe(mockUrl);
        expect(req.request.method).toBe('POST');
        expect(mockData).toEqual(user);
        req.flush({});
      }));

});

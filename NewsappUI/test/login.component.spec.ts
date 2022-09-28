import { async, ComponentFixture, TestBed, fakeAsync, tick, inject} from '@angular/core/testing';
import { Router } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Location } from '@angular/common';
import { By } from '@angular/platform-browser';
import { FormsModule, FormControl } from '@angular/forms';
import { NgForm } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSliderModule } from '@angular/material/slider';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatListModule } from '@angular/material/list';
import { MatStepperModule } from '@angular/material/stepper';
import { MatTabsModule } from '@angular/material/tabs';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ReactiveFormsModule } from '@angular/forms';
import { of } from 'rxjs';
import { HttpClientTestingModule,HttpTestingController } from '@angular/common/http/testing';




import { LoginComponent } from '../src/app/login/login.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthenticationService } from '../src/app/services/authentication.service';
import { RouterserviceService } from '../src/app/services/routerservice.service';
import { User } from '../src/app/model/user';
import { Http } from '@angular/http';
const testConfig = {
  error404: {
    message: 'Server not found',
    name: 'HttpErrorResponse',
    ok: false,
    status : 404,
    statusText: 'Not Found',
    url: 'http://localhost:8765/userService/api/v1/authService/login'
  },
  error403: {
    error: {message: 'Invalid UserId or Password'},
    message: 'Http failure response for http://localhost:8765/userService/api/v1/authService/login: 403 Forbidden',
    name: 'HttpErrorResponse',
    ok: false,
    status: 403,
    statusText: 'Forbidden',
    url: 'http://localhost:8765/userService/api/v1/authService/login'
  },
  positive: {
    token: 'token123'
  }
};

describe('LoginComponent', () => {
  let authenticationService: AuthenticationService;
  let positiveResponse: object;
  let loginComponent: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let spyAuthenticateUser: any;
  let spySetBearerToken: any;
  let spyRouteToDashboard: any;
  const routerSpy: any = {};
  let location: Location;
  let routerService: any;
  let errorMessage: any;
  let debugElement: any;
  let element: any;
  let http: HttpTestingController;
  let user: User;
  let user1: any;
 
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule,
      RouterTestingModule,     
      FormsModule,
      BrowserAnimationsModule,
      MatInputModule,
      MatAutocompleteModule,
      MatCheckboxModule,
      MatDatepickerModule,
      MatFormFieldModule,
      MatInputModule,
      MatRadioModule,
      MatSelectModule,
      MatSliderModule,
      MatSlideToggleModule,
      MatMenuModule,
      MatSidenavModule,
      MatToolbarModule,
      MatCardModule,
      MatExpansionModule,
      MatGridListModule,
      MatListModule,
      MatStepperModule,
      MatTabsModule,
      MatButtonModule,
      MatButtonToggleModule,
      MatChipsModule,
      MatIconModule,
      MatProgressSpinnerModule,
      MatProgressBarModule,
      MatDialogModule,
      MatSnackBarModule,
      MatTooltipModule,
      MatPaginatorModule,
      MatSortModule,
      MatTableModule,
      HttpClientModule,
      ReactiveFormsModule
      ],
      providers: [
        AuthenticationService,
        RouterserviceService,
        { provide: Location, useValue: {} },
        { provide: Router, useValue: routerSpy }
      ],
      declarations: [ LoginComponent ],
      schemas: [NO_ERRORS_SCHEMA]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    loginComponent = fixture.componentInstance;
    fixture.detectChanges();

    authenticationService = TestBed.get(AuthenticationService);
    http = TestBed.get(HttpTestingController);
    user = new User();
    
  

  });

  afterEach(() => http.verify());

  it('should create Login Component', () => {
    const fixture = TestBed.createComponent(LoginComponent);
    const login = fixture.debugElement.componentInstance;
    expect(login).toBeTruthy();
  });

  it('should handle to login into the NewsApp', fakeAsync(() => {
    const mockData = '{"message":"Login Success"}';
    const exceptData = '{"message":"Login Success"}';
        const mockUrl = `http://localhost:8765/userService/api/v1/authService/login`;

        authenticationService.validateLoginUser(user1)
            .subscribe(() => { });
        const req = http.expectOne(mockUrl);  
        expect(req.request.urlWithParams).toBe(mockUrl);
        expect(req.request.method).toBe('POST');
        expect(exceptData).toEqual(mockData);
        req.flush({});
      }));
  
});



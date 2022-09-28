import { TestBed ,async } from '@angular/core/testing';
import { AuthenticationService } from '../src/app/services/authentication.service';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { HttpClientTestingModule,HttpTestingController } from '@angular/common/http/testing';

beforeEach(() => {
  TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
    providers : [AuthenticationService]
  }).compileComponents();
});


describe('AuthenticationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AuthenticationService = TestBed.get(AuthenticationService);
    expect(service).toBeTruthy();
  });
});

import { TestBed, async, inject, fakeAsync } from '@angular/core/testing';
import { AuthenticationService } from '../src/app/services/authentication.service';
import { CanActivateGuard } from '../src/app/guard/can-activate.guard';
import { HttpClientModule } from '@angular/common/http';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { RouterserviceService } from '../src/app/services/routerservice.service';
import { Location } from '@angular/common';



describe('CanActivateGuard', () => {
  let canActivateRouteGuard: CanActivateGuard;
  const activatedRouteSnapshot: ActivatedRouteSnapshot = new ActivatedRouteSnapshot();
  const routerStateSnapshot: RouterStateSnapshot = jasmine.createSpyObj<RouterStateSnapshot>('RouterStateSnapshot', ['toString']);
  let authService: any;
  let spyCanActivate: any;
  let response: any;
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        { provide: Location, useValue: {back: () => { }} },
        { provide: Router, useValue: {} },
        CanActivateGuard,
        AuthenticationService,
        RouterserviceService
      ]
    });
  });

  it('should create can activate route guard', inject([CanActivateGuard], (guard: CanActivateGuard) => {
    expect(guard).toBeTruthy();
  }));

});

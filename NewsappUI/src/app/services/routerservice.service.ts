import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class RouterserviceService {

  constructor(private router:Router, private location:Location) { }

  toLogin(){
    this.router.navigate(['login']);
  }

  toRegister(){
    this.router.navigate(['register']);
  }
  toDashBoard(){
    this.router.navigate(['dashboard']);
  }

  routeBack() {
    this.location.back();
  }
}

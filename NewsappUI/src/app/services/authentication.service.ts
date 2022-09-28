import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  
  loginurl : string;
  registerurl : string;
  constructor(private httpClient:HttpClient) { 
      //this.loginurl = 'http://localhost:9111/api/v1/authService/login';
      //this.registerurl = 'http://localhost:9111/api/v1/authService/register';

     // this.loginurl = 'http://localhost:8765/userService/api/v1/authService/login';
     // this.registerurl = 'http://localhost:8765/userService/api/v1/authService/register';

      this.loginurl = 'http://10.0.75.2:8765/userService/api/v1/authService/login';
      this.registerurl = 'http://10.0.75.2:8765/userService/api/v1/authService/register';
  }

  validateLoginUser(data) {
    
    return this.httpClient.post(this.loginurl,data);
  }

  registerUser(userDetails){
    return this.httpClient.post(this.registerurl,userDetails);
  }
  
  setJwtToken(token) {
    localStorage.setItem('jwtToken',token);
  }

  getJwtToken() {
    return localStorage.getItem('jwtToken');
  }

  setUserId(userId){
    localStorage.setItem('userId',userId);
  }

  getUserId(){
    return localStorage.getItem('userId');
  }
  
}

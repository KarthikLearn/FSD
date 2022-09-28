import { Component, OnInit } from '@angular/core';
import { FormControl,Validators } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { RouterserviceService } from '../services/routerservice.service';

import { User } from '../model/user'
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username  = new FormControl('',Validators.required);
  password = new FormControl('',[Validators.required,Validators.minLength(5)]);
  user: User;
  errorMessage='';
  successMessage= '';

  constructor(private authService:AuthenticationService,private routeService:RouterserviceService) {
    this.user = new User();
   }

  ngOnInit() {
  }

  login(){
  
      this.user.userId = this.username.value;
      this.user.userPassword = this.password.value;
      this.authService.validateLoginUser(this.user).subscribe(
        data=>{
          this.authService.setJwtToken(data['token']);
          this.authService.setUserId(this.user.userId);
          this.routeService.toDashBoard();
          console.log("Login Success"+this.authService.getJwtToken());
        },
        error=>{
          if(error.status === 404)
          {
            this.successMessage = 'Server not found';
          }
          else if(error.status === 403){
            this.successMessage = 'Invalid UserId or Password';
          }
          else if(error.status === 401){
            this.successMessage = 'Invalid UserId or Password';
          }
          else{
            console.log("error--->"+error.message);
            console.log("error--->"+error);
            this.errorMessage = error.message;
            this.successMessage = 'Connection not available';
          }    
        }
      );
  
    
  }

  registerUser(){
    this.routeService.toRegister();
  }

}

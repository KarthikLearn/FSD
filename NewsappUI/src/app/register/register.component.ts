import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { RouterserviceService } from '../services/routerservice.service'

import { User } from '../model/user';
import * as EmailValidator from 'email-validator';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User;
  errMessage:string;
  validMessage  = 'Please fill all the fields';
  constructor(private authService:AuthenticationService,private routeService:RouterserviceService) { 

    this.user = new User();
  }

  ngOnInit() {
  }

  register(){
    
    if(this.user.firstName.trim().length<=0||this.user.lastName.trim().length<=0||
    this.user.userEmail.trim().length<=0||this.user.userId.trim().length<=0||
    this.user.userPassword.trim().length<=0){
     this.errMessage = this.validMessage;
      return;
    }

    if(!EmailValidator.validate(this.user.userEmail.trim())){
      this.errMessage = "Please enter valid email";
      return;
    } 
  
   this.authService.registerUser(this.user).subscribe(
    data=>{
        alert("User Registered Successfully, Please Login");
          this.routeService.toLogin();
    },
    error=>{
      if(error.status === 404)
      {
        this.errMessage = 'Server not found';
      }
      if(error.status === 409)
      {
        this.errMessage = 'Same UserId already exists';
      }
      else{
        this.errMessage = error.message;
      }
    }
  );
  this.user = new User();
  }

  cancel(){
    this.routeService.toLogin();
  }

}

import { Component, OnInit } from '@angular/core';
import { RouterserviceService } from '../services/routerservice.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private routeService:RouterserviceService) { }

  ngOnInit() {
  }

  userLogout(){
    this.routeService.toLogin();
    localStorage.clear();
  }

}

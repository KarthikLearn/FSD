import { Component, OnInit } from '@angular/core';
import {NewsDetailService} from '../services/news-detail.service';
import { News } from '../model/news';

@Component({
  selector: 'app-recommended',
  templateUrl: './recommended.component.html',
  styleUrls: ['./recommended.component.css']
})
export class RecommendedComponent implements OnInit {
  news: Array<News>;
  isFavourite:boolean;
  isFavCount:boolean = true;
  isFavRemove:boolean;
  successMessage= '';
  constructor(private newsservice:NewsDetailService) {
    this.news = [];
   }

  ngOnInit() {
    this.isFavourite = true;
    this.isFavCount = true;
    this.isFavRemove = true;
    this.newsservice.getRecommendList().subscribe(
      data=>{
            this.news = data as Array<News>;
      },
      error=>{
        if(error.status === 404)
         {
          console.log('Service not available'); 
          this.successMessage = 'Service not available';
          return 'News API Not Found';

         }
         else{
          console.log('Service not available');
         }
      }
    );
  }

}

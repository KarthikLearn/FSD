import { Component, OnInit } from '@angular/core';
import {NewsDetailService} from '../services/news-detail.service';
import { News } from '../model/news';

@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent implements OnInit {
  news: Array<News>;
  isFavourite:boolean;
  isFavCount:boolean;
  isFavRemove:boolean;
  successMessage= '';
  constructor(private newsservice:NewsDetailService) {
    this.news = [];
   }

  ngOnInit() {
    this.getFavouriteDetails();
}
getFavouriteDetails(){
  
  this.isFavourite = true;
  this.isFavCount = true;
  this.isFavRemove = false;
  this.newsservice.getUserFavouriteList().subscribe(
    data=>{
          this.news = data as Array<News>;
    },
    error=>{
      if(error.status === 404)
       {
        this.successMessage = 'Service not available';
        return 'News API Not Found';

       }
       else{
        this.successMessage = 'Service not available';
       }
    }
  );
}
removeFavourite(removeFavNews:News){

  this.newsservice.removeFavourite(removeFavNews).subscribe(
    data=>{
          this.news = data as Array<News>;
    },
    error=>{
      if(error.status === 404)
       {
        return 'News API Not Found';

       }
       else{
       // console.log('Service not available');
       }
    }
  );

}
}
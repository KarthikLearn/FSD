import { Component, OnInit, ɵConsole } from '@angular/core';
import {NewsDetailService} from '../services/news-detail.service';
import { News } from '../model/news';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit {
  news: Array<News>;
  public message: string;
  isFavourite:boolean = true;
  isFavCount:boolean = false;
  isFavRemove:boolean = false;
  public searchNews : string;
  filterNews: Array<News>;
  constructor(private newsservice:NewsDetailService) {
    this.news = [];
    this.filterNews = [];
   }

  ngOnInit() {
    this.isFavCount = true;
    this.isFavRemove = true;
    this.newsservice.getAllNewsDetails().subscribe(
      data=>{
        this.news = data['articles']
        this.filterNews = this.news;
       },
       error=>{
         if(error.status === 404)
         {
           return 'News API Not Found';
         }
   }
    );

  }

  favouriteMessage(message :string){
    this.message = message;
  }

  searchNewsTitle(newValue){
    if(newValue!=null&&newValue.length>=3){
      this.filterNews =[];
      for (var item of this.news) {
        if(item.title.substring(0,newValue.length) == newValue)
        {
          this.filterNews.push(item);
        }
    }
    
    }
    else{
      this.filterNews =[];
      this.filterNews = this.news;
    }
  }

}

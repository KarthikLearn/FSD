import { Component, OnInit,Input,Output, EventEmitter,ViewChild,NO_ERRORS_SCHEMA } from '@angular/core';
import {NewsDetailService} from '../services/news-detail.service'
import { News } from '../model/news';
import { MatTableDataSource, MatPaginator } from '@angular/material';

@Component({
  selector: 'app-newsarticle',
  templateUrl: './newsarticle.component.html',
  styleUrls: ['./newsarticle.component.css']
})
export class NewsarticleComponent implements OnInit {
  
  @Input()
  news:News = new News();
  @Output() 
  private message = new EventEmitter<string>();
  @Input()
  isFavourite:boolean;
  @Input()
  isFavCount:boolean;
  @Input()
  isFavRemove:boolean;
  @Output() removeFav = new EventEmitter();
  
  

  constructor(private newsservice:NewsDetailService) { }

  ngOnInit() {
  }

  addtofavourite(favouriteNews){
    this.newsservice.addToFavouriteList(favouriteNews).subscribe(
      data=>{
          this.message.emit("Favourite added successfully !");
      },
      error=>{
        this.message.emit("Unable to add or Already added !");
      }
    );
  }
  
  openNews(openNews){
    window.open(openNews.url);
  }

  removefavourite(removeNews:News){
    this.removeFav.emit(removeNews);
 }
}

import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { News } from '../model/news';
import { AuthenticationService } from './authentication.service';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class NewsDetailService {

  news: Array<News>;
  newsurl : string;
  favouriteServiceurl : string;
  favouritelistrurl : string;
  recommendServiceurl: string;
  removeFavouriteServiceurl:string;
  constructor(private httpClient:HttpClient,private authService:AuthenticationService) { 
    this.news = [];
    //this.newsurl = 'https://newsapi.org/v2/everything?q=bitcoin&apiKey=52acba73846d4622acf4f27812a07aaf';
    this.newsurl = 'https://newsapi.org/v2/top-headlines?country=in&apiKey=52acba73846d4622acf4f27812a07aaf';
    
    /* this.favouriteServiceurl = 'http://localhost:9112/api/v1/favouriteservice/addFavourite/';
    this.favouritelistrurl = 'http://localhost:9112/api/v1/favouriteservice/getAllFavourite/';
    this.recommendServiceurl ='http://localhost:9113/api/v1/recommendservice/getRecommendList';
    this.removeFavouriteServiceurl = 'http://localhost:9112/api/v1/favouriteservice/removeFavourite/'; */

    /* this.favouriteServiceurl = 'http://localhost:8765/favouriteservice/api/v1/favouriteservice/addFavourite/';
    this.favouritelistrurl = 'http://localhost:8765/favouriteservice/api/v1/favouriteservice/getAllFavourite/';
    this.removeFavouriteServiceurl = 'http://localhost:8765/favouriteservice/api/v1/favouriteservice/removeFavourite/';
    this.recommendServiceurl ='http://localhost:8765/recommendservice/api/v1/recommendservice/getRecommendList';
     */

    this.favouriteServiceurl = 'http://10.0.75.2:8765/favouriteservice/api/v1/favouriteservice/addFavourite/';
    this.favouritelistrurl = 'http://10.0.75.2:8765/favouriteservice/api/v1/favouriteservice/getAllFavourite/';
    this.removeFavouriteServiceurl = 'http://10.0.75.2:8765/favouriteservice/api/v1/favouriteservice/removeFavourite/';
    this.recommendServiceurl ='http://10.0.75.2:8765/recommendservice/api/v1/recommendservice/getRecommendList';
    
    
  }

  getAllNewsDetails(): Observable<any>{
    return this.httpClient.get(this.newsurl)
  }

  addToFavouriteList(news:News){

    let userId = this.authService.getUserId();
    let jwtToken = 'Bearer '+this.authService.getJwtToken();

    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json;charset=UTF-8')
    headers = headers.set('authorization', jwtToken);

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': jwtToken
        })};


   // console.log('jwtToken----------'+headers.get('authorization'));

    return this.httpClient.post(this.favouriteServiceurl+userId,news,httpOptions);
  }

  getUserFavouriteList(){
    
    let userId = this.authService.getUserId();
    let jwtToken = 'Bearer '+this.authService.getJwtToken();

    /* let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json;charset=UTF-8')
    headers = headers.set('Authorization', jwtToken); */

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': jwtToken
        })};

    console.log('JwtToken---------->'+jwtToken);
    httpOptions.headers.set('Authorization',jwtToken);
    return this.httpClient.get(this.favouritelistrurl+userId,httpOptions);

 }

  getRecommendList(){
    let jwtToken = this.authService.getJwtToken();
    return this.httpClient.get(this.recommendServiceurl,{
      headers : new HttpHeaders().set(
        'authorization',`Bearer ${this.authService.getJwtToken()}`
      )});
  }

  removeFavourite(news:News){
    let userId = this.authService.getUserId();
    let jwtToken = this.authService.getJwtToken();
    const requestData = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization':`Bearer ${this.authService.getJwtToken()}`
      }),
      body: news
    }
    return this.httpClient.delete(this.removeFavouriteServiceurl+userId,requestData);
  }

  getHttpOptions() {
    let jwtToken = 'Bearer '+this.authService.getJwtToken();
    const headers = {
       'Access-Control-Allow-Origin':'*',
        'Content-Type':  'application/json',
        'Authorization': jwtToken
      }
    return { withCredentials: true, headers: headers };
}

}

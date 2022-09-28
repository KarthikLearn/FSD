import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';

// Form module declarations
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// Material module declarations
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatExpansionModule} from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {MatPaginatorModule} from '@angular/material/paginator';
import {  MatSidenavModule, MatListModule, MatIconModule } from "@angular/material";
import { MatMenuModule} from '@angular/material/menu';

// Http Client module
import { HttpClientModule,HttpHeaders,HttpClient } from '@angular/common/http';
import { HttpModule } from '@angular/http';
// Router Module
import { RouterModule, Routes } from '@angular/router'

//Component List
import { HeaderComponent } from './header/header.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NewsComponent } from './news/news.component';
import { FavouriteComponent } from './favourite/favourite.component';
import { RecommendedComponent } from './recommended/recommended.component';
import { NewsarticleComponent } from './newsarticle/newsarticle.component';

//Service List 
import {AuthenticationService} from './services/authentication.service';
import {NewsDetailService} from './services/news-detail.service';
import {RouterserviceService} from './services/routerservice.service';
// Can Activate Guard
import {CanActivateGuard} from './guard/can-activate.guard';
import { FooterComponent } from './footer/footer.component';

const routes: Routes = [

  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "dashboard",
    component: DashboardComponent,
    canActivate:[CanActivateGuard],
    children:[
      {
        path:'',
        component:NewsComponent,
        pathMatch:'full'
      },
      {
        path:'view/news',
        component:NewsComponent,
      },
      {
        path:'view/favourite',
        component:FavouriteComponent,
      },
      {
        path:'view/recommended',
        component:RecommendedComponent,
      }
    ]
  },
  {
    path: "",
    component: LoginComponent
  },
  
];


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    RegisterComponent,
    DashboardComponent,
    NewsComponent,
    FavouriteComponent,
    RecommendedComponent,
    NewsarticleComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatDialogModule,
    MatSelectModule,
    HttpClientModule,
    HttpModule,
    MatSnackBarModule,
    MatPaginatorModule,
    MatSidenavModule, 
    MatListModule, 
    MatIconModule,
    MatMenuModule,
    RouterModule.forRoot(routes)
  ],
  providers: [AuthenticationService,NewsDetailService,RouterserviceService],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { browser, by, element,ElementFinder, promise } from 'protractor';

export class DashboardPage {
  navigateTo() {
    return browser.get('/');
  }

  getdashboardComponent(): ElementFinder {
    return element(by.tagName('app-dashboard'));
  }

  navigateToDashboard() {
    return browser.get('/dashboard');
  }
  
  getCurrentURL() {
    return browser.getCurrentUrl();
  }

  getNewSearchButton(): ElementFinder {
    return this.getdashboardComponent().element(by.className('news-button mat-button mat-button-base'));
  }

  isNewSearchButtonPresent(): promise.Promise<boolean> {
    return this.getNewSearchButton().isPresent();
  }

  clickNewSearchButton(): promise.Promise<void> {
    return this.getNewSearchButton().click();
  }

  getFavouriteButton(): ElementFinder {
    return this.getdashboardComponent().element(by.className('favourite-button mat-button mat-button-base'));
  }

  isFavouriteButtonPresent(): promise.Promise<boolean> {
    return this.getFavouriteButton().isPresent();
  }

  clickFavouriteButton(): promise.Promise<void> {
    return this.getFavouriteButton().click();
  }

  getRecommendButton(): ElementFinder {
    return this.getdashboardComponent().element(by.buttonText('Recommended'));
  }

  isRecommendButtonPresent(): promise.Promise<boolean> {
    return this.getRecommendButton().isPresent();
  }

  clickRecommendButton(): promise.Promise<void> {
    return this.getRecommendButton().click();
  }

  getLogoutButton(): ElementFinder {
    return this.getdashboardComponent().element(by.buttonText('Logout'));
  }

  isLogoutButtonPresent(): promise.Promise<boolean> {
    return this.getLogoutButton().isPresent();
  }

  clickLogoutButton(): promise.Promise<void> {
    return this.getLogoutButton().click();
  }

  navigatetoNewsSearch() {
    return browser.get('/dashboard/view/news');
  }

  navigatetoRecommend(){
    return browser.get('/dashboard/view/recommended');
  }

  navigatetoFavourite(){
    return browser.get('/dashboard/view/favourite');
  }

  navigatetoLogin(){
    return browser.get('/login');
  }

}

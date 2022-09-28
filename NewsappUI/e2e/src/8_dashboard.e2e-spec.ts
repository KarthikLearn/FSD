import { DashboardPage } from './dashboard.po';

describe('Dashboard Component', () => {
  let page: DashboardPage;

  beforeEach(() => {
    page = new DashboardPage();
  });

 
  it('should get Favourite button', () => {
    page.navigateToDashboard();
   // console.log('--------------------Fav valuse------------------------');
   // console.log(page.isFavouriteButtonPresent());
    expect(page.isFavouriteButtonPresent()).toBeTruthy(
      `<button class="favourite-button">Favourite</button> 
      should exist in dashboard.component.html`);
  });

  it('should get Recommended button', () => {
    page.navigateToDashboard();
    expect(page.isRecommendButtonPresent()).toBeTruthy(
      `<button class="recommended-button">Recommended</button> 
      should exist in dashboard.component.html`);
  });

  it('should get Search button', () => {
    page.navigateToDashboard();
    expect(page.isNewSearchButtonPresent()).toBeTruthy(
      `<button class="news-button">Search</button> 
      should exist in dashboard.component.html`);
  });

  it('should get Logout button', () => {
    page.navigateToDashboard();
    expect(page.isLogoutButtonPresent()).toBeTruthy(
      `<button class="filler">Logout</button> 
      should exist in dashboard.component.html`);
  });

  it('should Navigate to Favoutite tab', () => {
    page.navigateToDashboard();
    page.clickFavouriteButton();
    page.navigatetoFavourite();
    page.getCurrentURL().then((url) => {
      expect(page.getCurrentURL()).toContain('favourite', 'Should navigate to Favourite tab');
    });
  });

  it('should Navigate to Recommend tab', () => {
    page.navigateToDashboard();
    page.clickRecommendButton();
    page.navigatetoRecommend();
    page.getCurrentURL().then((url) => {
     expect(page.getCurrentURL()).toContain('recommended', 'Should navigate to Recommended tab');
    });
  });

  it('should Navigate to Search tab', () => {
    page.navigateToDashboard();
    page.clickNewSearchButton();
    page.navigatetoNewsSearch();
    page.getCurrentURL().then((url) => {
     expect(page.getCurrentURL()).toContain('news', 'Should navigate to Search tab');
    });
  });

  it('should Navigate to Login', () => {
    page.navigateToDashboard();
    page.clickLogoutButton();
    page.navigatetoLogin();
    page.getCurrentURL().then((url) => {
     expect(page.getCurrentURL()).toContain('login', 'Should navigate to Login Page');
    });
  });

});

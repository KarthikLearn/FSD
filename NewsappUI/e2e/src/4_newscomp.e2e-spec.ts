import { NewsPage } from './news.po';

describe('Search Component', () => {
  let page: NewsPage;

  beforeEach(() => {
    page = new NewsPage();
  });

  it('should get News Add Fav button present', () => {
    page.navigateToNewsSearch();
    expect(page.isAddFavButtonPresent()).toBeTruthy(
      `<button class="mat-button">Add Favourite</button> 
      should exist in news.component.html`);
  });

  it('should get Click more info button present', () => {
    page.navigateToNewsSearch();
    expect(page.isMoreInfoButtonPresent()).toBeTruthy(
      `<button class="mat-button">Click for more info</button> 
      should exist in news.component.html`);
  });
 
});

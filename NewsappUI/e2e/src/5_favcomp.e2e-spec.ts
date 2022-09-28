import { FavPage } from './fav.po';

describe('Favourite Component', () => {
  let page: FavPage;

  beforeEach(() => {
    page = new FavPage();
  });


  it('should get Click more info button present', () => {
    page.navigateToFavPage();
    expect(page.isMoreInfoButtonPresent()).toBeTruthy(
      `<button class="mat-button">Click for more info</button> 
      should exist in favourite.component.html`);
  });
 
});

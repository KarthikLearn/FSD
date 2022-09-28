import { RecPage } from './rec.po';

describe('Favourite Component', () => {
  let page: RecPage;

  beforeEach(() => {
    page = new RecPage();
  });


  it('should get Click more info button present', () => {
    page.navigateToRecPage();
    expect(page.isMoreInfoButtonPresent()).toBeTruthy(
      `<button class="mat-button">Click for more info</button> 
      should exist in recommended.component.html`);
  });
 
});

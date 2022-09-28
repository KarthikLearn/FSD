import { browser, by, element,ElementFinder, promise } from 'protractor';

export class NewsPage {
  navigateTo() {
    return browser.get('/');
  }

  getNewSearchComponent(): ElementFinder {
    return element(by.tagName('app-news'));
  }

  navigateToNewsSearch() {
    return browser.get('/dashboard/view/news');
  }
  
  getCurrentURL() {
    return browser.getCurrentUrl();
  }

  getAddFavButton(): ElementFinder {
    return this.getNewSearchComponent().element(by.buttonText('Add Favourite'));
  }

  isAddFavButtonPresent(): promise.Promise<boolean> {
    return this.getAddFavButton().isPresent();
  }

  clickAddFavButton(): promise.Promise<void> {
    return this.getAddFavButton().click();
  }

  getMoreInfoButton(): ElementFinder {
    return this.getNewSearchComponent().element(by.buttonText('Click for more info'));
  }

  isMoreInfoButtonPresent(): promise.Promise<boolean> {
    return this.getMoreInfoButton().isPresent();
  }

  clickMoreInfoButton(): promise.Promise<void> {
    return this.getMoreInfoButton().click();
  }

}

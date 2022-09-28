import { browser, by, element,ElementFinder, promise } from 'protractor';

export class RecPage {
  navigateTo() {
    return browser.get('/');
  }

  getNewSearchComponent(): ElementFinder {
    return element(by.tagName('app-recommended'));
  }

  navigateToRecPage() {
    return browser.get('/dashboard/view/recommended');
  }
  
  getCurrentURL() {
    return browser.getCurrentUrl();
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

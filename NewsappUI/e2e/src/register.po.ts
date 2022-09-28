import { browser, by, element,ElementFinder, promise } from 'protractor';

export class RegisterPage {
  navigateTo() {
    return browser.get('/');
  }

  getregisterComponent(): ElementFinder {
    return element(by.tagName('app-register'));
  }

  navigateToRegister() {
    return browser.get('/register');
  }
  
  getCurrentURL() {
    return browser.getCurrentUrl();
  }

  getUserIdInputBox(): ElementFinder {
    return element(by.className('UserId'));
  }

  getUserFirstNameInputBox(): ElementFinder {
    return element(by.className('FirstName'));
  }

  getUserLastnameInputBox(): ElementFinder {
    return element(by.className('LastName'));
  }

  getUserEmailInputBox(): ElementFinder {
    return element(by.className('Email'));
  }

  getUserPasswordInputBox(): ElementFinder {
    return element(by.className('Password'));
  }

  getRegisterButton(): ElementFinder {
    return this.getregisterComponent().element(by.buttonText('Register'));
  }
  
  isRegisterbuttonPresent(): promise.Promise<boolean> {
    return this.getRegisterButton().isPresent();
  }
  
  clickSubmitButton(): promise.Promise<void> {
    return this.getRegisterButton().click();
  }

  getCancelButton(): ElementFinder {
    return this.getregisterComponent().element(by.buttonText('Cancel'));
  }
  
  isCancelbuttonPresent(): promise.Promise<boolean> {
    return this.getCancelButton().isPresent();
  }
  
  clickCancelButton(): promise.Promise<void> {
    return this.getCancelButton().click();
  }

  navigatetoLogin()
  {
    return browser.get('/login');
  }
}

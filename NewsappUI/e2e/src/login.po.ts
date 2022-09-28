import { browser, by, element,ElementFinder, promise } from 'protractor';

export class LoginPage {
  navigateTo() {
    return browser.get('/login');
  }

  getloginComponent(): ElementFinder {
    return element(by.tagName('app-login'));
  }

  navigateToLogin() {
    return browser.get('/login');
  }
  
  getCurrentURL() {
    return browser.getCurrentUrl();
  }
  
  getUserNameInputBox(): ElementFinder {
    return element(by.className('username'));
  }
  
  isUserNameInputBoxPresent(): promise.Promise<boolean> {
    return this.getUserNameInputBox().isPresent();
  }
  
  getPasswordInputBox(): ElementFinder {
    return element(by.className('password'));
  }
  
  isPasswordInputBoxPresent(): promise.Promise<boolean> {
    return this.getPasswordInputBox().isPresent();
  }
  
  getSubmitButton(): ElementFinder {
    return this.getloginComponent().element(by.buttonText('Login'));
  }
  
  isSubmitButtonPresent(): promise.Promise<boolean> {
    return this.getSubmitButton().isPresent();
  }
  
  clickSubmitButton(): promise.Promise<void> {
    return this.getSubmitButton().click();
  }
  
  getRegisterButton(): ElementFinder {
    return this.getloginComponent().element(by.buttonText('Register'));
  }

  isRegisterButtonPresent(): promise.Promise<boolean> {
    return this.getRegisterButton().isPresent();
  }

  clickRegisterButton(): promise.Promise<void> {
    return this.getRegisterButton().click();
  }

  getLoginInputBoxesDefaultValues(): any {
    let inputUsername, inputPassword;
    inputUsername = this.getUserNameInputBox().getAttribute('value');
    inputPassword = this.getPasswordInputBox().getAttribute('value');
    return Promise.all([inputUsername, inputPassword]).then( (values) => {
      return values;
    });
  }
  
  getMockLoginDetail(): any {
    const loginDetail: any = { username: 'admin', password : 'admin'};
    return loginDetail;
  }
  
  addLoginValues(): any {
    const login: any = this.getMockLoginDetail();
    this.getUserNameInputBox().sendKeys(login.username);
    this.getPasswordInputBox().sendKeys(login.password);
    return Object.keys(login).map(key => login[key]);
  }

  navigateToDashboard() {
    return browser.get('/dashboard');
  }

  navigatetoRegister()
  {
    return browser.get('/register');
  }
}

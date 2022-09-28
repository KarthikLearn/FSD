import { LoginPage } from './login.po';

describe('Login Component', () => {
  let page: LoginPage;

  beforeEach(() => {
    page = new LoginPage();
  });

  
  it('should get username input box', () => {
    page.navigateToLogin();
    expect(page.isUserNameInputBoxPresent())
    .toBeTruthy(`<input class="username" matInput [formControl]='username'> should exist in login.component.html`);
  });

  it('should get passsword input box', () => {
    page.navigateToLogin();
    expect(page.isPasswordInputBoxPresent())
    .toBeTruthy(`<input class="password" matInput type = 'password' [formControl]='password'>
      should exist in login.component.html`);
  });

  it('should get login button', () => {
    page.navigateToLogin();
    expect(page.isSubmitButtonPresent()).toBeTruthy(`<button class="login-button">Login</button> should
      exist in login.component.html`);
  });

  it('should get register button', () => {
    page.navigateToLogin();
    expect(page.isSubmitButtonPresent()).toBeTruthy(`<button class="register-button">Register</button> should
      exist in login.component.html`);
  });

  it('default values of username and password should be empty', () => {
    const emptyLoginValues = ['', ''];
    page.navigateToLogin();
    expect(page.getLoginInputBoxesDefaultValues()).toEqual(emptyLoginValues, 'Default values for username and password should be empty');
  });

  it('should login into the NewsApp', () => {
    page.navigateToLogin();
    let loginValue = page.addLoginValues();
    expect(page.getLoginInputBoxesDefaultValues()).toEqual(loginValue, 'Should be able to set values for username and password');
    page.clickSubmitButton();
    page.navigateToDashboard();
    page.getCurrentURL().then((url) => {
      if (url.indexOf('login') > -1) {
        loginValue = page.addLoginValues();
        page.clickSubmitButton();
        page.navigateToDashboard();
        expect(page.getCurrentURL()).toContain('dashboard', 'Should navigate to Dashboard');
      } else {
        expect(page.getCurrentURL()).toContain('dashboard', 'Should navigate to Dashboard');
      }
    });

  });

  it('should Navigate to register', () => {
    page.navigateToLogin();
    page.clickRegisterButton();
    page.navigatetoRegister();
    page.getCurrentURL().then((url) => {
      expect(page.getCurrentURL()).toContain('register', 'Should navigate to Register');
    });
  });
  
});

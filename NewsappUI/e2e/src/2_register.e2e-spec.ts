import { RegisterPage } from './register.po';

describe('Register Component', () => {
  let page: RegisterPage;

  beforeEach(() => {
    page = new RegisterPage();
  });

   it('should get User Id input box', () => {
    page.navigateToRegister();
    expect(page.getUserIdInputBox)
    .toBeTruthy(`<input class="UserId" matInput > should exist in register.component.html`);
  });

  it('should get passsword input box', () => {
    page.navigateToRegister();
    expect(page.getUserPasswordInputBox)
    .toBeTruthy(`<input class="Password" matInput type = 'password'>
      should exist in register.component.html`);
  });

  it('should get First Name input box', () => {
    page.navigateToRegister();
    expect(page.getUserFirstNameInputBox)
    .toBeTruthy(`<input class="FirstName" matInput > should exist in register.component.html`);
  });

  it('should get Last Name input box', () => {
    page.navigateToRegister();
    expect(page.getUserLastnameInputBox)
    .toBeTruthy(`<input class="LastName" matInput > should exist in register.component.html`);
  });

  it('should get Email input box', () => {
    page.navigateToRegister();
    expect(page.getUserEmailInputBox)
    .toBeTruthy(`<input class="Email" matInput > should exist in register.component.html`);
  });

  it('should get register button', () => {
    page.navigateToRegister();
    expect(page.isRegisterbuttonPresent()).toBeTruthy(
      `<button class="reg-reg-button">Register</button> should exist in register.component.html`);
  });

  it('should get Cancel button', () => {
    page.navigateToRegister();
    expect(page.isCancelbuttonPresent()).toBeTruthy(
      `<button class="reg-cancel-button">Cancel</button> should exist in register.component.html`);
  });

  it('should Navigate to Login', () => {
    page.navigateToRegister();
    page.clickCancelButton();
    page.navigatetoLogin();
    page.getCurrentURL().then((url) => {
      expect(page.getCurrentURL()).toContain('login', 'Should navigate to login');
    });
  });
 
  
});

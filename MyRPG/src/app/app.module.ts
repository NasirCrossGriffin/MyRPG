import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './Login/Login.component'; // Import the component
import { SignupComponent } from './Signup/Signup.component'; // Import the component
import { routes } from './app.routes'; // Import the routes


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent, // Add the component here
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes) // Add routes here
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}

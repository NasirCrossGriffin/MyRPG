import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common'; // Import CommonModule
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './Login/Login.component'; // Import the component
import { SignupComponent } from './Signup/Signup.component'; // Import the component
import { routes } from './app.routes'; // Import the routes
import { AdventurerComponent } from './Adventurer/Adventurer.component';
import { NavBarComponent } from './Navbar/Navbar.component';


@NgModule({
  declarations: [
  ],
  imports: [
    BrowserModule,
    CommonModule,
    RouterModule.forRoot(routes) // Add routes here
  ], 
  exports: [CommonModule],
  providers: [],
  bootstrap: []
})
export class AppModule {}

import { Routes } from '@angular/router';
import { LoginComponent } from './Login/Login.component';
import { SignupComponent } from './Signup/Signup.component'; // Import the component

export const routes: Routes = [
    { path: 'login', component: LoginComponent }, // Default route
    { path: 'signup', component: SignupComponent },
    { path: '**', redirectTo: 'login' } // Wildcard route for 404
];

import { Routes } from '@angular/router';
import { LoginComponent } from './Login/Login.component';

export const routes: Routes = [
    { path: 'Login', component: LoginComponent }, // Default route
    { path: '**', redirectTo: 'Login' } // Wildcard route for 404
];

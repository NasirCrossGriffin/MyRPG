import { Routes } from '@angular/router';
import { LoginComponent } from './Login/Login.component';
import { SignupComponent } from './Signup/Signup.component'; // Import the component
import { AdventurerComponent } from './Adventurer/Adventurer.component'; // Import the component
import { ViewAdventurersComponent } from './ViewAdventurers/ViewAdventurers.component';
import { NewQuestComponent } from './NewQuest/NewQuest.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent }, // Default route
    { path: 'signup', component: SignupComponent },
    { path: 'adventurer/:id', component: AdventurerComponent },
    { path: 'adventurers', component: ViewAdventurersComponent },
    { path: 'newquest', component: NewQuestComponent },
    { path: '**', redirectTo: 'login' } // Wildcard route for 404
];

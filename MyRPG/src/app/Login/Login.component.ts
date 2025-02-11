import { Component } from '@angular/core';
import { environment } from '../../environments/environment';
import { RouterModule } from '@angular/router';


@Component ({
    selector: 'app-login',
    imports: [RouterModule],
    standalone: true,
    templateUrl: './Login.component.html',
    styleUrls: ["./Login.component.css"],
})

export class LoginComponent {
    username : string = "";
    password : string = "";

    BASE_URL : string = environment.BASE_URL;

    updateUsername(event: Event) : void {
        const input = (event.target as HTMLInputElement).value;
        this.username = input;
    }

    updatePassword(event: Event) : void {
        const input = (event.target as HTMLInputElement).value;
        this.password = input;
    }

    async authenticate() {
        console.log(this.username);
        console.log(this.password);

        const authentication = {
            username : this.username,
            password : this.password
        }

        const authenticationresponse = await fetch(`${this.BASE_URL}/api/users/authenticate` ,{ 
            headers : {'Content-Type': 'application/json'},
            method : 'POST',
            body: JSON.stringify(authentication)
        })

        
        console.log(await authenticationresponse.text())
    }
}
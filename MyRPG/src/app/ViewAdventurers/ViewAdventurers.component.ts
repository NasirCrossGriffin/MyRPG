import { Component } from '@angular/core';
import { environment } from '../../environments/environment';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import {getAllUsers, User} from "../Middleware/User"
import {getCharacterClassById} from "../Middleware/Class";
import { UserCard } from './UserCard';

@Component ({
  selector : 'app-viewadventurers',
  standalone : true,
  imports: [CommonModule, RouterModule],
  templateUrl : './ViewAdventurers.component.html',
  styleUrls : ["./ViewAdventurers.component.css"],
})

export class ViewAdventurersComponent {
  users : any = [];
  userView : UserCard[] = [];
  loaded : boolean = false
  loadingimage : string = "/jokerrunning.gif"

  async ngOnInit() {
    this.users = await getAllUsers();

    if (this.users.length === 0)
      return

    for (var user of this.users) {
      var characterClass = await getCharacterClassById(user.classId)

      var tempUserCard : UserCard = {
        id : user.id,
        name : user.username,
        level : user.level,
        profilePic : user.profilePic,
        bannerPic : user.bannerPic,
        className : characterClass.name
      }

      console.log(tempUserCard);

      this.userView.push(tempUserCard);
    }

    this.loaded = true
  }
}

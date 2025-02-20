import { Component } from '@angular/core';
import { environment } from '../../environments/environment';
import { CommonModule } from '@angular/common';
import {createCharacterClass, characterClass} from "../Middleware/Class";
import {createStat, Stat} from "../Middleware/Stat"
import {createUser, User} from "../Middleware/User"
import {createBucketObject} from "../Middleware/Bucket"



@Component ({
    selector: 'app-signup',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './Signup.component.html',
    styleUrls: ["./Signup.component.css"],
})

export class SignupComponent {
	username : string = "";
	password : string = "";
	email : string = "";
	class : string = "Warriors";
  profilepic : File | null = null;
  bannerpic : File | null = null;
  profilePicURL : string = "";
  bannerPicURL : string = "";
  stats : Stat[] = [];
  warrior : string[] = ["Strength", "Speed", "Stamina", "Skill"]
  scholar : string[] = ["Math", "Science", "History", "Language"]



	BASE_URL : string = environment.BASE_URL;

	updateUsername(e: Event) {
    console.log("Update username accessed")
		const input =  (e.target as HTMLInputElement).value;
		this.username = input;
	}

	updatePassword(e: Event) {
    console.log("Update password accessed")
		const input = (e.target as HTMLInputElement).value;
		this.password = input;
	}

	updateEmail(e: Event) {
    console.log("Update email accessed")
		const input = (e.target as HTMLInputElement).value;
		this.email = input;
	}

	updateClass(e: Event) {
    console.log("Update class accessed")
		const input = (e.target as HTMLInputElement).value;
		this.class = input;
    console.log(this.class)
	}

  updateProfilePic(e: Event) {
    console.log("Update profilepic accessed")
		const input = e.target as HTMLInputElement;
		if (input.files && input.files.length > 0) {
      this.profilepic = input.files[0]; // Assign the first selected file
      this.profilePicURL = URL.createObjectURL(this.profilepic);
      console.log("Selected file:", this.profilepic);
    } else {
        console.log("No file selected");
    }
	}

  updateBannerPic(e: Event) {
    console.log("Update bannerpic accessed")
		const input = e.target as HTMLInputElement;
		if (input.files && input.files.length > 0) {
      this.bannerpic = input.files[0]; // Assign the first selected file
      this.bannerPicURL = URL.createObjectURL(this.bannerpic);
      console.log("Selected file:", this.bannerpic);
    } else {
        console.log("No file selected");
    }
	}

	async submitHandler () {
    console.log(this.BASE_URL);
    console.log("Button press registered");

    const characterclass : characterClass = {
      name : this.class,
    }

    const newCharacterClass  = await createCharacterClass(characterclass)

    if (newCharacterClass === null) {
      console.log("Class not created");
      return;
    }

    console.log("class, " + newCharacterClass.id + " was created successfully")

    const characterClassID = parseInt(newCharacterClass.id)

    if (this.class === "Scholar") {
      for (var stat of this.scholar) {
        var currentStat : Stat = {
          name : stat,
          value : parseInt("0"),
          classId : characterClassID
        }

        this.stats.push(currentStat)
      }
    }

    if (this.class === "Warrior") {
      for (var stat of this.warrior) {
        var currentStat : Stat = {
          name : stat,
          value : parseInt("0"),
          classId : characterClassID
        }

        this.stats.push(currentStat)
      }
    }

    for (var statIter of this.stats) {
      var newStat = await createStat(statIter)

        if (newStat === null) {
          console.log("Stat not created");
          return;
        }

        console.log(newStat);
    }

    if (this.profilepic instanceof File)
      this.profilePicURL = await createBucketObject(this.profilepic);
    else
      return;

    if (this.bannerpic instanceof File)
      this.bannerPicURL = await createBucketObject(this.bannerpic);
    else
      return;

      console.log(this.profilePicURL);
      console.log(this.bannerPicURL);

    var user : User = {
      username : this.username,
      password : this.password,
      email : this.email,
      level : parseInt("0"),
      toNextLevel : parseInt("1000"),
      classId : characterClassID,
      profilePic : this.profilePicURL,
      bannerPic : this.bannerPicURL
    }

    const newUser = await createUser(user)

    if (newUser === null) {
      console.log("User not created");
      return;
    }

    console.log("user, " + newUser.username + " was created successfully"
    )
	}
}

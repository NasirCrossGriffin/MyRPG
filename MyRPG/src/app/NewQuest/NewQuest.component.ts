import {Component} from '@angular/core'
import { checkLoggedIn } from '../Middleware/User';
import { Router } from '@angular/router';
import { createQuest, getQuestById, Quest } from '../Middleware/Quest';
import { createQuestContent, QuestContent } from '../Middleware/QuestContent';
import { createBucketObject } from '../Middleware/Bucket';
import { CommonModule } from '@angular/common';

@Component ({
    selector : "app-newquest",
    imports : [CommonModule],
    templateUrl : 'NewQuest.component.html' ,
    styleUrls : ['NewQuest.component.css']
})

export class NewQuestComponent {
    user : any;
    name : string = "";
    description : string = "";
    datetime : Date = new Date();
    questContent : any = [];
    questContentEntries : any;
    constructor(private router : Router) {};

    async ngOnInit() {
        this.user = await checkLoggedIn();
        if (this.user === null) {
            this.router.navigate(['/adventurer', this.user.id]); // Navigates to /user/:id
        }
        console.log(this.user);
    }

    updateName(e: Event) {
        console.log("Update name accessed")
            const input =  (e.target as HTMLInputElement).value;
            this.name = input;
    }

    updateDescription(e: Event) {
        console.log("Update name accessed")
            const input =  (e.target as HTMLInputElement).value;
            this.description = input;
    }

    uploadQuestContent(e: Event) {
        const input = (e.target as HTMLInputElement);
        if (input.files && input.files.length > 0) {
            const content = input.files[0];
            var type = "";
            if (content === null)
                return;

            if (content.type.startsWith('image/')) {
                type = "image";
            } else if (content.type.startsWith('video/')) {
                type = "video";
            } else {
                console.log(content.type)
                return;
            }

            this.questContent.push({file : content, url : URL.createObjectURL(content), type : type })
            this.questContentEntries = Object.entries(this.questContent);
        } else {
            return;
        }
    }

    async submitHandler() {
        const protoQuest : Quest = {
            name : this.name,
            description : this.description,
            datetime : this.datetime,
            userId : this.user.id
        };

        console.log(protoQuest);

        const newQuest = await createQuest(protoQuest)
        if (newQuest) {
            for (var content of this.questContent) {
                var contentUrl = await createBucketObject(content.file);
                if (contentUrl === "")
                    return;

                var protoQuestContent : QuestContent = {
                    contentUrl : contentUrl,
                    type : content.type,
                    questId : newQuest.id
                };

                console.log(protoQuestContent);

                var newQuestContent = await createQuestContent(protoQuestContent);

                if (newQuestContent === null)
                    return
            }
        } else {
            return
        }
    }
}

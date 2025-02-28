import { environment } from '../../environments/environment';

const BASE_URL : string = environment.BASE_URL;

export type Quest = {
  name : String
  description : String;
  datetime : Date;
  userId : String;
}

export async function createQuest(protoQuest : Quest) {
  console.log(JSON.stringify(protoQuest))

  try {
    const questresponse = await fetch (`${BASE_URL}/api/quest`,{
      headers : {"content-type" : "application/json"},
      method : "POST",
      body : JSON.stringify(protoQuest)
    })

    if (questresponse.ok) {
      console.log("Quest created successfully")
      const newQuest = await questresponse.json();
      console.log(newQuest)
      return newQuest;
    } else {
      return null;
    }
  } catch (err) {
    console.log(err);
    return null;
  }
}

export async function getQuestById(questId : String) {
    console.log("The quest id specified is " + questId)

    try {
      const questresponse = await fetch (`${BASE_URL}/api/quest/${questId}`,{
        headers : {"content-type" : "application/json"},
        method : "GET",
      })

      if (questresponse.ok) {
        console.log("quest retrieved successfully")
        const retrievedquest = await questresponse.json();
        return retrievedquest;
      } else {
        return null;
      }
    } catch (err) {
      console.log(err);
      return null;
    }
  }

export async function getQuestByUserId(userId : String) {
  console.log("The user id specified is " + userId)

  try {
    const questresponse = await fetch (`${BASE_URL}/api/quest/user/${userId}`,{
      headers : {"content-type" : "application/json"},
      method : "GET",
    })

    if (questresponse.ok) {
      console.log("quest retrieved successfully")
      const listofquests = await questresponse.json();
      return listofquests;
    } else {
      return null;
    }
  } catch (err) {
    console.log(err);
    return null;
  }
}



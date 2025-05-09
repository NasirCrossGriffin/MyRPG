import { environment } from '../../environments/environment';

const BASE_URL : string = environment.BASE_URL;

export async function getFollowers(userId : number) {
    const followers = await fetch(`${BASE_URL}/api/follows/followers`, {
        method : "POST",
        headers : {"content-type" : "text/plain"},
        body : userId.toString()
    })

    if (followers.ok)
        return await followers.json()

    return null
}

export async function getFollowing(userId : number) {
    const following = await fetch(`${BASE_URL}/api/follows/following`, {
        method : "POST",
        headers : {"content-type" : "text/plain"},
        body : userId.toString()
    })

    if (following.ok)
        return await following.json()

    return null
}

export async function countFollowers(userId : number) {
    const numberOfFollowers = await fetch(`${BASE_URL}/api/follows/count`, {
        method : "POST",
        headers : {"content-type" : "application/json"},
        body : JSON.stringify(userId)
    })

    if (numberOfFollowers.ok) {
        var followerCount = await numberOfFollowers.json()
        console.log(followerCount);
        return  followerCount;
    }

    console.log("failed to retrieve number of followers")
    return null
}

export async function createFollow(followRequest : any) {
    const newBridge = await fetch(`${BASE_URL}/api/follows/new`, {
        method : "POST",
        headers : {"content-type" : "application/json"},
        body : JSON.stringify(followRequest)
    })

    if (newBridge.ok)
        return await newBridge.json()

    return null
}

export async function unfollow(followRequest : any) {
    const unfollow = await fetch(`${BASE_URL}/api/follows/unfollow`, {
        method : "POST",
        headers : {"content-type" : "application/json"},
        body : JSON.stringify(followRequest)
    })

    if (unfollow.ok)
        return await unfollow.json()

    return null
}

export async function removeFollower(followRequest : any) {
    const unfollow = await fetch(`${BASE_URL}/api/follows/remove`, {
        method : "POST",
        headers : {"content-type" : "application/json"},
        body : JSON.stringify(followRequest)
    })

    if (unfollow.ok)
        return await unfollow.json()

    return null
}

export async function getFollowBridge(followRequest : any) {
    const followBridge = await fetch(`${BASE_URL}/api/follows/get`, {
        method : "POST",
        headers : {"content-type" : "application/json"},
        body : JSON.stringify(followRequest)
    })

    if (followBridge.ok)
        return await followBridge.json()

    return null
}



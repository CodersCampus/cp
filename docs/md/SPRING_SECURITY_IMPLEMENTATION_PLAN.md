# Spring Security Add - a 'splainer

## The 3 historical blockers to security in CP

There have been 5 blockers working against CP, at least in the mind of Pete Carapetyan, author of this document:

1. "Friends don't let friends code up username password authentication." So says me, anyway.
1. CC coursework has no "generic" instruction on security except through Spring Security. So high level constructs are a bit hard to talk about.
1. CC coursework is a bit behind with a simplified approach to Spring Security
1. I made some really bad design decisions trying to implement a quick and dirty workaround, initially.
1. Integration with other CC apps is also a blocker. We really don't want to re-invent any of those wheels if we can avoid it. But we also need to be integrated so you can be recognized as a student when you are a student, etc.

The reader could quickly come to the conclusion that the problems then, were on my end, and they would be correct. But it has taken me a while to come up with the necessary fixes.

## Friends don't let friends Blocker

I will save you the 3 hour speech on the many good reasons that app market is moving away from username/password registration and towards leaving it to Google, Github, Facebook, Okta/Auth0, Azure AD or Amazon Cognito for example. But everyone has a Google or Github or Facebook account ... so just let people log in with one of those.

The main thing is that it's 2024. Using Spring Security to force your user to come up with yet one more password for yet one more site - not a good plan. Just don't.

Problem is, you still have to integrate with Spring Security on the back end Authorization side.

## Generic Security Blocker

Security is an industry thing, not just a Spring thing. Everybody uses the same generic types of security in the same general ways.

Until we can talk about authentication and authorization as separate issues, and then the basics of how role based authorization works, changing things becomes a blocker, because we lack the high level semantics to discuss the changes amongst ourselves.

This forces us to up our game, and learn it earlier in the program so we can begin the design work enough to understand the changes in CP

## CC Coursework Blocker

Our coursework is a little behind in the security area, at the Spring Security code implementation side.

We expect to catch up in 2025, but that did not help us in 2023 or 2024. So we have to fall back on such as Dan Vega from the Spring team to supplement.

## Bad Design Decisions Blocker

Yours truly made some hasty and poorly thought out design decisions to implement Google login in 2023 and 2024. The authentication side was Google based, so it works great. But the authorization side using only the uid - that has been a disaster.

This has been a high cost in terms of developer time. A huge blocker. It will now have to be reversed out, hopefully more smoothly than in it's first implementation :(

## Integration with CC apps blocker

Our current coursework app and assignment app already maintain students and roles in real time. It is critical that we never duplicate nor compete against this information.

A change there should be reflected on our side, as well, for example.

## But Wait! There is good news

Spring Security can actually be more straightforward to learn if ...

- You learn the basics of security before you learn how to code Spring Security
- You don't even try to do authentication - provide a Google or Github login
- You follow some pretty straightforward videos from Dan Vega of Spring's team
- Test it out in some hello world projects first, before bringing in to CP or your final project
- Maybe get some additional help from projects like [spring-jwt-firebase](https://github.com/iethem/spring-jwt-firebase)

## Exclusion: Authentication JWT

This document does not attempt to teach or explain how we authenticate using Google or Github login, nor how to integrate with client side or server side HTML and provide the logged in JWT for consumption by Spring Security.

Instead, there is a very small and universally usable tool published open source with intentionally sparse documentation at https://github.com/CodersCampus/AuthWCFirebase

Except swapping out your own firebase, this could be used by anyone, anywhere, from a React or Vue or Angular client side app, to a Spring Boot or Python or PHP or even NodeJS server side app.

There are too many design decisions and code implementations in this code to explain it's providence in brief terms. Rather than explaining poorly, there will be no attempt made.

Dan Vega does have a good youtube though if you want to take a more direct route within Spring Security for Google or Github logins.

## Feature Ticket for Adding Spring Security to CP

The addition of Spring Security to the CP app will not be a single simple task.

Most of this work has been done in advance in "hello world" level projects, to make sure that the concepts would even work.

At minimum, these sub-tickets will be required. They are first enumerated as a list, but then each one is discussed in more detail, below.

1. Refactor Student to User
1. Add Spring Security to project with `any().permitAll()`
1. Add Firebase dependencies and metadata
1. Domain specific - links, contollers, more
1. Rest call to CC apps for Role info, then apply internally
1. (Long overdue) refactoring of current UID mess

## Sub-Task: Refactor Student to User

When we conceptualized CP it was for students, so that is what the primary user was named.

When Trevor and Kevin looked at this, they saw an app for both students, and also other types of people that wanted to look at students.

So now our central user is no longer a `Student` but a more general `User` with many potentially different roles. This will require refactoring the app before adding Spring Security, to rename Student to User.


## Sub-Task: Add Spring Security to project

The first step in adding Spring Security will need to happen without securing anything. This just lets us:

- Add dependencies to maven
- Configure the Spring Security filter chain

Then we have to test this separately, to make sure that this alone doesn't break anything that otherwise already works.

## Sub-Task: Add Firebase dependencies and metadata

Two metadata files and maven changes are required before SpringSecurity and Firebase can work together to validate an incoming JWT which is set by a Firebase login (of Google or Github, either).

Again, we have to test this separately to make sure it doesn't break anything, before moving to the next step.

## Sub-Task: Domain specific - links, contollers

Once security seems like it is "working" then we need to apply it one thing at a time to all the relevant domains - checkins, worklog, etc.

This involves primarily work with links and controllers, but other things may be found that need to take place as part of that process.

## Sub-Task: Rest call to CC apps for Role info

Even when Firebase has validate authenitation (you have proved that you are who you say you are) we still don't know what Roles apply to you.

Are you a student? A potential interviewer? Someone scouring the web for candidates?

If you are a student, then a call into the assignment app's REST API should return JSON outlining your role as student. Other data may also be handled and persisted into the database, as a part of this process.

## Sub-Task: (Long overdue) refactoring of current UID mess

Now we will be able to refactor our current screwball setup, and get rid of all that `saveByUid()` and related gobbledygook.

It will probably be a messy ticket, but we'll just have to take it one thing at a time.







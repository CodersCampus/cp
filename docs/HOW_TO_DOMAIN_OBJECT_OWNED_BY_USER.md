# How to: Domain Object Owned By User

## What is the purpose of this document:

- To be able to move fast through tickets
- Follow the same rules for every single domain object that is owned by a user


## Primary Rules:

- All services and controllers' public methods only DTOs as args, never server-side domain objects
- DTOs in this case never include a uid, which prevents a uid from being sent back to UI
- Because of above, uids need to be sent to the backend as a separate parameter 
- Unless found otherwise DTOs should always represent parents and children as IDs and never as entire objects

## Ancillary side-effects:

- Need to write/test services before write/test controllers - meaning separate tickets
- When writing unit tests for persisted objects, it is easier to create a pool of persisted objects in the @BeforeEach of the unit test and then destroy them all in the @AfterEach

## Yet to be refined:
- Standardize these methods in a process:
    - findByUid
    - saveByUid
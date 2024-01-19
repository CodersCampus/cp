# The 5 High Level Tenets of the `coder packaging` Application

In this document we are examining the basic assumptions and ideas behind what we are doing.

The purpose of this high level work is to provide a foundation for more low level documentation, such as Figma UX/UI interpretations, and marketing materials.

## The 5 Tenets:
1. Give credit, where credit is due
1. If checklist then organize a completion
1. If prescription, then checklist
1. If emergent pattern of results, then prescription
1. If documentation, then organize for access

## 1. Give credit, where credit is due

Prospective employers discount the value of a junior coder because they assume zero experience in every critical area. Our LiveCoders have a lot of experience in a lot of areas - but this doesn't help unless we can collect information around this experience, and document it as quite real.

The first job of the coder packaging app is then to make collection of data around all relevant experience something that happens automagically, rather than something that is forced retroactively after the fact.


#### UX/UI

The UI is first organized around the checkin process, and designed to collect as much data as is reasonable about each relevant activity in real time. The assumption is that, at any later time, the aggregation and reporting of such data becomes more natural, if the data is there to be sorted and sifted through in the first place.

## 2. If checklist then organize a completion:

Do you have an a resume? Check.
Has it been reviewed by competent eyes? Check.
Has it been made customizable for specific target markets? Check.

For every aspect of coder packaging - checklists are the heartbeat of getting the job done. Providing this mechanism as simply and easily as possible is one key to this process.

#### Tabular CRUD Data

Schema: `Activity, type, role, timestamp, duration` or something like that.

It is assumed that one checkin might record 2 to 10 different activities for a specific student, depending on what the student did at any given meeting

#### Derivative Report Data

The reporting on this data would look nothing like the data itself. It would start out as summaries and totals, and later probably morph into some combination of this and charts.


#### UX/UI

Each major category (Resume, Github, LinkedIn etc) would probably need its own UI that focuses on that specific task.
There would also be at least one scoring/summarizing UI that maintains an overall view of all such major categories in one place.
If the app had the additional capability of aggregating votes and/or comments by other bootcampers that would be an extra win.

The checkin data would be designed around a checkin dashboard, and designed mostly for ease of use.

## 3. If prescription, then checklist:

"Go forth and network!" That sounds like a great general idea, but unless the respective student is exceptionally creative or talented in that area - even just knowing what steps to take would be beyond him or her.

For every such high level category (Networking, Resume, LinkedIn, Github etc) there must be a corresponding checklist to collect completion data against.


#### UX/UI

This aspect of the UI negotiates two competing goals. 
1. Usability demands single views focused on small number of identifiable tasks.
2. Understanding demands the ability to expand any one of those tasks into relevant extra information, when the summary itself might not make sense to the user.

#### Tabular CRUD Data

Fields for whichever checkboxes and links to docs are necessary.

#### Derivative Report Data

There are two types of report data in these areas.
1. Something that instructs student to complete remaining tasks
2. Few, but perhaps some rReports that are meaningful to the browsing prospective employer.


## 4. If emergent pattern of results, then prescription:

There is no single source of canonical information for achieving results. David Roberts, Trevor, all are great places to start. But as the networking example has shown, great results can happen in unexpected ways. 

Caleb devised his own quick networking formula one day, executed it that afternoon, followed a simple checklist and found high paying work immediately, with plenty more results streaming in the following weeks! The ability of the coder packaging app to add such capability, once learned about, is beneficial.


#### UX/UI

The team should have a process for adding or modifying UI and data as required, through the normal software development process. Thus, the app itself should never be thought of as "finished" but instead varying degrees of usable.

### Tabular CRUD Data

Schema here would be derived from whatever it takes to document steps required to complete a sequence of tasks.

### Derivative Report Data

As above #3 - There are two types of report data in these areas.
1. Something that instructs student to complete remaining tasks
2. Few, but perhaps some rReports that are meaningful to the browsing prospective employer.


## 5. If documentation, then organize for access, revisions, search.

The same thing that makes for great documentation often makes it suck as documentation. Easy to access can make documentation hard to revise. Easy to revise can make it less consistent and reliable. Etc.

Given the many competing concerns - too many to note here - it is proposed for initial design that markdown docs on the springwise repository be a first happy medium until a better approach comes along.


#### UX/UI

Keep it Simple S... Links on the UI go to relevant markdown docs. Breakages and lack of consistency are expected. Imperfection accepted as a necessary consequence of an evolving system.

#### Tabular CRUD Data

Probably none? Just the markdown docs, themselves?

#### Derivative Report Data

Not applicable.


# Secret Recipe 🌶

This the appilcation for "Secret Recipe".

Focus was to build a first version containing the basic funcionality of the Secret Recipe application.

[GitHub Link to the project](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe)

## Authors
We are a small team of Computer Science students from the Zurich University of Applied Sciences.

- [@unverjoh](https://github.zhaw.ch/unveryoh)
- [@jasarard](https://github.zhaw.ch/jasarard)
- [@gabricyr](https://github.zhaw.ch/gabricyr)


## Configuration
The project is build with [Gradle](https://gradle.org/) and available on GitHub through the link above.
The user interface is build in [JavaFX](https://openjfx.io/). 

To start the project you have to manually start the project by gradle run (on class [App](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/blob/main/app/src/main/java/ch/zhaw/pm2/secretrecipe/App.java)).

The GUI will the appear and you can start to enjoy our Secret Recipe application!
## Branching-Model
The branch for a non released working-on version is called ["dev"](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/tree/dev).

The philosophie in this project is to create a branch for each [issue](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/issues). 

The benefit of "one branch per issue" is that you can then link every pull request with one issue which automatically creates in the kanban board a card and moves it due to the acutal progress.
## Documentation of the refactoring process
In the project the refactroing input of the other team members where discussed in the pull requests and by person. 

### Approach

All issues are documented in the [GitHub Issue register (see "Closed" for closed issues)](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/issues) .  
The issues are split up in Functional and Structural issues.

To make it clear we defined two [issue templates](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/tree/main/.github/ISSUE_TEMPLATE).

As an example here are two pull requests: 


[Pull Request 1:](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/pull/10)

__Process:__    

    1. first version from a developer 
    2. comments for refactoring of the other team members -> developer implements the inputs
    3. refactored code which will be merged into the dev branch

---

[Pull Request 2:](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/pull/16)

__Process:__   

    1. first version from a developer 
    2. comments for refactoring of the other team members -> developer implements the inputs
    3. refactored code which will be merged into the dev branch

### Structure

__Please check out the [class diagram](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/tree/main/diagram) of the project!__

#### General Architecture

The programm is split in [model](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/tree/main/app/src/main/java/ch/zhaw/pm2/secretrecipe/model), 
[ui](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/tree/main/app/src/main/java/ch/zhaw/pm2/secretrecipe/ui) and 
[views](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/tree/main/app/src/main/resources/views) in the resources folder.

This are the fundamentals for the [MVC pattern](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller).

#### MVC:

In the link above you see a wikipedia entry for the MVC pattern. We structured the classes in regard of the MVC pattern. 
This means that the user uses the controllers they then manipulate the models and the view gets updated by the model and the user only sees the view.

#### Database & DataManager:
Class [database](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/blob/main/app/src/main/java/ch/zhaw/pm2/secretrecipe/model/Database.java) 
and [dataManager](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/blob/main/app/src/main/java/ch/zhaw/pm2/secretrecipe/model/DataManager.java) 
are the input and output of the saved recipes and users. It is the single source of truth of existing recipes and users.

#### Why this structure?

Which the choosed structure we eliminated codeduplication and we have a central and single source of truth.
It is also structured to adapt the future implementations as easy as possible. 

#### Future Implementations:

Implementation which extends the basic finctionality of the project.

##### Implementation 1:
In this [issue](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/issues/34) 
we described the problem that you can not comment recipes.

---

##### Implementation 2:

In this [issue](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/issues/35)
we descirbed that you can not like other recipes.

---

##### Implementation 3:

In this [issue](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/issues/36)
we described that you can not see the comments from other users.

---

##### Implementation 4:

In this [issue](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/issues/37)
we described that you can not set lables to a recipe like "allergic", "vegan" or something similar like this.

---

##### Implementation 5:

In this [issue](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/issues/38)
we described that you can not sort the recipes by their lables. 

---

##### Implementation 6:

In this [issue](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/issues/39)
we described that you can not follow a other user to get updates when he shares a recipe with you.

---

##### Implementation 7:

In this [issue](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/issues/27)
we described that you can not add a picture of the recipe.

---


## Bugs
All known and not implemented bugs are listed as [open issues](https://github.zhaw.ch/PM2-IT21aWIN-fame-rayi-wahl/gruppe06-gamma-projekt2-secret-recipe/issues). 

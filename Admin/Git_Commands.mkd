# Git Commands
git init - Initializes a new git repository in the current directory
git clone \[url] - Downloads a project and its entire version history from a remote url
git config --global user.name "\[name]" - Sets the name you want attached to your commit transactions
git config --global user.email "\[email]" - Sets the email you want attached to your commit transactions
### Workflow
git status - List all new or modified files to be committed
git add \[file] - Snapshots the file in preparation for versioning
git add . - Stages all changes in the current directory and subdirectories
git commit -m "\[message]" - Records file snapshots permanently in the version history
git log - Lists the version history for the current branch
git diff - shows file differences that are not yet staged

### Branching and merging
git branch - Lists all local branches in the current repo
git branch \[branch-name] - Creates a new branch
git switch \[branch-name] - Creates a new branch and switches to it immediately
git merge \[branch-name] - Combines the specified branch's history into the current branch
git branch -d \[branch-name] - Deletes the specified branch

### Remote Synchronization
git remote -v - Shows the urls of the remote repos linked to my local project
git fetch - downloads all history from the remote repo without merging changes
git pull - fetches and merges any commits from the remote branch into my current local branch
git push - uploads all local branch commits to the remote repo

### Inspection and troubleshooting
git stash - Temporarily stores all modified tracked files
git stash pop - Resores the most recently stashed files and removes them from the stash list
git stash list - List all stashed changesets

Undoing changes
git reset \[file] - unstages the file, but preserves its contents
git reset --hard \[commit] - discards all history and changes back to the specified commit (cannot be undone)
git revert \[commit] - creates a new commit that undoes the changes of a previous commit, preserving history

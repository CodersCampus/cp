# Check if the script has been given an argument
if [ $# -eq 0 ]; then
    echo "Usage: $0 <comment>"
    exit 1
fi

# Set the internal variable 'comment' to the provided argument
comment="$1"
# Function to check if the current branch is "dev"
is_dev_branch() {
    current_branch=$(git symbolic-ref --short HEAD 2>/dev/null)
    [ "$current_branch" = "dev" ]
}

# Check the length of 'comment' and echo accordingly
if [ ${#comment} -gt 2 ]; then
    echo "Pushing commit with comment: $comment"
    git add .
    git commit -m "$comment"
    if is_dev_branch; then
        echo "The 'dev' branch is currently checked out. Aborting git push."
        exit 1
    else
        echo "The current branch is not 'dev'. Proceeding with git push."
        git push
    fi
else
    echo "Sorry dude"
fi
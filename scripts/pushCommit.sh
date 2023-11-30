# Check if the script has been given an argument
if [ $# -eq 0 ]; then
    echo "Usage: $0 <comment>"
    exit 1
fi

# Set the internal variable 'comment' to the provided argument
comment="$1"

# Check the length of 'comment' and echo accordingly
if [ ${#comment} -gt 2 ]; then
    echo "Pushing commit with comment: $comment"
    git add .
    git commit -m "$comment"
    git push
else
    echo "Sorry dude"
fi
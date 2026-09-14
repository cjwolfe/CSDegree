# Directory for storing bookmarks
export MARKPATH=$HOME/.marks

# Create marks directory if it doesn't exist
[[ -d $MARKPATH ]] || mkdir -p $MARKPATH

# Mark current directory
function mark {
    local mark_name="$1"
    if [[ -z "$mark_name" ]]; then
        echo "Usage: mark <name>"
        return 1
    fi
    ln -sf "$(pwd)" "$MARKPATH/$mark_name"
    echo "Marked $(pwd) as $mark_name"
}

# Jump to mark
function jump {
    local mark_name="$1"
    if [[ -z "$mark_name" ]]; then
        echo "Available marks:"
        ls -1 "$MARKPATH"
        return 1
    fi
    if [[ ! -L "$MARKPATH/$mark_name" ]]; then
        echo "Mark not found: $mark_name"
        return 1
    fi
    cd -P "$MARKPATH/$mark_name" 2>/dev/null || {
        echo "Mark exists but directory not found. Removing stale mark."
        rm "$MARKPATH/$mark_name"
        return 1
    }
}

# Delete mark
function unmark {
    local mark_name="$1"
    if [[ -z "$mark_name" ]]; then
        echo "Usage: unmark <name>"
        return 1
    fi
    rm -i "$MARKPATH/$mark_name"
}

# List all marks
function marks {
    ls -l "$MARKPATH" | tail -n +2 | awk '{print $9" -> "$11}'
}

# Tab completion for jump and unmark
function _completemarks {
    reply=($(ls $MARKPATH))
}
compctl -K _completemarks jump
compctl -K _completemarks unmark

# Pippy User Guide

Pippy is a **command-line task manager** that helps you track todos, deadlines, and events. It's fast, no-nonsense, and saves your tasks automatically between sessions.

```
 ____  _
|  _ \(_)_ __  _ __  _   _
| |_) | | '_ \| '_ \| | | |
|  __/| | |_) | |_) | |_| |
|_|   |_| .__/| .__/ \__, |
        |_|   |_|    |___/
 Hello! I'm Pippy
 What can I do for you?
```

---

## Quick Start

1. Ensure you have **Java 17** installed.
2. Download `pippy.jar` from the [latest release](https://github.com/).
3. Open a terminal, navigate to the folder containing the JAR, and run:
   ```
   java -jar pippy.jar
   ```
4. Type a command and press Enter. Type `bye` to exit.

---

## Features

### Add a Todo — `todo`

Adds a task with no deadline.

**Format:** `todo DESCRIPTION`

**Example:**
```
todo borrow book
```
**Output:**
```
Added Todo:
   [T][ ] borrow book
You now have 1 tasks in your list, unproductive waste of space
```

---

### Add a Deadline — `deadline`

Adds a task that must be completed by a specific date/time.

**Format:** `deadline DESCRIPTION /by DATETIME`

**Example:**
```
deadline return book /by Sunday
```
**Output:**
```
Added Deadline:
   [D][ ] return book (by: Sunday)
You now have 2 tasks in your list, unproductive waste of space
```

---

### Add an Event — `event`

Adds a task with a start and end time.

**Format:** `event DESCRIPTION /from DATETIME /to DATETIME`

**Example:**
```
event project meeting /from Mon 2pm /to 4pm
```
**Output:**
```
Added Event:
   [E][ ] project meeting (from: Mon 2pm to: 4pm)
You now have 3 tasks in your list, unproductive waste of space
```

---

### List all tasks — `list`

Shows all tasks currently in the list.

**Format:** `list`

**Example output:**
```
1.[T][ ] borrow book
2.[D][ ] return book (by: Sunday)
3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
```

Task icons:
- `[T]` — Todo
- `[D]` — Deadline
- `[E]` — Event
- `[X]` — Done, `[ ]` — Not done

---

### Mark a task as done — `mark`

Marks the task at the given number as completed.

**Format:** `mark INDEX`

**Example:**
```
mark 2
```
**Output:**
```
Marked as Done: [D][X] return book (by: Sunday)
```

---

### Unmark a task — `unmark`

Marks the task at the given number as not done.

**Format:** `unmark INDEX`

**Example:**
```
unmark 2
```
**Output:**
```
Marked as Undone: [D][ ] return book (by: Sunday)
```

---

### Delete a task — `delete`

Permanently removes the task at the given number.

**Format:** `delete INDEX`

**Example:**
```
delete 1
```
**Output:**
```
Noted. I've removed this task, slacker:
   [T][ ] borrow book
Now you have 2 tasks in the list.
```

---

### Find tasks by keyword — `find`

Searches all task descriptions for the given keyword (case-insensitive).

**Format:** `find KEYWORD`

**Example:**
```
find book
```
**Output:**
```
Here are the matching tasks in your list:
1.[D][ ] return book (by: Sunday)
```

---

### Exit — `bye`

Exits the application. Tasks are saved automatically.

**Format:** `bye`

---

## Data Storage

Tasks are saved automatically to `./data/pippy.txt` after every change. The file is created on first run. Do not manually edit this file unless you know the storage format.

---

## Command Summary

| Command | Format |
|---|---|
| Add Todo | `todo DESCRIPTION` |
| Add Deadline | `deadline DESCRIPTION /by DATETIME` |
| Add Event | `event DESCRIPTION /from DATETIME /to DATETIME` |
| List | `list` |
| Mark done | `mark INDEX` |
| Unmark | `unmark INDEX` |
| Delete | `delete INDEX` |
| Find | `find KEYWORD` |
| Exit | `bye` |
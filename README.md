# CSC120-FinalProject

## Architecture Diagram & Route Map

The architecture diagram and a map of all routes in the game can be found [here](https://viewer.diagrams.net/?tags=%7B%7D&lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&title=CSC120%20Final%20Project%20Architecture.drawio&dark=auto#Uhttps%3A%2F%2Fdrive.google.com%2Fuc%3Fid%3D1EUzs4jco-MmQYJOuMJLUEatxQLkz1VDW%26export%3Ddownload). The link contains:
1. The architecture diagram
2. A route map for Chapters I and II
3. A route map of only Chapter I: The Hero and the Princess
4. A simplified full-game route map

## Design Justification

The back-end design of the game centers around four main classes: **GameManager**, **Cycle**, **IOHandler**, and **Script**.

- **GameManager** manages the overarching playthrough from start to finish, keeping track of global variables that persist between gameplay loops and the player's overall progress through the game. It runs the game's intro and ending sequences and handles the progression between gameplay loops, or **Cycles**.
- **Cycle** (and mainly its subclass, **StandardCycle**) runs a single gameplay loop from start to finish -- from the beginning of Chapter I to either aborting a vessel or claiming a vessel and speaking with the Shifting Mound. It keeps track of any variables that are used within the cycle, then updates the **GameManager's** global variables as necessary, discarding everything else.
    - **StandardCycle** represents a standard gameplay loop, but in the full game, **Cycle** has another subclass: **Finale**, which runs the game's finale.
- **IOHandler** handles text input and output. It prints out **DialogueLines** and **OptionsMenus** and handles and parses multiple types of player input (progressing dialogue, commands, options, or yes/no answers). It also ensures that printed text is properly wrapped so that words are not split between multiple lines of text.
- **Scripts** store the text from a given script file (a .txt file) and are able to parse and execute the script. They are able to "jump around" within the file and run different sections on command, and can print dialogue or change the currently playing song.

Less prominent classes include:
- **IndexedLinkedHashMap** is a subclass of [LinkedHashMap](https://docs.oracle.com/en/java/javase/13/docs/api/java.base/java/util/LinkedHashMap.html), and essentially combines a LinkedHashMap with an ArrayList, allowing values to be accessed with either an integer index or a key.
- **DialogueLines** represent a line of dialogue, and the class contains the code for slowly printing out dialogue.
    - **VoiceDialogueLines** represent a line of dialogue from a given Voice. They automatically include the given Voice's dialogue tag at the beginning of the line when printing.
    - **PrincessDialogueLines** represent a line of dialogue from the Princess. They are formatted slightly differently than regular DialogueLines when printing.
- **AbstractConditions** represent a condition that automatically updates across multiple classes or methods, allowing Options to dynamically check whether or not the condition is met.
    - **Conditions** are the most basic type of condition, representing a simple boolean value.
    - **InverseConditions** contain a link to another AbstractCondition and automatically return the opposite of that condition's value.
    - **OrConditions** contain an array of other AbstractConditions and act like an "or" operator, returning true if *any* of their conditions are met.
- **Options** represent options that the player can choose from, with a variety of attributes that dictate whether the option is currently visible and/or available.
- **OptionsMenu** stores an **IndexedLinkedHashMap** of the **Options** that are shown in a single menu, dynamically updating the menu as it is shown to the player and interpreting the player's chosen option.

The game also makes frequent use of enums:
- **GameLocations** are the locations the player can be in. They are mainly used to determine whether movement commands are currently available and where they take the player.
- **Commands** are the valid commands that the player can enter. They are mainly used to efficiently parse player input and redirect IOHandler to the appropriate methods, as well as being crucial for the functionality of the `HELP` command.
- **Voices** are the Voices that appear in the player's head as they progress through different chapters. They always begin Chapter I with the Narrator and the Voice of the Hero. They are mainly used to automatically format dialogue lines.
- **Chapters** are the different chapters that exist in the game. They are mainly used to prevent the player from seeing the same chapter twice in one playthrough, as well as to redirect StandardCycle to the appropriate method for initiating the chapter.
- **ChapterEndings** are the different endings the player can reach in any given chapter. They are mainly used to determine what to do after finishing a **Chapter** -- whether to run a given new **Chapter** and add a new **Voice** or claim a given **Vessel** and end the current **StandardCycle**.
- **Vessels** are the variations of the Princess that the player can "claim" by reaching the end of a route, tied to a specific **Chapter**. They are mainly used to determine the Shifting Mound's comment on the player's most recent route.

About halfway through working on implementing all Chapter IIs, I came up with the idea of Conditions (which later expanded to AbstractConditions in general) -- a class that basically just stores a boolean that can be automatically evaluated from a different class/method, allowing me to create Options that automatically become available or unavailable based on a conditions I can set with one line of code.

Previously, I had been manually setting Options' availability with regular booleans, meaning that if one action caused multiple Options to become available or unavailable, I had to manually write one line of code for every Option it affected. It definitely *worked,* but it was irritating, inefficient. and a lot to keep track of. I only started using Conditions halfway through implementing The Witch, and I haven't had the time to go back and implement Conditions into the chapters I'd already written, so Chapter I, The Stranger, The Razor (and its Chapter IIIs and IVs), The Tower, The Damsel, and The Beast all have artifacts of that annoying process.

## Reflection

My overall approach to this project was to start with the framework before even starting on anything the player would see. First, I decided what classes and enums I would need and made a list of all the commands I wanted the player to be able to enter. I implemented the enums (GameLocation, Voice, Chapter, ChapterEnding, and Vessel) first, since they were extremely simple and most of the work was just compiling lists of all their values. Once those were done, I implemented as much back-end functionality into the main classes (GameManager, Cycle, IOHandler, DialogueLine and its subclasses, Option, and OptionsMenu) as I could think of; I also added the Command enum while working on IOHandler. Once I couldn't think of anything else I'd need, I decided it was finally time to finally on the core content of the game, going back and adding edge cases or other nuances into the back-end as needed.

The two most useful things I learned while working on this project were absolutely how to work with enums and how to read from files. Using enums for things like the Voices and especially chapter endings simplified things a *lot,* and made it *much* easier to keep track of which ending leads to what chapter with which Voice(s), etc....

Learning how to read from files was a complete game-changer, allowing me to *massively* cut down on the size of StandardCycle by moving almost all dialogue into separate script files instead. When I first created the Script class, I'd already implemented all of Chapter I, the shared intro of Chapter II, Chapter II: The Stranger, and Chapter II: The Razor (and its Chapter IIIs and IVs). After reworking the code to run on Scripts instead of printing dialogue directly from StandardCycle, StandardCycle went from ~12,400 lines of code to only ~8,800! Even now, with all Chapter IIs implemented, StandardCycle sits at ~14,100 lines, and I'm sure the scripts themselves are over 10,000 lines long in total.

Switching to Scripts was definitely the most impactful change I made while working on this project. I was fully prepared to simply run all dialogue directly from StandardCycle, but this is *much* more efficient and significantly streamlines the implementation of new chapters/routes. I also had fun deciding how to format my script files, and ironically ended up with something that looks quite close to the way the code is laid out in the original game.

Switch cases were also a much smaller discovery I made early on in the project (mostly just because I was sick of VSCode yelling at me to "replace chain of ifs with switch"), but they ended up being absolutely invaluable for running options menus.

It's not something I wish I'd *implemented* differently, but I *really* wish I'd given myself more time to playtest the game. As it is, most Chapter IIs have literally never been playtested, and I'm just hoping I didn't miss anything while programming them...

I'm actually planning to continue working on this project after the semester ends, adding in all the content from the original game and tweaking the framework a bit more, so I even have a to-do list for myself now that I *do* have more or less unlimited time to work on it. Of course, I'm excited to implement the rest of the game, including all Chapter IIIs and the finale. I also definitely want to go back and update of the earlier Chapters I implemented to use Conditions where appropriate.

I'm also very interested in figuring out how to dynamically write to a specific line in a file, without erasing the entire thing every time, because I'd love to implement an achievements system that persists between playthroughs of the game. Hypothetically, if I could figure that out, I could also figure out how to make a full-on save/load system, but I'd definitely wait a while, because that would be a *huge* undertaking...

If I could go back to the beginning of this project and give myself advice, I would first tell myself not to get all excited about making the *entirety of Slay the Princess* playable by the end of the semester -- that was, to put it bluntly, an *insane* goal, and I think implementing everything up to the end of all Chapter IIs and claiming your first vessel was a very good compromise. Honestly, I would even encourage my past self to stop at the end of the original Slay the Princess demo, which ends right after first meeting the Chapter II Princess.

I would also tell my past self to just use Conditions from the get-go. One of the first questions I asked myself while working on the back-end was whether I could use Booleans to automatically update a boolean in another class, and I was very disappointed when that didn't work. It didn't occur to me to make a class for that purpose until earlier this week, but if I'd thought of it earlier, I probably would've saved a bunch of time and frustration implementing Chapter I and the first few Chapter IIs!

Overall, I had a lot of fun working on this project, and I'm excited to continue improving it! :)
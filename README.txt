project folder:
Veronika131-cs1c-project05/

**NOTE:  - Part 1 and Part 2 are implemented in this assignment.
         - Part 1 output is in RUN.txt
         - Part 2 output is in RUN2.txt

Brief description of submitted files:


docs/
    -   JavaDocs folder based on source code

lib/
    -   library folder with json-simple-1.1.1.jar file that's used for parsing JSON file.



src/cs1c/MillionSongDataSubset.java
    -   Parses a JSON data set and stores each entry in an array.

src/cs1c/SongEntry.java
    -   Stores a simplified version of the genre data set from the Million Song Dataset.

src/cs1c/TimeConverter.java
    -   Converts duration into a string representation.

src/cs1c/Traverser.java
    -   Traverser interface used for traversing binary search tree.



src/queues/FoothillTunesStore.java
    -   Simulates the music store scenario of buying and adding tunes to shopping playlist.

src/queues/Item.java
    -   One object of Item class represents one item in the inventory, with two class members.

src/queues/LazySearchTree.java
    -   Lazy Search Tree uses lazy deletion to remove nodes, LazySearchTree object represents
        inventory and keeps track of added/removed items.

src/queues/PrintObject.java
    -   The functor class PrintObject traverses and prints out data from our tree.

src/queues/SuperMarket.java
    -   Simulates the market scenario of buying and adding items to a store's inventory.


resources/inventory_empty.txt
    -   Testing the boundary condition when removing item from empty inventory.

resources/inventory_invalid_removal.txt
    -   Testing the boundary condition when removing an item that may not exist.

resources/inventory_log.txt
    -   Testing the LazySearchTree by adding and removing items from the inventory.

resources/inventory_no_such_item_in_inventory.txt
    -   Testing removing item that was never in inventory.

resources/inventory_short.txt
    -   Testing for removal of root node from LazySearchTree.

resources/music_genre_subset.json
    -   Input data file that holds over 59600 songs used by FoothillTunesStore.java.

resources/RUN.txt
    -   Various test outputs of SuperMarket class that display different states of inventory
        while adding and removing items to and from inventory.

resources/RUN2.txt
    -   Various test outputs of FoothillTunesStore class that display different states of song list
        while adding and removing song items to and from song list.

resources/tunes.txt
    -   Testing the LazySearchTree by adding and removing song items from tunes.

resources/tunes_test3.txt
    -   Testing for removal of root node from LazySearchTree

resources/tunes_test4.txt
    -   Testing file that has no songs in any of the playlists.

resources/tunes_truncated.txt
    -   Testing the boundary condition where requested number of songs is bigger than
        total number of songs in file.



.gitignore
    -   .gitignore content tells git to ignore specified things.

README.txt
    -   Description of submitted files.
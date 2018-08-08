package lazyTrees;
import cs1c.MillionSongDataSubset;
import cs1c.SongEntry;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * Simulates the music store scenario of buying and adding tunes to shopping playlist.
 * Implements BST with lazy deletion to keep track of total inventory (deleted + non deleted)
 * and current inventory (non deleted only).
 *
 * @author Foothill College, Veronika Cabalova Joseph
 */
public class FoothillTunesStore
{
    public static final boolean SHOW_DETAILS = true;

    // TODO: Define the functor class PrintObject to traverse and print out data
    //       from LazySearchTree.
    PrintObject<SongEntry> printObject = new PrintObject<SongEntry>();

    // The data structure, which we use to add and remove items.
    private LazySearchTree<SongEntry> tunes;

    /**
     * Instantiates tunes to be a LazySearchTree of SongEntry objects.
     */
    public FoothillTunesStore()
    {
        tunes = new LazySearchTree<SongEntry>();
    }

    /**
     * Add a new song item with the name as in parameter into tunes.
     * @param songItem		The item to be added to the tunes tree.
     */
    public void addToInventory(SongEntry songItem)
    {
        // Check if the songItem is in the tunes tree.
        boolean isFound = tunes.contains(songItem);

        // If the songItem is not found, add the temporary object as another node (category) to the tree.
        if (!isFound)
        {
            // TODO: Modify insert method to work with lazy deletion such that it updates
            // both hard and soft sizes.
            tunes.insert(songItem);

              // NOTE: Need to check if the songItem was lazily deleted, then we need to increment the count
            SongEntry found = tunes.find(songItem);
            if (found.getCount() == 0)
            {
                found.incrementCount();
            }
            else
                //avoid increasing count of song beyond one,  won't add duplicate song
                return;
        }
    }

    /**
     * This method removes song from tunes by decreasing the count from one to zero.
     * @param songItem		The item to be removed to the tunes tree.
     */
    public void removeFromInventory(SongEntry songItem)
    {
        boolean isFound = tunes.contains(songItem);

        // check if the songItem exists in the tunes disregarding lazy deletion
        if (!isFound)
        {
            throw new NoSuchElementException();
        }

        SongEntry found = tunes.find(songItem);

        // if the songItem has zero left in stock,
        // then treat it as if it does not exist in the tree.
        if (found.getCount() == 0)
        {
            throw new NoSuchElementException();
        }
        // if the songItem has one in stock,
        // then decrement its count to zero and lazy delete it in the tree.
        else
        {
            found.decrementCount();

            // TODO: Modify to be a lazy deletion remove method, which marks
            //       found nodes as "deleted".
            tunes.remove(songItem);
        }
    }


    /**
     * Display the first songItem and last time of the soft tree in lexical order.
     */
    public void showFirstAndLastItem(String message)
    {
        System.out.println("\n" + message);

        // TODO: Modify the protected methods findMin() and findMax() to implement lazy deletion.
        //       Searches from the root of the tree and return sthe minimum and maximum node that
        //       has NOT been "deleted".
        try
        {
            SongEntry min = tunes.findMin();
            System.out.println ( "First item: " + min.toString());
        }
        catch (Exception NoSuchElementException)
        {
            System.out.println("Warning: minimum element not found!");
        }

        try
        {
            SongEntry max = tunes.findMax();
            System.out.println ( "Last item: " + max.toString());
        }
        catch (Exception NoSuchElementException)
        {
            System.out.println("Warning: minimum element not found!");
        }

    }

    /**
     * Shows the state of the tree by displaying:
     * - the *hard* tunes, which includes deleted nodes.
     * - the *soft* tunes, which excludes deleted nodes.
     * @param message	Additional details about the state.
     * @param showTree	Set to true if we want to display the contents of the tree
     */
    protected void displayInventoryState(String message, boolean showTree)
    {
        System.out.println("\n" + message);
        System.out.println("\"hard\" number of unique items (i.e. mSizeHard) = " + tunes.sizeHard());
        System.out.println("\"soft\" number of unique items (i.e. mSize) = " + tunes.size());

        if (!showTree)
            return;

        System.out.println( "\nTesting traversing \"hard\" tunes:");

        // TODO: First, rename the public/private pair traverse() method of LazySearchTree to traverseHard() method.
        //       Then, reuse this public/private pair of methods to traverses the tree
        //       and displays all the nodes.
        // NOTE: Here, we call the public version.
        tunes.traverseHard(printObject);


        System.out.println( "\n\nTesting traversing \"soft\" tunes:");

        // TODO: Define a public/private pair of methods that traverses the tree
        //       and displays only nodes that have not been lazily deleted.
        // NOTE: Here, we call the public version.
        tunes.traverseSoft(printObject);
        System.out.println("\n");
    }

    public static void main(String[] args)
    {
        // Directory path for JSON file of songs
        final String FILEPATH = "resources/music_genre_subset.json";

        // TODO: Tests the LazySearchTree by adding and removing items from the tunes
        final String TESTFILE = "resources/tunes.txt";	// Directory path for plain-text file

        // NOTE: Testing buying song item before adding any songs.
        //final String TESTFILE = "resources/tunes_removal_before_adding.txt";

        // NOTE: Testing for removal of root node from LazySearchTree
        //final String TESTFILE = "resources/tunes_test3.txt";

        // NOTE: Testing adding songs and then buying more songs than added
        //final String TESTFILE = "resources/tunes_test4.txt";


        // parses the JSON input file
        MillionSongDataSubset msd = new MillionSongDataSubset(FILEPATH);

        // retrieves the parsed objects
        SongEntry [] allSongs = msd.getArrayOfSongs();


        System.out.printf("Test file: %s \n", TESTFILE);

        FoothillTunesStore musicStore = new FoothillTunesStore();

        File infile = new File(TESTFILE);

        try
        {
            Scanner input = new Scanner(infile);

            String line = "";
            int lineNum = 0;
            while (input.hasNextLine())
            {
                lineNum++;
                line = input.nextLine();
                String [] tokens = line.split(",");

                String selection = tokens[0];
                String songName = tokens[1];

                String message = "at line #" + lineNum + ": " + line;

                // this loop will search for a song that matches
                // title stored in itemName and saves SongEntry
                // object in foundSong once song is found
                SongEntry foundSong = null;
                // loops through list of all songs
                for (int i = 0; i < allSongs.length; i++)
                {
                    //find song title in allSongs that matches itemName from testing file
                    if (allSongs[i].getTitle().equals(songName))
                    {
                        foundSong = allSongs[i];
                        break;
                    }
                }

                // When an item is added:
                // If the item is not in our tunes,
                // create a new entry in our tunes.
                if (selection.equals("add"))
                {
                    musicStore.addToInventory(foundSong);

                    // NOTE: Currently displaying the contents is disabled to reduce cluttering the output.
                    // Suggestion: To start, enable displaying the contents of the tree to help you debug.
                    if (SHOW_DETAILS)
                        musicStore.displayInventoryState("\nUpdate " + message, true);
                }

                // When an item is bought:
                // Decrement the count of the item to zero.
                // If the item is out of stock,
                // remove the item from tunes.
                //
                // Note: buying an out of stock item, is invalid. Handle it appropriately.
                else if (selection.equals("buy"))
                {
                    try
                    {
                        musicStore.removeFromInventory(foundSong);

                        // NOTE: Currently displaying the contents is disabled to reduce cluttering the output.
                        // Suggestion: To start, enable displaying the contents of the tree to help you debug.
                        if (SHOW_DETAILS)
                            musicStore.displayInventoryState("\nUpdate " + message, true);
                    }
                    catch (java.util.NoSuchElementException ex)
                    {
                        // NOTE: Ideally we'd print to the error stream,
                        //       but to allow correct interleaving of the output
                        //       we'll use the regular output stream.
                        System.out.printf("\nWarning: Unable to fulfill request: %s \n", message);
                        System.out.printf("Warning: Item %s is out of stock.\n", songName);
                    }
                }
                else
                {
                    System.out.println("Warning: Inventory selection not recognized!");
                }

                // Display the first item and the last item before checking
                // if it's time to clean up our tunes.
                if (SHOW_DETAILS)
                    musicStore.showFirstAndLastItem(message);
            }
            input.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        // Display the tunes
        System.out.println("\n");
        musicStore.displayInventoryState("\nFinal state of tunes:", true);

        // flush the error stream
        System.err.flush();

        System.out.println("\nDone with FoothillTunesStore.");
    }
}

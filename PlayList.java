/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
public class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */ 
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */ 
    public int getMaxSize() {
        return maxSize;
    }
    
    /** Returns the current number of tracks in this play list. */ 
    public int getSize() {
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }
    
    /** Appends the given track to the end of this list. 
     *  If the listgt is full, does nothing and returns false.
     *  Otherwise, appends the track and returns true. */
    public boolean add(Track track) {
        if(size==maxSize) return false;
        tracks[size] = track;

        size++;
        return true;
        
    }

    /** Returns the data of this list, as a string. Each track appears in a separate line. */
    //// For an efficient implementation, use StringBuilder.
    public String toString() {

        String str = "";
        for (Track track : tracks) {
            if(track!=null){
            str+= track.getArtist()+",";
            str+= " " + track.getTitle()+",";
            str+= " " + track.getDuration();
            str+= "/n";
        }
        }

        return str;
    }

    /** Removes the last track from this list. If the list is empty, does nothing. */
     public void removeLast() {

        if(tracks.length>0){
            tracks[size] = new Track(null, null, 0);
            size--;
        }
    }
    
    /** Returns the total duration (in seconds) of all the tracks in this list.*/
    public int totalDuration() {
        int totalDuration = 0;
        for (Track track : tracks) {
            if(track!=null){
            totalDuration += track.getDuration();
        }
        }
        //// replace the following statement with your code
        return totalDuration;
    }

    /** Returns the index of the track with the given title in this list.
     *  If such a track is not found, returns -1. */
    public int indexOf(String title) {

        title = title.toLowerCase();
        for(int i=0;i<size;i++){
            String tarkTitle = tracks[i].getTitle().toLowerCase();
            if(title.equals(tarkTitle)){
                return i;
            }
        }
        return -1;
    }

    /** Inserts the given track in index i of this list. For example, if the list is
     *  (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     *  If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     *  If i is negative or greater than the size of this list, or if the list
     *  is full, does nothing and returns false. Otherwise, inserts the track and
     *  returns true. */
    public boolean add(int i, Track track) {
        if(i<0||i>size||size==maxSize) return false;
        if(i==size){
            tracks[i] = track;
            return true;
        }
        for(int j=size-1;j>=i;j--){
            tracks[j+1] = tracks[j];
        }
        tracks[i] = track;

        size++;
        return true;
    }
     
    /** Removes the track in the given index from this list.
     *  If the list is empty, or the given index is negative or too big for this list, 
     *  does nothing and returns -1. */
    public void remove(int i) {

        if(size!=0&&i>0&&i<size){
            for(int j=i+1;j<size;j++){
                tracks[j-1] = tracks[j];
            }
        
        }
        tracks[size-1] = new Track(null, null, 0);
        size--;
    }

    /** Removes the first track that has the given title from this list.
     *  If such a track is not found, or the list is empty, or the given index
     *  is negative or too big for this list, does nothing. */
    public void remove(String title) {

        int index = indexOf(title);
        remove(index);
    }

    /** Removes the first track from this list. If the list is empty, does nothing. */
    public void removeFirst() {

        remove(0);
    }
    
    /** Adds all the tracks in the other list to the end of this list. 
     *  If the total size of both lists is too large, does nothing. */
    //// An elegant and terribly inefficient implementation.
     public void add(PlayList other) {

        if(other.getSize()+this.size<=this.maxSize){
            for(int i=0;i<other.getSize();i++){
                this.add(other.getTrack(i));
            }
        }
    }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) {

        if(start<0||start>=size) return -1;

        int minDuration = tracks[start].getDuration();
        int index = start;
        for(int i=start;i<size;i++){
            int duration = tracks[i].getDuration();
            if(duration<minDuration){
                minDuration = duration;
                index = i;
            }
        }
        return index;
    }

    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() {

        return tracks[minIndex(0)].getTitle();
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() {
        // Uses the selection sort algorithm,  
        // calling the minIndex method in each iteration.
        for(int i=0;i<size;i++){
            int minDurationIndex = minIndex(i);
            Track temp = tracks[i];
            tracks[i] = tracks[minDurationIndex];
            tracks[minDurationIndex] = temp;
        }



    }
}

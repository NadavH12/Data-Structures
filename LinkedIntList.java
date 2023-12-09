import java.util.*;
public class LinkedIntList implements Iterable {

     //Field
     public ListNode front;

     //Constructor
     public LinkedIntList(){
     }

     public void addSorted(int value){
          if(front == null || value < front.data){
               front = new ListNode(value, front);
          }         
          else{
               ListNode cursor = front;
               while(cursor.next != null && cursor.next.data < value){                      
                    cursor = cursor.next;
               }
               cursor.next = new ListNode(value, cursor.next);
          }
     }
                       
     public int size(){
          int nodes = 0;
          ListNode cursor = front;
          while(cursor != null){
               nodes++;
               cursor = cursor.next;
          }
          return nodes;
     }

    //remove and return first node
    public int remove(){
        //attempting to remove from an empty list
        if(front == null){
            throw new NoSuchElementException();
        }
        else{
            //remove and return first value
            int result = front.data;
            front = front.next;
            return result;
        }  
    }

     public void remove(int index){
          //If list is empty, or index of removal is greater than number of nodes
          if(front == null || index >= this.size()){
               throw new NoSuchElementException();
          }
          //Special case for removing the 1st element in the list        
          if(index == 0){
               front = front.next;
          }
          //Removal of a middle element
          else {
               ListNode cursor = front;
               for(int i = 0; i < index - 1; i++){
                    cursor=cursor.next;
               }
               cursor.next = cursor.next.next;
          }
     }

    //Insertion add
    public void add(int index, int value){
        //0 index add
        if(index == 0){
            front = new ListNode(value, front);    
        } else {
            ListNode cursor = front;           
            for(int i = 0; i < index - 1; i++){
                cursor = cursor.next;            
            }          
            //*******
            //            kinda reads this side first
            //then does this side
            //without garbage collecting the new node
           cursor.next = new ListNode(value, cursor.next);           
        }  
    }


    //Appending add
    public void add(int value){
        if (front == null){
            front = new ListNode(value);
        }
      
        else {
            ListNode cursor = front;
      
            while(cursor.next != null) {
                cursor = cursor.next;
            }
      
            cursor.next = new ListNode(value);
            cursor = cursor.next;
        }
    } 

    public int get(int index) {
        ListNode cursor = front;
   
        for(int i = 0; i < index; i++) {
            cursor = cursor.next;
        }
   
        return cursor.data;
   
    }

    public void print() {        
        ListNode cursor = front;
   
        while(cursor != null) {
            System.out.print(cursor.data);
            cursor = cursor.next;
        }
    }
    
    public Iterator iterator(){
        return new LinkedIntListIterator();
    }
    
    
    
    
    
    
    
     private class LinkedIntListIterator implements Iterator{

          private ListNode cursor;
         
          public LinkedIntListIterator(){
               cursor = front;
          }

          @Override
          public boolean hasNext(){
               return cursor != null;    
          }
          
          //Iterator returns data field of ListNode in LinkedIntList
          //and moves the Iterator to the next ListNode
          @Override
          public Object next(){
               Integer result = cursor.data;
               cursor = cursor.next;
               return result;
          }
     }
    

}
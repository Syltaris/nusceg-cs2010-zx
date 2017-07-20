import java.util.*;
import java.lang.*;
import java.io.*;

class GeekTester {
	public static void main (String[] args) {
		//code
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		PriorityQueue<IntegerTriple> pq;
		while(T-- >0) {
		    int N = sc.nextInt();
		    IntegerTriple[] max = new IntegerTriple[N];
		    ArrayList<PriorityQueue<IntegerTriple>> waitingList = new ArrayList<PriorityQueue<IntegerTriple>>(N);
		    boolean[] taken = new boolean[N];
		    pq = new PriorityQueue<IntegerTriple>();
		    
		    for(int i=0; i<N; i++) {
		        taken[i] = false;
		        waitingList.add(new PriorityQueue<IntegerTriple>()); //for jobs if alr assigned
		    }
		        
		    for(int j=0; j<N; j++)
    		    for(int i=0; i<N; i++) {
    		        pq.offer(new IntegerTriple(sc.nextInt(), j, i)); //cost, employee, jobno.
    		    }
    		    
    		int assigned = 0;
    	    while(!pq.isEmpty()) {
    	        IntegerTriple next = pq.poll();
    	        
    	        if(taken[next.second()]) {//employee alr assigned 
    	            waitingList.get(next.second()).add(next);
    	            continue;
    	        }
    	        else if (max[next.third()] == null) {
    	            max[next.third()] = next;
    	            taken[next.second()] = true;
    	            System.out.println(next.second() + " is now taking " + next.third() + " with value " + next.first());

    	            assigned++;
    	       }
    	    }  
    	    
    	    //put all back into pq for consideration
    	    for(int i=0; i<N ; i++) {
                PriorityQueue<IntegerTriple> list = waitingList.get(i);
                while(!list.isEmpty()) {
                    pq.offer(list.poll()); //put it back to the queue
                    System.out.println("putting back..." + i +"'s ");
                }
    	    }
    	    
    	    //recheck the pqs
    	    while(!pq.isEmpty()) {
    	        IntegerTriple next = pq.poll();
    	        
	            if(max[next.third()].first() > next.first()) { //if contender better
	                taken[max[next.third()].second()] = false; //unassign the other guy
//	                PriorityQueue<IntegerTriple> list = waitingList.get(max[next.third()].second());
//	                while(!list.isEmpty()) {
//	                    pq.offer(list.poll()); //put it back to the queue
//	                    System.out.println("putting back..." + max[next.third()].second() +"'s ");
//	                }
	               
                    max[next.third()] = next;
	                taken[next.second()] = true;
    	            System.out.println("CONTENDER" + next.second() + " is now taking " + next.third()  + " with value " + next.first());

	                //assigned remains unchanged
	            }
    	        
    	    }
    		    
    		int sum = 0;
	    	for(int i=0; i<N; i++) {
	    		System.out.println(i + " is " + max[i].first());
	    		sum += max[i].first();
	    	}
		    
	    	System.out.println(sum);
		}
	}
}

class IntegerTriple implements Comparable<IntegerTriple> {
    Integer first;
    Integer second;
    Integer third;
    
    public IntegerTriple(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
    
    @Override
    public int compareTo(IntegerTriple o) {
        if(!this.first().equals(o.first())) {
            return this.first() - o.first();
        } else if(!(this.second().equals(o.second()))) {
            return this.second() - o.second();
        } else {
            return this.third() - o.third();
        }
    }
    
    public Integer first() {return this.first;}
    public Integer second() {return this.second;}
    public Integer third() {return this.third;}
}

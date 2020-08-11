import java.util.*;
public class QueuesLinkList<Datatype> {
    private LinkedList<Datatype> linkList;

    public QueuesLinkList(){
        linkList = new LinkedList<Datatype>();
    }
    public void enQueue(Datatype item){
        linkList.add(item);
    }
    public Datatype deQueue(){
        Datatype d  = linkList.get(linkList.size() - 1);
        linkList.remove(linkList.size()-1);
        return d;
    }
    public int len(){
        return linkList.size();
    }

}

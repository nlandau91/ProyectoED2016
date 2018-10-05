package TDALista;
 
import java.util.Iterator;
import java.util.NoSuchElementException;
 
public class ElementIterator<E> implements Iterator<E> {
    protected NodePositionList<E> l;
    protected Position<E> cursor;
   
    /**
     * Crea un iterador para la clase NodePositionList
     */
    public ElementIterator(NodePositionList<E> l){
        this.l = l;
        if(l.isEmpty()){
            cursor = null;
        }
        else{
            try {
                cursor = l.first();
            } catch (EmptyListException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean hasNext() {
       
        return cursor != null;
    }
 
    @Override
    
    
    public E next() throws NoSuchElementException {
        if (cursor == null){
            throw new NoSuchElementException("no hay siguiente");
        }
        E toReturn = cursor.element();
        try {
            if(cursor == l.last()){
                cursor = null;
            }
            else{
                cursor = l.next(cursor);
            }
        } catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return toReturn;
    }
 
}
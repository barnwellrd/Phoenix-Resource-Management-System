package dao;

import java.util.List;

public interface JdbcTemplateInterface <E>{
	
	public int insert(E objectToInsert);
	
	public int delete(int objectIdToDelete);
	
	public int update(E objectToUpdate);
	
	public E search(int objectID);
	
	public List<E> getAll();
}

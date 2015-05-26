
package controllers;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class Dao {
      
    public EntityManager getSession() {
        return JPAUtil.getSession();
    }
    // Guardar objeto y recibir true o false
    public boolean saveData(Object obj){
        boolean b = false;
        EntityManager s = getSession();
        EntityTransaction tx = null;
        try {
            tx = s.getTransaction();
            tx.begin();
            s.persist(obj);
            s.flush();
            tx.commit();  
            b = true;
        } catch (Exception e) {
            System.out.println(e);
            tx.rollback();
        }finally{
            s.close();
        }
        return b;
    }
     
    // Guardar objeto y recibir de nuevo el objeto guardado
        public Object saveGetData(Object obj){
        	Object obj1= null;
	        EntityManager s = getSession();
	        EntityTransaction tx = null;
	        try {
	            tx = s.getTransaction();
	            tx.begin();
	            s.persist(obj);
	            s.flush();
	            obj1 = obj;
	            tx.commit();  
	            
	        } catch (Exception e) {
	            System.out.println(e);
	            tx.rollback();
	        }finally{
	            s.close();
	        }        
	        return obj1;        
        }        

    public <T> T getById(Class<T> clase, int id){
        try {
        	System.out.println("Dentro de getById");
            EntityManager s = getSession();
            System.out.println("Dentro de getById 2 ");
            return (T)s.find(clase, id);
        } catch (Exception e) {
            return null;
        }
    }
    
    public <T> List<T> getAll(Class<T> clase){
        try {
        	CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<T> cri = cb.createQuery(clase);
            Root<T> c = cri.from(clase);
            cri.select(c);
            List<T> groupList = (List<T>) cri.select(c).getGroupList();
			return groupList;                        
        } catch (Exception e) {
            return new ArrayList<T>();
        }
        
    }
	
		   
		 //Editar usuario
		  /* public boolean editarUsuario(Usuario usuario){
			   boolean res = false;
			   EntityManager s = getSession();
			   EntityTransaction tx = null;
			   try {
				
				   tx = s.getTransaction();
				   tx.begin();
				   Query us= s.createQuery("UPDATE Usuario u SET u.apellido =:apellido, u.cedula =:cedula, u.direccion =:direccion, u.nombre =:nombre, u.telefono =:telefono, u.ruc =:ruc, u.telefono =:telefono WHERE u.idUsuario=:id");
		        	us.setParameter("apellido", usuario.getApellido());
		        	us.setParameter("cedula", usuario.getCedula());
		        	us.setParameter("direccion", usuario.getDireccion());
		        	us.setParameter("nombre", usuario.getNombre());
		        	us.setParameter("telefono", usuario.getTelefono());
		        	us.setParameter("id", usuario.getIdUsuario());
		        	
		        	Query cla= s.createQuery("UPDATE Cuenta c SET c.clave =:clave, c.estado =:estado, usuario WHERE c.usuarioBean=:id");
		        	
		        	int cont = us.executeUpdate();
		           if (cont > 0) {
		        	   System.out.println(cont+" editarEmpresa");
		        	   res =true;
		           }
		           tx.commit();
			   }catch (Exception e) {
				   tx.rollback();
			   }finally{
				   s.close();
			   }        
			   return res;
		   }*/
    
}
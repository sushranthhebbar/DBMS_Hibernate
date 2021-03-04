package HorseRacingManagement.IMT2017042;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;



public class Relations {
	public static void main(String[] args)
	{
		Configuration cfg=new Configuration().configure().addAnnotatedClass(Horse_Info.class).addAnnotatedClass(Sponsor_Info.class).addAnnotatedClass(Owner_Info.class).addAnnotatedClass(Race_Info.class);	        
	    SessionFactory factory = cfg.buildSessionFactory();
	    Session session = factory.openSession();
	    while(true)
	    {
	    	Scanner sc=new Scanner(System.in);
	    	System.out.println("Press 5 to exit");
	    	System.out.println("User");
	    	String user=sc.nextLine();
	    	if(user.equals("Owner"))
	    	{
	    		while(true)
	    		{
	    			Transaction tx=session.beginTransaction();
	    			tx.commit();
	    			System.out.println("Press -1 to create new account");
		    		System.out.println("If you already have an account,then enter your id");
		    		System.out.println("Press -2 to exit");
		    		String id=sc.nextLine();
		    		if(id.equals("-1"))
		    		{
		    			System.out.println("Enter new id");
		    			String newid=sc.nextLine();
		    			create_Owner(newid,session,tx);
		    			System.out.println("New Owner successfully created");
		    			//tx.commit();
		    			owner_interface(newid,session,tx);
		    			//tx.commit();
		    		}
		    		else if(id.equals("-2"))
		    		{
		    			break;
		    		}
		    		else
		    		{
		    			//tx=session.beginTransaction();
		    			owner_interface(id,session,tx);
		    			//tx.commit();
		    		}
	    		}
	    	}
	    	else if(user.equals("Sponsor"))
	    	{
	    		while(true)
	    		{
	    			Transaction tx=session.beginTransaction();
	    			tx.commit();
	    			System.out.println("Press -1 to create new account");
		    		System.out.println("If you already have an account,then enter your id");
		    		System.out.println("Press -2 to exit");
		    		String id=sc.nextLine();
		    		if(id.equals("-1"))
		    		{
		    			System.out.println("Enter new id");
		    			String newid=sc.nextLine();
		    			create_Sponsor(newid,session,tx);
		    			System.out.println("New Sponsor successfully created");
		    			//tx.commit();
		    			sponsor_interface(newid,session,tx);
		    			//tx.commit();
		    		}
		    		else if(id.equals("-2"))
		    		{
		    			break;
		    		}
		    		else
		    		{
		    			//tx=session.beginTransaction();
		    			sponsor_interface(id,session,tx);
		    			//tx.commit();
		    		}
	    		}
	    	}
	    	else if(user.equals("5"))
	    	{
	    		break;
	    	}
	    }
	    session.close();
	    factory.close();
	    
	}
	public static void sponsor_interface(String id,Session session,Transaction tx)
	{
		Scanner sc=new Scanner(System.in);
		String newid;
		Sponsor_Info obj=session.load(Sponsor_Info.class,id);
		while(true)
		{
			String cmd=sc.nextLine();
			if(cmd.equals("exit"))
			{
				break;
			}
			else if(cmd.equals("sponsor_Horse"))
			{
				newid=sc.nextLine();
				sponsor_Horse(obj,newid,session,tx);
			}
			else if(cmd.equals("remove_Sponsor"))
			{
				newid=sc.nextLine();
				remove_Sponsor(obj,session,tx);
			}
			else if(cmd.equals("unsponsor_Horse"))
			{
				newid=sc.nextLine();
				unsponsor_Horse(obj,newid,session,tx);
			}
			else if(cmd.equals("obtain_Ownerid"))
			{
				newid=sc.nextLine();
				obtain_Ownerid(newid,session,tx);
			}
			else if(cmd.equals("obtain_Raceid"))
			{
				newid=sc.nextLine();
				obtain_Raceid(newid,session,tx);
			}
			else if(cmd.equals("display_owners"))
			{
				newid=sc.nextLine();
				display_owners(session);
			}
			else if(cmd.equals("display_horses"))
			{
				newid=sc.nextLine();
				display_horses(session);
			}
			else if(cmd.equals("display_races"))
			{
				newid=sc.nextLine();
				display_races(session);
			}
			else if(cmd.equals("Help"))
			{
				System.out.println("Type remove_Sponsor to remove a sponsor record");
				System.out.println("Type sponsor_Horse to sponsor the required horse");
				System.out.println("Type unsponsor_Horse to terminate sponsorship");
				System.out.println("Type obtain_Ownerid to receive the owner id of the horse");
				System.out.println("Type obtain_Raceid to receive the all raceids of the horse");
				System.out.println("Type display_owners to receive all the owner records");
				System.out.println("Type display_horses to receive all the horse records");
				System.out.println("Type display_races to receive all the race records");
				System.out.println("Type exit to leave");
			}
		}
	}
	public static void create_Sponsor(String id,Session session,Transaction tx)
	{
		tx=session.beginTransaction();
	 	Sponsor_Info sf=new Sponsor_Info();
	    sf.setSponsorid(id);
	    sf.setSponsor_name("Sponsor_1");
	    sf.setSponsor_contact("Sponsor_Contact_1");
	    session.save(sf);
	    tx.commit();
	}
	public static void remove_Sponsor(Sponsor_Info obj,Session session,Transaction tx)
	{
		tx=session.beginTransaction();
		session.delete(obj);
		System.out.println("Sucessfully removed a sponsor record");
		tx.commit();
	}
	public static void sponsor_Horse(Sponsor_Info obj,String newid,Session session,Transaction tx)
	{
		tx=session.beginTransaction();
		Horse_Info hf=session.load(Horse_Info.class,newid);
		hf.setSponsor(obj);
		Set s=new HashSet();
		if(obj.getChildren()!=null)
	    {
	    	 s=obj.getChildren();
	    }
	    s.add(hf);
	    obj.setChildren(s);
	    session.save(hf);
	    System.out.println("Sucessfully sponsored a horse");
		tx.commit();
	}
	public static void unsponsor_Horse(Sponsor_Info obj,String newid,Session session,Transaction tx)
	{
		tx=session.beginTransaction();
		Set s=new HashSet();
		s=obj.getChildren();
		Iterator values=s.iterator();
		while(values.hasNext())
		{
			Horse_Info obj1=(Horse_Info) values.next();
			//System.out.println();
			if(obj1.getHorseid().equals(newid))
			{
				obj1.setSponsor(null);
				s.remove(obj1);
				System.out.println("This horse has been unsponsored");
				break;
			}
		}
		tx.commit();
	}
	public static void obtain_Ownerid(String newid,Session session,Transaction tx)
	{
		tx=session.beginTransaction();
		Horse_Info obj=session.load(Horse_Info.class, newid);
		System.out.println(obj.getOwner().getOwnerid());
		tx.commit();
	}
	public static void obtain_Raceid(String newid,Session session,Transaction tx)
	{
		tx=session.beginTransaction();
		Horse_Info obj=session.load(Horse_Info.class, newid);
		
		if(obj.getChildren()!=null)
		{
			Set s=new HashSet();
			s=obj.getChildren();
			if(s.isEmpty()==true)
			{
				System.out.println("This horse has not participated in any races");
			}
			else
			{
				Iterator value=s.iterator();
				while(value.hasNext())
				{
					Race_Info r=(Race_Info) value.next();
					System.out.println(r.getRaceid());
				}
			}
		}
		else
		{
			System.out.println("This horse has not participated in any races");
		}
		tx.commit();
	}
	public static void display_sponsors(Session session)
	{
		String hql="SELECT s.Sponsorid FROM Sponsor_Info s";
		Query query = session.createQuery(hql);
		List results = query.list();
		for(int i=0;i<results.size();i++)
		{
			System.out.println(results.get(i));
		}
		
	}
	public static void display_owners(Session session)
	{
		String hql="SELECT s.Ownerid FROM Owner_Info s";
		Query query = session.createQuery(hql);
		List results = query.list();
		for(int i=0;i<results.size();i++)
		{
			System.out.println(results.get(i));
		}
		
	}
	public static void display_horses(Session session)
	{
		String hql="SELECT s.Horseid FROM Horse_Info s";
		Query query = session.createQuery(hql);
		List results = query.list();
		for(int i=0;i<results.size();i++)
		{
			System.out.println(results.get(i));
		}
		
	}
	public static void display_races(Session session)
	{
		String hql="SELECT s.Raceid FROM Race_Info s";
		Query query = session.createQuery(hql);
		List results = query.list();
		for(int i=0;i<results.size();i++)
		{
			System.out.println(results.get(i));
		}
		
	}
	//session.delete(obj)
	public static void owner_interface(String id,Session session,Transaction tx)
	{
		Scanner sc=new Scanner(System.in);
		String newid;
		Owner_Info obj=session.load(Owner_Info.class, id);
		while(true)
		{
			String cmd=sc.nextLine();
			if(cmd.equals("exit"))
			{
				break;
			}
			else if(cmd.equals("add_Horse"))
			{
				newid=sc.nextLine();
				add_Horse(obj,newid,session,tx);
				System.out.println("Horse added successfully");
			}
			else if(cmd.equals("add_Race"))
			{
				newid=sc.nextLine();
				String raceid=sc.nextLine();
				String name=sc.nextLine();
				String distance=sc.nextLine();
				String time=sc.nextLine();
				String position =sc.nextLine();
				Set s=new HashSet();
				s=obj.getChildren();
				Iterator value=s.iterator();
				int flag=0;
				while(value.hasNext())
				{
					Horse_Info h=(Horse_Info) value.next();
					//System.out.println(h.getHorseid());
					if(h.getHorseid().equals(newid))
					{
						add_Race(h,raceid,name,time,distance,position,session,tx);
						flag=1;
						System.out.println("This race record has been successfully added");
						break;
					}
				}
				if(flag==0)
				{
					System.out.println("This horse does not exist");
				}
			}
			else if(cmd.equals("remove_Race"))
			{
				String horseid=sc.nextLine();
				String raceid=sc.nextLine();
				Set s=new HashSet();
				s=obj.getChildren();
				Iterator value=s.iterator();
				while(value.hasNext())
				{
					Horse_Info obj1=(Horse_Info)value.next();
					if(obj1.getHorseid().equals(horseid))
					{
						remove_Race(obj1,raceid,session,tx);
						break;
					}
				}
			}
			else if(cmd.equals("remove_Horse"))
			{
				String horseid=sc.nextLine();
				remove_Horse(obj,horseid,session,tx);
			}
			else if(cmd.equals("remove_Owner"))
			{
				String ownerid=sc.nextLine();
				remove_Owner(obj,session,tx);
				
			}
			else if(cmd.equals("obtain_Sponsorid"))
			{
				newid=sc.nextLine();
				obtain_Sponsorid(newid,session,tx);
			}
			else if(cmd.equals("obtain_Raceid"))
			{
				newid=sc.nextLine();
				obtain_Raceid(newid,session,tx);
			}
			else if(cmd.equals("display_races"))
			{
				newid=sc.nextLine();
				display_races(session);
			}
			else if(cmd.equals("display_horses"))
			{
				newid=sc.nextLine();
				display_horses(session);
			}
			else if(cmd.equals("display_sponsors"))
			{
				newid=sc.nextLine();
				display_sponsors(session);
			}
			else if(cmd.equals("Help"))
			{
				System.out.println("Type add_Horse to insert a new horse record");
				System.out.println("Type add_Race to insert a new race record");
				System.out.println("Type remove_Race to remove a new record");
				System.out.println("Type remove_Horse to remove a horse record");
				System.out.println("Type remove_owner to remove an owner record");
				System.out.println("Type obtain_Sponsorid to receive the sponsor id of the horse");
				System.out.println("Type obtain_Raceid to receive the all raceids of the horse");
				System.out.println("Type display_sponsors to receive all the owner records");
				System.out.println("Type display_horses to receive all the horse records");
				System.out.println("Type display_races to receive all the race records");
				System.out.println("Type exit to logout");
			}
		}
	}
	
	public static void create_Owner(String id,Session session,Transaction tx)
	{
		tx=session.beginTransaction();
		//System.out.println("in create Owner");
		Owner_Info of=new Owner_Info();
	    of.setOwnerid(id);
	    of.setOwner_Name("Owner_"+id);
	    of.setOwner_Contact("Owner_Contact_"+id);
	    session.save(of);
	    tx.commit();
	}
	
	public static void add_Horse(Owner_Info obj,String id,Session session,Transaction tx)
	{
		tx=session.beginTransaction();
		Horse_Info hf=new Horse_Info();
	    hf.setAge(2);
	    hf.setBest_time("-1");
	    hf.setHorseid(id);
	    hf.setNo_of_races(0);
	    hf.setNo_of_wins(0);
	   // hf.setSponsorid("1");
	    hf.setWeight(90);
	    hf.setWorst_time("-1");
	    hf.setOwner(obj);
	    Set s=new HashSet();
	    //System.out.println("HERE");
	    if(obj.getChildren()!=null)
	    {
	    	 s=obj.getChildren();
	    }
	    s.add(hf);
	    obj.setChildren(s);
	    session.save(hf);
	    tx.commit();
	}
	
	public static void add_Race(Horse_Info obj,String raceid,String name,String time,String distance,String position,Session session,Transaction tx)
	{
		tx=session.beginTransaction();
		//System.out.println("Here in add_Race");
		Race_Info rf11=new Race_Info();
	    rf11.setCompetition_Name(name);
	    //rf11.setHorseid("1");
	    rf11.setPosition(position);
	    rf11.setRaceid(raceid);
	    rf11.setTime(time);
	    rf11.setTrack_Distance(distance);
	    rf11.setTrack_Name(name);
	    rf11.setParent(obj);
	    Set s=new HashSet();
	    if(obj.getChildren()!=null)
	    {
	    	 s=obj.getChildren();
	    }
	    s.add(rf11);
	    obj.setChildren(s);
	    session.save(rf11);
	    tx.commit();
	}
	
	public static void remove_Race(Horse_Info obj,String raceid,Session session,Transaction tx)
	{
	//	System.out.println("Here in remove_Race");
		tx=session.beginTransaction();
		Set s=new HashSet();
		s=obj.getChildren();
		Iterator value=s.iterator();
		while(value.hasNext())
		{
			Race_Info r=(Race_Info) value.next();
			//System.out.println(r.getRaceid());
			if(r.getRaceid().equals(raceid))
			{
				s.remove(r);
				session.delete(r);
				System.out.println("Successfully removed Racing Record");
				break;
			}
		}
		tx.commit();
	}
	
	public static void remove_Horse(Owner_Info obj,String id,Session session,Transaction tx)
	{
		tx=session.beginTransaction();
		Set s=new HashSet();
		s=obj.getChildren();
		Iterator value=s.iterator();
		while(value.hasNext())
		{
			Horse_Info h=(Horse_Info) value.next();
			if(h.getHorseid().equals(id))
			{
				s.remove(h);
				session.delete(h);
				System.out.println("Horse record has been successfully deleted");
				break;
			}
		}
		tx.commit();
	}
	public static void remove_Owner(Owner_Info obj,Session session,Transaction tx)
	{
		tx=session.beginTransaction();
		session.delete(obj);
		System.out.println("Owner record successfully deleted");
		tx.commit();
	}
	public static void obtain_Sponsorid(String newid,Session session,Transaction tx)
	{
		tx=session.beginTransaction();
		Horse_Info obj=session.load(Horse_Info.class, newid);
		if(obj.getSponsor()!=null)
		{
			System.out.println(obj.getSponsor().getSponsorid());
		}
		else
		{
			System.out.println("This horse is not sponsored");
		}
		tx.commit();
	}
}

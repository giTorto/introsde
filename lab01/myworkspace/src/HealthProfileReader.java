import pojos.HealthProfile;
import pojos.Person;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.exit;


public class HealthProfileReader {
	
	public static Map<Long,Person> database = new HashMap<Long,Person>();

	static
    {
    	Person pallino = new Person();
		Person pallo = new Person(new Long(1005),"Pinco","Pallo","1992-12-12");
		HealthProfile hp = new HealthProfile(68.0,1.72);
		Person john = new Person(new Long(1002),"John","Doe","1999-11-11",hp);
		
		database.put(pallino.getPersonId(), pallino);
		database.put(pallo.getPersonId(), pallo);
		database.put(john.getPersonId(), john);
    }
	/**
	 * The health profile reader gets information from the command line about
	 * weight and height and calculates the BMI of the person based on this 
	 * parameters
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//initializeDatabase();
		int argCount = args.length;
		if (argCount == 0) {
			System.out.println("I cannot do anithing without a function name.");
		} else if (argCount < 2) {
			System.out.println("I need a function name and its parameters");
		} else {
			String funName = args[0].toLowerCase();
            Long personId = null;

            if( args[1].matches("[1234567890]+") ) {
                personId = new Long(args[1]);
            }else{
                System.out.println("Wrong person id, must be a number");
                exit(0);
            }

            switch (funName) {
                case "createperson": {
                    if (argCount<5) {
                        System.out.println("I need more parameters");
                        exit(0);
                    }else{
                        String firstName = new String(args[2]);
                        String lastName = new String(args[3]);
                        String birthday = new String(args[4]);
                        createPerson(personId, firstName, lastName, birthday);
                        exit(0);
                    }
                }
                case "displayhealthproﬁle": {
                    displayHealthProﬁle(personId);
                    exit(0);
                }
                case "updatehealthproﬁle": {
                    if (argCount<4) {
                        System.out.println("I need more parameters");
                        exit(0);
                    } else {
                        updateHealthProﬁle(personId,new Double(args[2]),new Double(args[3]));
                    }
                }
                default:
                    System.out.println("This function doesn't exist");
            }

		}
		// add the case where there are 3 parameters, the third being a string that matches "weight", "height" or "bmi"
	}
	
	//public static void initializeDatabase() {
	//	Person pallino = new Person();
	//	Person pallo = new Person("Pinco","Pallo");
	//	HealthProfile hp = new HealthProfile(68.0,1.72);
	//	Person john = new Person("John","Doe",hp);
	//	
	//	database.put(pallino.getFirstname()+" "+pallino.getLastname(), pallino);
	//	database.put(pallo.getFirstname()+" "+pallo.getLastname(), pallo);
	//	database.put(john.getFirstname()+" "+john.getLastname(), john);
	//}

    public static void createPerson(Long personID, String firstName, String lastName, String birthDate){
        Person p = new Person(personID, firstName, lastName, birthDate);
        database.put(p.getPersonId(), p);
        System.out.println("Addition DONE");
    }


    public static void displayHealthProﬁle(Long personId) {
        Person p = database.get(personId);
        if (p != null)
            System.out.println(p.getFirstname()+" "+p.getLastname()+"'s health profile is: "+p.gethProfile().toString());
        else{
            System.out.println("This id doesn't exist");
            exit(0);
        }
    }

    public static void updateHealthProﬁle(Long	personId, Double height, Double	weight) {
        Person p = database.get(personId);
        p.sethProfile(new HealthProfile(weight,height));
        database.remove(personId);
        database.put(personId, p);
        System.out.println("Update DONE");

    }
}
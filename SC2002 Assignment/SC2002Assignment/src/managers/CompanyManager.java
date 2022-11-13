package managers;

import company_entities.Cineplex;
import company_entities.Company;
import company_entities.Cinema;
import utils.ProjectRootPathFinder;
import utils.SerializerHelper;

import java.util.ArrayList;

/**
 * Company manager Class. Company holds 3 cineplexes which in turn each holds 3 Cinemas
 */
public class CompanyManager {
	// Attributes
	/**
	 * company object
	 */
	private Company company;

	
	
	/** 
     * Checks if there is a single instance of CompanyManager
     */
	private static CompanyManager single_instance = null;
	
	/**
     * Creates an instance of CompanyManager if none exists, if one exists we use that instance instead.
     * @return an instance of CompanyManager.
     */
	public static CompanyManager getInstance() {
		if (single_instance == null)
			single_instance = new CompanyManager();
		return single_instance;
	}
	
	
	
    /**
     * Constructor of CompanyManager. Read serialized data, if none exists we will create.
     */
	private CompanyManager() {
		Company serializedObject = this.load();
		if (serializedObject != null) {
			this.company = serializedObject;
		} else {
			this.company = new Company();
			this.save();
			System.out.println("Company File created!");
		}
	}
	
	
	// Public exposed methods to app
	/**
	 * Returns company
	 * @return Company
	 */
	public Company getCompany(){
		return company;
	}
	
	/**
	 * This returns a deep copy of a cinema for the showtime manager to create a new showtime from. 
	 * The deep copy is always of the completely empty cinema 
	 * @param cinemaID This is the ID of the cinema to be copied
	 * @return Cinema this returns a new cinema object with all its attributes
	 */
	public Cinema getNewCinema(String cinemaID) {
		int i;
		int noOfCineplex = this.company.getCineplexes().size();
		
		for (i=0;i<noOfCineplex;i++) {
			Cineplex cineplex = this.company.getCineplexes().get(i);
			
			if (cineplex.getCinemaIDs().contains(cinemaID)) {
				int cinemaIndex = cineplex.getCinemaIDs().indexOf(cinemaID);

				Cinema newCinema = new Cinema(cinemaID);
				Cinema oldCinema = cineplex.getCinemas().get(cinemaIndex);

				newCinema.setCinemaID(oldCinema.getCinemaID());
				newCinema.setHallNo(oldCinema.getHallNo());
				newCinema.setCinemaType(oldCinema.getCinemaType());
				newCinema.setTotalSeatNo(oldCinema.getTotalSeatNo());
				newCinema.setOccupiedSeatsNo(oldCinema.getOccupiedSeatsNo());
				newCinema.setCinemaLayout(oldCinema.getCinemaLayout());
				return newCinema;
			}
		}
		
		return null;
	}
	
	/**
	 *  returns all the Cineplex Names that  company owns
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getCineplexNames() {
		ArrayList<String> cineplexNames = (ArrayList<String>) this.company.getCineplexNames();
		return cineplexNames;
	}
	
	
	// Private Serialization and Deserialization
	/**
	 * This saves the entire company object and serializes it
	 */
	public void save() {
		String filePath = ProjectRootPathFinder.findProjectRootPath() + "/data/company/company.dat";
		SerializerHelper.serializeObject(this.company, filePath);
	}
	
	/**
	 * This returns the entire company object from a serialized file
	 * @return
	 */
	public Company load() {
		String filePath = ProjectRootPathFinder.findProjectRootPath() + "/data/company/company.dat";
		return (Company) SerializerHelper.deSerializeObject(filePath);
	}
}
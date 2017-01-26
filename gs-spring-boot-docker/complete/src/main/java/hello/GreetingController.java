package hello;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

@RestController
public class GreetingController {

    private static String template = "Hi Hello Welcome, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
	private ListRepository repository;

	@CrossOrigin
	@RequestMapping("/lists")
    //public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	System.out.println("/lists ----------?");
    	/*repository.deleteAll();

		// save a couple of customers
		repository.save(new WatchList("List 01", "Company", "shamnalan"));
		repository.save(new WatchList("List 02", "Asset", "jamesbond"));
		repository.save(new WatchList("List 03", "Company", "ironman"));*/

		/*ArrayList<String> obj = new ArrayList<String>();
		Map dictionary = new HashMap();
			dictionary.put("one", "first");
			dictionary.put("two", "second");*/

		String contents = "<tr><td><b>List Name</b></td><td><b>Type</b></td><td><b>List Type</b></td>"
		+"<td><b>Owner</b></td><td><b>Last Updated</b></td><td><b>Access Level</b></td></tr>";
	    for (WatchList list : repository.findAll()) {
			//System.out.println(customer);
			//template = template + customer.firstName;
			contents = contents + "<tr><td><a href=# target='_blank' onclick=window.open('/listDetails.html','mywin','width=500,height=200'); >" + list.listName +"</a></td>"
			+"<td>Type</td><td>"+list.listType+"</td><td>"+list.listOwner
			+"</td><td>01-13-2017</td><td>_</td></tr>";
		}

		/*Customer customer = (Customer)repository.findByFirstName("Alice");

		template = customer.firstName +"----------"+customer.lastName+" : "+customer.type;*/
		//template = template + "-" + ((Customer)repository.findByLastName("Smith")).firstName;

		// fetch all customers
		/*String customerStr = "";
		for (Customer customer : repository.findAll()) {
			//System.out.println(customer);
			template = template + customer.firstName;
		}

		System.out.println(repository.findByFirstName("Alice"));

		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}*/

		 

        /*return new Greeting(counter.incrementAndGet(),
                           // String.format(template, name), obj);
        					"", obj, dictionary);*/
        return "<table border='1' width='100%'>"+contents+"</table><table>"+
        "<tr><td><a href=# target='_blank' onclick=window.open('/createpage','mywin','width=500,height=200'); >Create List</a></td></tr>"+
        "<tr><td><a href='/deleteall'>Delete All</a></td></tr></table>";
    }

    @RequestMapping(value = "/createpage", method = RequestMethod.GET)
    public ModelAndView createPage(Locale locale, Model model) {
    	System.out.println("in /static/html");
        return new ModelAndView("/createList.html"); // NOTE here there is /someurl/resources
    }

    @RequestMapping("/createlist")
    public ModelAndView create(@RequestParam(value="listname") String listname, 
    						   @RequestParam(value="listtype") String listtype,
    						   @RequestParam(value="listowner") String listowner) {
    //@RequestMapping(value = "/create", method = RequestMethod.GET)
    //public ModelAndView home(Locale locale, Model model) {
    	System.out.println("Input param - "+listname +" - "+listtype+" - "+listowner);

    	//repository.deleteAll();

		// save a couple of customers
		repository.save(new WatchList(listname, listtype, listowner));
	
        //return new ModelAndView("/lists"); // NOTE here there is /someurl/resources
        return new ModelAndView("/close.html");
    }

    @RequestMapping(value = "/deleteall", method = RequestMethod.GET)
    public ModelAndView deleteAll(Locale locale, Model model) {
    	//System.out.println("in /static/html");
    	repository.deleteAll();
        return new ModelAndView("/lists"); // NOTE here there is /someurl/resources
    }
}
